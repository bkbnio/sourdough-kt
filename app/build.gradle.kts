plugins {
  kotlin("jvm")
  id("io.bkbn.sourdough.application.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
  application
}

sourdoughApp {
  mainClassName.set("io.bkbn.sourdough.app.AppKt")
}

dependencies {
  implementation(projects.persistence)
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation(testLibs.kotest.runner.junit5.jvm)
        implementation(testLibs.kotest.assertions.core.jvm)
      }
    }
  }
}
