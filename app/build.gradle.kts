plugins {
  kotlin("jvm") version "1.6.10"
  id("io.bkbn.sourdough.application.jvm") version "0.6.0"
  id("io.gitlab.arturbosch.detekt") version "1.19.0"
  id("com.adarshr.test-logger") version "3.2.0"
  application
}

sourdough {
  mainClassName.set("io.bkbn.sourdough.app.AppKt")
}

dependencies {
  implementation(projects.lib)
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.1.0")
        implementation("io.kotest:kotest-assertions-core-jvm:5.1.0")
      }
    }
  }
}
