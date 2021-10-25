plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(logs.bundles.default)
  implementation(ktor.bundles.server)
  detektPlugins(misc.detekt.formatting)
}

application {
  mainClass.set("io.bkbn.sourdough.api.ApiKt")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()

      dependencies {
        implementation(test.bundles.unit)
      }
    }
    create<JvmTestSuite>("testIntegration") {
      useJUnitJupiter()

      dependencies {
        implementation(test.bundles.integration)
      }
    }
  }
}
