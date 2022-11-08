plugins {
  // Root Plugins
  id("io.bkbn.sourdough.root") version "0.12.0"
  id("com.github.jakemarsden.git-hooks") version "0.0.2"
  id("org.jetbrains.kotlinx.kover") version "0.6.1"

  // Child Plugins
  kotlin("jvm") version "1.7.21" apply false
  kotlin("plugin.serialization") version "1.7.21" apply false
  id("io.bkbn.sourdough.library.jvm") version "0.12.0" apply false
  id("io.bkbn.sourdough.application.jvm") version "0.12.0" apply false
  id("io.gitlab.arturbosch.detekt") version "1.21.0" apply false
  id("com.adarshr.test-logger") version "3.2.0" apply false
}

gitHooks {
  setHooks(
    mapOf(
      "pre-commit" to "detekt",
      "pre-push" to "test"
    )
  )
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
