plugins {
  id("io.bkbn.sourdough.library")
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
        implementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
        implementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
      }
    }
  }
}
