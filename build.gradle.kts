plugins {
  // Root Plugins
  id("io.bkbn.sourdough.root")
  id("com.github.jakemarsden.git-hooks")
  id("org.jetbrains.kotlinx.kover")

  // Child Plugins
  kotlin("jvm") apply false
  id("com.google.devtools.ksp") apply false
  kotlin("plugin.serialization") apply false
  id("io.bkbn.sourdough.library.jvm") apply false
  id("io.bkbn.sourdough.application.jvm") apply false
  id("com.adarshr.test-logger") apply false
}

gitHooks {
  setHooks(
    mapOf(
      "pre-commit" to "detekt",
      "pre-push" to "test"
    )
  )
}

dependencies {
  subprojects.forEach { kover(it) }
}

allprojects {
  group = "io.bkbn"
  version = run {
    val baseVersion =
      project.findProperty("project.version") ?: error("project.version needs to be set in gradle.properties")
    when ((project.findProperty("release") as? String)?.toBoolean()) {
      true -> baseVersion
      else -> "$baseVersion-SNAPSHOT"
    }
  }
  plugins.withType(JavaPlugin::class.java) {
    configure<JavaPluginExtension> {
      toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
      }
    }
  }
}
