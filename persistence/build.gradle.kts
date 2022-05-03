plugins {
  kotlin("jvm")
  id("io.bkbn.sourdough.library.jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("org.jetbrains.dokka")
}

dependencies {
  // Sourdough
  implementation(projects.domain)

  // Flyway
  implementation( "org.flywaydb:flyway-core:8.5.9")

  // Hikari
  implementation("com.zaxxer:HikariCP:5.0.1")

  // Driver
  implementation("org.postgresql:postgresql:42.3.4")

  // Exposed
  implementation("org.jetbrains.exposed:exposed-core:0.38.2")
  implementation("org.jetbrains.exposed:exposed-dao:0.38.2")
  implementation("org.jetbrains.exposed:exposed-jdbc:0.38.2")
  implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.38.2")
}


testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()
      dependencies {
        // Kotest
        implementation("io.kotest:kotest-runner-junit5-jvm:5.2.3")
        implementation("io.kotest:kotest-assertions-core-jvm:5.2.3")
      }
    }
  }
}
