plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(libs.bundles.logging)
  testImplementation(libs.bundles.test)
  detektPlugins(libs.detekt.formatting)
}

application {
  // Define the main class for the application.
  mainClass.set("io.bkbn.sourdough.app.AppKt")
}
