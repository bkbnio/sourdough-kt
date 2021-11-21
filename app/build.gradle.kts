plugins {
  id("kotlin-application-conventions")
}

dependencies {
  implementation(projects.lib)
}

application {
  mainClass.set("io.bkbn.sourdough.app.AppKt")
}
