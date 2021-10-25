plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(logs.bundles.default)
  testImplementation(test.bundles.unit)
  detektPlugins(misc.detekt.formatting)
}

application {
  mainClass.set("io.bkbn.sourdough.app.AppKt")
}

//testing {
//  suites {
//    named<JvmTestSuite>("test") {
//      useJUnitJupiter()
//
//      dependencies {
//        implementation(test.bundles.unit)
//      }
//    }
//    create<JvmTestSuite>("testIntegration") {
//      useJUnitJupiter()
//    }
//  }
//}
