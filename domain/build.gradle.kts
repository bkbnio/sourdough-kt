import kotlinx.kover.api.DefaultJacocoEngine

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
  id("org.jetbrains.dokka")
  id("maven-publish")
  id("java-library")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.4.2")
        implementation("io.kotest:kotest-assertions-core-jvm:5.4.2")
      }
    }
  }
}

kover {
  engine.set(DefaultJacocoEngine)
}
