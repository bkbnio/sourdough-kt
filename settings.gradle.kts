rootProject.name = "sourdough-kt"

include("app")
include("api")
include("cli")
include("client")
include("domain")
include("lib")
include("persistence")

// Feature Previews
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Plugin Repositories
pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenLocal()
  }
}
