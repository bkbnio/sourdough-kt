plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp")
  id("io.bkbn.sourdough.library.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.kotlinx.kover")
}

dependencies {
  // Versions
  val komapperVersion: String by project

  // Sourdough
  implementation(projects.domain)

  // Flyway
  implementation( "org.flywaydb:flyway-core:9.8.3")

  // Hikari
  implementation("com.zaxxer:HikariCP:5.0.1")

  // Datetime
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

  // Driver
  implementation("org.postgresql:postgresql:42.5.1")

  // ORM
  platform("org.komapper:komapper-platform:$komapperVersion").let {
    implementation(it)
    ksp(it)
  }
  implementation("org.komapper:komapper-starter-jdbc")
  implementation("org.komapper:komapper-dialect-postgresql-jdbc")
  implementation("org.komapper:komapper-datetime-jdbc:$komapperVersion")
  ksp("org.komapper:komapper-processor")
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
        implementation("io.kotest:kotest-runner-junit5-jvm:5.5.4")
        implementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
      }
    }
  }
}
