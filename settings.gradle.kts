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

// Plugin Management
pluginManagement {

  // Plugin Repositories
  repositories {
    gradlePluginPortal()
    mavenLocal()
  }

  // Plugin Resolution
  plugins {
    val gitHooksVersion = extra["git-hooks.version"] as String
    val kotlinVersion = extra["kotlin.version"] as String
    val koverVersion = extra["kover.version"] as String
    val kspVersion = extra["ksp.version"] as String
    val sourdoughVersion = extra["sourdough.version"] as String
    val testLoggerVersion = extra["test-logger.version"] as String

    // Kotlin
    kotlin("jvm").version(kotlinVersion)
    kotlin("plugin.serialization").version(kotlinVersion)
    id("com.google.devtools.ksp").version(kspVersion)
    id("org.jetbrains.kotlinx.kover").version(koverVersion)

    // Sourdough
    id("io.bkbn.sourdough.root").version(sourdoughVersion)
    id("io.bkbn.sourdough.library.jvm").version(sourdoughVersion)
    id("io.bkbn.sourdough.application.jvm").version(sourdoughVersion)

    // Misc
    id("com.github.jakemarsden.git-hooks").version(gitHooksVersion)
    id("com.adarshr.test-logger").version(testLoggerVersion)
  }
}

// Dependency Management
dependencyResolutionManagement {
  versionCatalogs {
    // Main Version Catalog
    create("libs") {
      val flywayVersion = extra["flyway.version"] as String
      val kermitVersion = extra["kermit.version"] as String
      val komapperVersion = extra["komapper.version"] as String
      val kompendiumVersion = extra["kompendium.version"] as String
      val ktorVersion = extra["ktor.version"] as String
      val ktxCliVersion = extra["ktx-cli.version"] as String
      val ktxCoroutinesVersion = extra["ktx-coroutines.version"] as String
      val ktxDatetime = extra["ktx-datetime.version"] as String
      val ktxSerializationVersion = extra["ktx-serialization.version"] as String
      val logbackClassicVersion = extra["logback-classic.version"] as String
      val postgresDriverVersion = extra["postgres-driver.version"] as String
      val r2dbcVersion = extra["r2dbc.version"] as String
      val satisfaketionVersion = extra["satisfaketion.version"] as String

      // Flyway
      library("flyway-core", "org.flywaydb", "flyway-core").version(flywayVersion)

      // Kermit
      library("kermit", "co.touchlab", "kermit").version(kermitVersion)

      // Komapper
      library("komapper-platform", "org.komapper", "komapper-platform").version(komapperVersion)
      library("komapper-starter-r2dbc", "org.komapper", "komapper-starter-r2dbc").version(komapperVersion)
      library("komapper-dialect-postgresql-r2dbc", "org.komapper", "komapper-dialect-postgresql-r2dbc").version(
        komapperVersion
      )
      library("komapper-datetime-r2dbc", "org.komapper", "komapper-datetime-r2dbc").version(komapperVersion)
      library("komapper-processor", "org.komapper", "komapper-processor").version(komapperVersion)
      bundle(
        "komapper-r2dbc",
        listOf("komapper-starter-r2dbc", "komapper-dialect-postgresql-r2dbc", "komapper-datetime-r2dbc")
      )

      // Kompendium
      library("kompendium-core", "io.bkbn", "kompendium-core").version(kompendiumVersion)

      // Kotlinx CLI
      library("ktx-cli", "org.jetbrains.kotlinx", "kotlinx-cli-jvm").version(ktxCliVersion)

      // Kotlinx Coroutines
      library("ktx-coroutines-core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version(
        ktxCoroutinesVersion
      )

      // Kotlinx Datetime
      library("ktx-datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").version(ktxDatetime)

      // Kotlinx Serialization
      library("ktx-serialization", "org.jetbrains.kotlinx", "kotlinx-serialization-json").version(
        ktxSerializationVersion
      )

      // Ktor Server
      library("ktor-server-core", "io.ktor", "ktor-server-core").version(ktorVersion)
      library("ktor-server-call-logging", "io.ktor", "ktor-server-call-logging").version(ktorVersion)
      library("ktor-server-cio", "io.ktor", "ktor-server-cio").version(ktorVersion)
      library("ktor-server-auth", "io.ktor", "ktor-server-auth").version(ktorVersion)
      library("ktor-server-auth-jwt", "io.ktor", "ktor-server-auth-jwt").version(ktorVersion)
      library("ktor-serialization", "io.ktor", "ktor-serialization").version(ktorVersion)
      library("ktor-server-status-pages", "io.ktor", "ktor-server-status-pages").version(ktorVersion)
      library("ktor-server-content-negotiation", "io.ktor", "ktor-server-content-negotiation").version(ktorVersion)
      library("ktor-serialization-kotlinx-json", "io.ktor", "ktor-serialization-kotlinx-json").version(ktorVersion)
      bundle(
        "ktor-server",
        listOf(
          "ktor-server-core",
          "ktor-server-call-logging",
          "ktor-server-cio",
          "ktor-server-auth",
          "ktor-server-auth-jwt",
          "ktor-serialization",
          "ktor-server-status-pages",
          "ktor-server-content-negotiation",
          "ktor-serialization-kotlinx-json",
          "kompendium-core",
        )
      )

      // Postgres Driver
      library("postgres-driver", "org.postgresql", "postgresql").version(postgresDriverVersion)

      // Logback
      library("logback-classic", "ch.qos.logback", "logback-classic").version(logbackClassicVersion)

      // R2DBC
      library("r2dbc-pool", "io.r2dbc", "r2dbc-pool").version(r2dbcVersion)

      // Satisfaketion
      library("satisfaketion-core", "io.github.unredundant", "satisfaketion-core").version(satisfaketionVersion)
      library("satisfaketion-generators", "io.github.unredundant", "satisfaketion-generators").version(
        satisfaketionVersion
      )
      library("satisfaketion-mutators", "io.github.unredundant", "satisfaketion-mutators").version(
        satisfaketionVersion
      )
      bundle("satisfaketion", listOf("satisfaketion-core", "satisfaketion-generators", "satisfaketion-mutators"))
    }

    // Test Version Catalog
    create("testLibs") {
      val kotestVersion = extra["kotest.version"] as String
      val kotestKtorAssertionsVersion = extra["kotest-ktor-assertions.version"] as String
      val ktorVersion = extra["ktor.version"] as String

      // Kotest
      library("kotest-runner-junit5-jvm", "io.kotest", "kotest-runner-junit5-jvm").version(kotestVersion)
      library("kotest-assertions-core-jvm", "io.kotest", "kotest-assertions-core-jvm").version(kotestVersion)
      library("kotest-assertions-ktor", "io.kotest", "kotest-assertions-ktor").version(kotestKtorAssertionsVersion)
      // TODO: Bundle errors out in test dependency block... gradle bug? 🤔
      bundle("kotest-core", listOf("kotest-runner-junit5-jvm", "kotest-assertions-core-jvm"))

      // Ktor
      library("ktor-client-mock", "io.ktor", "ktor-client-mock-jvm").version(ktorVersion)
    }
  }
}
