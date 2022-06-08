plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("io.bkbn.sourdough.application.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  application
}

sourdoughApp {
  mainClassName.set("io.bkbn.sourdough.api.ApiKt")
}

dependencies {
  implementation(projects.domain)
  implementation(projects.persistence)

  // Logging
  implementation("ch.qos.logback:logback-classic:1.2.11")

  // Serialization
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

  // Server
  implementation("io.ktor:ktor-server-core:1.6.8")
  implementation("io.ktor:ktor-server-netty:1.6.8")
  implementation("io.ktor:ktor-serialization:1.6.8")

  // OpenApi Generation
  implementation("io.bkbn:kompendium-core:2.3.5")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.3.0")
        implementation("io.kotest:kotest-assertions-core-jvm:5.3.0")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.8")
      }
    }
    create<JvmTestSuite>("testIntegration") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.3.0")
        implementation("io.kotest:kotest-assertions-core-jvm:5.3.0")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.8")
      }
    }
  }
}
