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
  implementation(projects.persistence)
  implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-cli-jvm", version = "0.3.4")
}
