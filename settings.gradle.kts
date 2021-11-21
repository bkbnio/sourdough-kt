rootProject.name = "sourdough-kt"

include("app")
include("api")
include("lib")
include("cli")

// Feature Previews
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

// Version Catalog
object Groups {
  const val detekt = "io.gitlab.arturbosch.detekt"
  const val kotest = "io.kotest"
  const val kotlin = "org.jetbrains.kotlin"
  const val kotlinx = "org.jetbrains.kotlinx"
  const val ktor = "io.ktor"
  const val logback = "ch.qos.logback"
  const val mockk = "io.mockk"
  const val slf4j = "org.slf4j"
}

dependencyResolutionManagement {
  val gitHookVersion: String by settings
  val nexusPublishVersion: String by settings
  val kotlinxSerializationPluginVersion: String by settings
  val kotlinxCoroutinesVersion: String by settings
  val kotlinxCliVersion: String by settings
  val kotlinxDatetimeVersion: String by settings
  val kotlinxSerializationVersion: String by settings
  val ktorVersion: String by settings

  versionCatalogs {
    create("build") {
      alias("git-hooks").toPluginId("com.github.jakemarsden.git-hooks").version(gitHookVersion)
      alias("nexus-publish").toPluginId("io.github.gradle-nexus.publish-plugin").version(nexusPublishVersion)
      alias("serialization").toPluginId("org.jetbrains.kotlin.plugin.serialization").version(kotlinxSerializationPluginVersion)
    }
    create("ktor") {
      alias("server-core").to(Groups.ktor, "ktor-server-core").version(ktorVersion)
      alias("server-netty").to(Groups.ktor, "ktor-server-netty").version(ktorVersion)

      bundle("server", listOf("server-core", "server-netty"))
    }
    create("ktx") {
      alias("cli").to(Groups.kotlinx, "kotlinx-cli-jvm").version(kotlinxCliVersion)
      alias("coroutines").to(Groups.kotlinx, "kotlinx-coroutines-core").version(kotlinxCoroutinesVersion)
      alias("datetime").to(Groups.kotlinx, "kotlinx-datetime").version(kotlinxDatetimeVersion)
      alias("serialization").to(Groups.kotlinx, "kotlinx-serialization-json").version(kotlinxSerializationVersion)
    }
  }
}
