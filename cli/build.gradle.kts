plugins {
  id("kotlin-application-conventions")
}

dependencies {
  implementation(projects.lib)
  implementation(ktx.cli)
}

application {
  mainClass.set("io.bkbn.sourdough.cli.EntrypointKt")
}
