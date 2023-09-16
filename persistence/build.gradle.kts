plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp")
  id("io.bkbn.sourdough.library.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
}

dependencies {
  // Sourdough
  implementation(projects.domain)

  // Flyway
  implementation(libs.flyway.core)

  // Datetime
  implementation(libs.ktx.datetime)

  // Driver
  implementation(libs.postgres.driver)

  // Pooling
  implementation(libs.r2dbc.pool)

  // ORM
  platform(libs.komapper.platform).let {
    implementation(it)
    ksp(it)
  }
  implementation(libs.bundles.komapper.r2dbc)
  ksp(libs.komapper.processor)
}

kotlin {
  sourceSets.main {
    kotlin.srcDir("build/generated/ksp/main/kotlin")
  }
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf("-opt-in=org.komapper.annotation.KomapperExperimentalAssociation")
  }
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation(testLibs.kotest.runner.junit5.jvm)
        implementation(testLibs.kotest.assertions.core.jvm)
      }
    }
  }
}
