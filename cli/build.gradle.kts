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
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

  // CLI
  implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.5")

  // Data generation
  implementation("io.github.unredundant:satisfaketion-core:0.6.4")
  implementation("io.github.unredundant:satisfaketion-generators:0.6.4")
  implementation("io.github.unredundant:satisfaketion-mutators:0.6.4")

  // Logging
  implementation("co.touchlab:kermit:1.2.2")
}
