plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("io.bkbn.sourdough.library.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
  id("maven-publish")
  id("java-library")
}

sourdoughLibrary {
  githubOrg.set("bkbnio")
  githubRepo.set("sourdough-kt")
  libraryName.set("sourdough-domain")
  libraryDescription.set("Sourdough Domain ❤️")
  licenseName.set("MIT License")
  licenseUrl.set("https://mit-license.org")
  developerId.set("unredundant")
  developerName.set("Ryan Brink")
  developerEmail.set("admin@bkbn.io")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation(testLibs.kotest.runner.junit5.jvm)
        implementation(testLibs.kotest.assertions.core.jvm)
      }
    }
  }
}
