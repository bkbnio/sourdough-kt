plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(ktor.bundles.server)
}

application {
  mainClass.set("io.bkbn.sourdough.api.ApiKt")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.5")
      }
    }
    create<JvmTestSuite>("testIntegration") {
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.5")
      }
    }
  }
}
