rootProject.name = "sourdough-kt"

include("app")
include("api")
include("lib")
include("cli")

// Feature Previews
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
