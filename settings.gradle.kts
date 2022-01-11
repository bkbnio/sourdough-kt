rootProject.name = "sourdough-kt"

include("app")
include("api")
include("lib")
include("cli")

// Feature Previews
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Plugin Repositories
pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenLocal()
  }
}
