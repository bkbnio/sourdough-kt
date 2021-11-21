plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
  gradlePluginPortal()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
  implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.19.0-RC2")
  implementation("com.adarshr:gradle-test-logger-plugin:3.1.0")
}
