plugins {
  application
}

dependencies {
  implementation(projects.lib)
  implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-cli-jvm", version = "0.3.4")
}

application {
  mainClass.set("io.bkbn.sourdough.cli.EntrypointKt")
}
