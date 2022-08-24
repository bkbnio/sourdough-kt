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
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

  // Server
  val ktorVersion: String by project

  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-cio:$ktorVersion")
  implementation("io.ktor:ktor-server-auth:$ktorVersion")
  implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
  implementation("io.ktor:ktor-serialization:$ktorVersion")
  implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
  implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
  implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

  // OpenApi Generation
  implementation("io.bkbn:kompendium-core:3.2.0")

  // Datetime
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.4.2")
        implementation("io.kotest:kotest-assertions-core-jvm:5.4.2")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.8")
      }
    }
    create<JvmTestSuite>("testIntegration") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.4.2")
        implementation("io.kotest:kotest-assertions-core-jvm:5.4.2")
        implementation("io.kotest:kotest-assertions-ktor:4.4.3")

        // Ktor
        implementation("io.ktor:ktor-client-mock:1.6.8")
      }
    }
  }
}
