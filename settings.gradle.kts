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
  val detektVersion: String by settings
  val gitHookVersion: String by settings
  val nexusPublishVersion: String by settings
  val kotestVersion: String by settings
  val kotlinVersion: String by settings
  val kotlinxCoroutinesVersion: String by settings
  val kotlinxCliVersion: String by settings
  val kotlinxDatetimeVersion: String by settings
  val kotlinxSerializationVersion: String by settings
  val ktorVersion: String by settings
  val logbackVersion: String by settings
  val mockkVersion: String by settings
  val slf4jVersion: String by settings
  val testLoggerVersion: String by settings

  versionCatalogs {
    create("build") {
      alias("detekt").toPluginId("io.gitlab.arturbosch.detekt").version(detektVersion)
      alias("git-hooks").toPluginId("com.github.jakemarsden.git-hooks").version(gitHookVersion)
      alias("kotlin").toPluginId("org.jetbrains.kotlin.jvm").version(kotlinVersion)
      alias("nexus-publish").toPluginId("io.github.gradle-nexus.publish-plugin").version(nexusPublishVersion)
      alias("serialization").toPluginId("org.jetbrains.kotlin.plugin.serialization").version(kotlinVersion)
      alias("test-logger").toPluginId("com.adarshr.test-logger").version(testLoggerVersion)
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
    create("logs") {
      alias("slf4j").to(Groups.slf4j, "slf4j-api").version(slf4jVersion)
      alias("logback-classic").to(Groups.logback, "logback-classic").version(logbackVersion)
      alias("logback-core").to(Groups.logback, "logback-core").version(logbackVersion)

      bundle("default", listOf("slf4j", "logback-classic", "logback-core"))
    }
    create("misc") {
      alias("detekt-formatting").to(Groups.detekt, "detekt-formatting").version(detektVersion)
    }
    create("test") {
      alias("ktor-client-mock").to(Groups.ktor, "ktor-client-mock").version(ktorVersion)
      alias("kotest-runner-junit").to(Groups.kotest, "kotest-runner-junit5").version(kotestVersion)
      alias("kotest-assertions-core").to(Groups.kotest, "kotest-assertions-core-jvm").version(kotestVersion)
      alias("kotest-assertions-json").to(Groups.kotest, "kotest-assertions-json-jvm").version(kotestVersion)
      alias("kotest-assertions-ktor").to(Groups.kotest, "kotest-assertions-ktor").version(kotestVersion)
      alias("kotest-property").to(Groups.kotest, "kotest-property-jvm").version(kotestVersion)
      alias("mockk").to(Groups.mockk, "mockk").version(mockkVersion)

      bundle(
        "unit",
        listOf(
          "ktor-client-mock",
          "kotest-runner-junit",
          "kotest-assertions-core",
          "kotest-assertions-json",
          "kotest-assertions-ktor",
          "kotest-property",
          "mockk",
        )
      )

      bundle(
        "integration",
        listOf(
          "kotest-runner-junit",
          "kotest-assertions-core",
          "kotest-assertions-json",
          "kotest-assertions-ktor",
          "kotest-property",
        )
      )
    }
  }
}
