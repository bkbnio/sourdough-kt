plugins {
  kotlin("jvm")
  id("io.bkbn.sourdough.application.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
  application
}

sourdoughApp {
  mainClassName.set("io.bkbn.sourdough.cli.EntrypointKt")
}

dependencies {
  // Sourdough
  implementation(projects.persistence)
  implementation(projects.domain)

  // Coroutines
  implementation(libs.ktx.coroutines.core)

  // CLI
  implementation(libs.ktx.cli)

  // Data generation
  implementation(libs.bundles.satisfaketion)

  // Logging
  implementation(libs.kermit)
}
