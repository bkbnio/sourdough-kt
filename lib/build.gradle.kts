plugins {
  `java-library`
  `maven-publish`
  signing
}

dependencies {
  implementation(logs.bundles.default)
  testImplementation(test.bundles.unit)
  detektPlugins(misc.detekt.formatting)
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
