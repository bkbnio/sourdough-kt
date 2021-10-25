plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(logs.bundles.default)
  implementation(ktor.bundles.server)
  testImplementation(test.bundles.unit)
  detektPlugins(misc.detekt.formatting)
}

application {
  mainClass.set("io.bkbn.sourdough.api.ApiKt")
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
