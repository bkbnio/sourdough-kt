plugins {
  kotlin("jvm") version "1.6.21"
  id("io.bkbn.sourdough.application.jvm") version "0.6.0"
  id("io.gitlab.arturbosch.detekt") version "1.19.0"
  id("com.adarshr.test-logger") version "3.2.0"
  application
}

sourdough {
  mainClassName.set("io.bkbn.sourdough.cli.EntrypointKt")
}

dependencies {
  implementation(projects.lib)
  implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-cli-jvm", version = "0.3.4")
}
