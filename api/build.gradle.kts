plugins {
  id("kotlin-application-conventions")
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
        implementation("io.ktor:ktor-client-mock:1.6.5")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")
      }
    }
    named<JvmTestSuite>("testIntegration") {
      dependencies {
        implementation("io.ktor:ktor-client-mock:1.6.5")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")
      }
    }
  }
}
