plugins {
  kotlin("jvm") version "1.6.10"
  kotlin("plugin.serialization") version "1.6.10"
  id("io.bkbn.sourdough.library.jvm") version "0.5.5"
  id("io.gitlab.arturbosch.detekt") version "1.19.0"
  id("com.adarshr.test-logger") version "3.1.0"
  id("org.jetbrains.dokka")
  id("maven-publish")
  id("java-library")
}

// Change Me 🍞
sourdough {
  githubOrg.set("bkbnio")
  githubRepo.set("sourdough-kt")
  libraryName.set("Sourdough")
  libraryDescription.set("Sourdough Library ❤️")
  licenseName.set("MIT License")
  licenseUrl.set("https://mit-license.org")
  developerId.set("unredundant")
  developerName.set("Ryan Brink")
  developerEmail.set("admin@bkbn.io")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.1.0")
        implementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
      }
    }
  }
}
