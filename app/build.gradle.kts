plugins {
  kotlin("jvm")
  id("io.bkbn.sourdough.application.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
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
        implementation("io.kotest:kotest-runner-junit5-jvm:5.3.2")
        implementation("io.kotest:kotest-assertions-core-jvm:5.3.2")
      }
    }
  }
}
