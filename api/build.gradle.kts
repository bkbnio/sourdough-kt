plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("io.bkbn.sourdough.application.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
  application
}

sourdoughApp {
  mainClassName.set("io.bkbn.sourdough.api.ApiKt")
}

dependencies {
  implementation(projects.domain)
  implementation(projects.persistence)

  // Logging
  implementation(libs.logback.classic)

  // Serialization
  implementation(libs.ktx.serialization)

  // Ktor Server
  implementation(libs.bundles.ktor.server)

  // Datetime
  implementation(libs.ktx.datetime)
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation(testLibs.kotest.runner.junit5.jvm)
        implementation(testLibs.kotest.assertions.core.jvm)
        implementation(testLibs.kotest.assertions.ktor)

        // Ktor
        implementation(testLibs.ktor.client.mock)
      }
    }
    create<JvmTestSuite>("testIntegration") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation(testLibs.kotest.runner.junit5.jvm)
        implementation(testLibs.kotest.assertions.core.jvm)
        implementation(testLibs.kotest.assertions.ktor)

        // Ktor
        implementation(testLibs.ktor.client.mock)
      }
    }
  }
}
