plugins {
  application
}

dependencies {
  implementation(projects.lib)
}

application {
  mainClass.set("io.bkbn.sourdough.app.AppKt")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
      }
    }
  }
}
