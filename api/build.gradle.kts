plugins {
  kotlin("jvm") version "1.6.10"
  id("io.bkbn.sourdough.application.jvm") version "0.5.4"
  id("io.gitlab.arturbosch.detekt") version "1.19.0"
  id("com.adarshr.test-logger") version "3.1.0"
  application
}

sourdough {
  mainClassName.set("io.bkbn.sourdough.api.ApiKt")
}

dependencies {
  implementation(projects.lib)

  implementation(group = "io.ktor", name = "ktor-server-core", version = "1.6.7")
  implementation(group = "io.ktor", name = "ktor-server-netty", version = "1.6.7")
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
        implementation("io.ktor:ktor-client-mock:1.6.7")
      }
    }
    create<JvmTestSuite>("testIntegration") {
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.7")
      }
    }
  }
}
