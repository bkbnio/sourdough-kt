plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(logs.bundles.default)
  detektPlugins(misc.detekt.formatting)
}

application {
  mainClass.set("io.bkbn.sourdough.app.AppKt")
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
