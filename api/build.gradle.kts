plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(libs.bundles.logging)
  implementation(libs.bundles.api)
  testImplementation(libs.bundles.test)
  detektPlugins(libs.detekt.formatting)
}

application {
  mainClass.set("io.bkbn.sourdough.api.ApiKt")
}
