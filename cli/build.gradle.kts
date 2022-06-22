plugins {
  kotlin("jvm")
  id("io.bkbn.sourdough.application.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  application
}

sourdoughApp {
  mainClassName.set("io.bkbn.sourdough.cli.EntrypointKt")
}

dependencies {
  // Sourdough
  implementation(projects.persistence)
  implementation(projects.domain)

  // CLI
  implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.4")

  // Data generation
  implementation("io.github.unredundant:satisfaketion-core:0.6.4")
  implementation("io.github.unredundant:satisfaketion-generators:0.6.4")
  implementation("io.github.unredundant:satisfaketion-mutators:0.6.4")

  // Logging
  implementation("co.touchlab:kermit:1.1.3")
}
