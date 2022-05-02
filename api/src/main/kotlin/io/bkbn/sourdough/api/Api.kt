package io.bkbn.sourdough.api

import io.bkbn.kompendium.core.Kompendium
import io.bkbn.kompendium.core.routes.redoc
import io.bkbn.kompendium.oas.OpenApiSpec
import io.bkbn.kompendium.oas.info.Info
import io.bkbn.kompendium.oas.schema.FormattedSchema
import io.bkbn.kompendium.oas.server.Server
import io.bkbn.sourdough.api.controller.AuthorController.authorRoute
import io.bkbn.sourdough.api.controller.BookController.bookRoute
import io.bkbn.sourdough.api.controller.HealthCheckController.healthRoute
import io.bkbn.sourdough.persistence.ConnectionManager
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import java.net.URI
import java.time.LocalDateTime

fun main() {
  // Perform Database Migrations
  ConnectionManager.cleanMigrations() // FIXME DO NOT LEAVE THIS IN PRODUCTION SETTING!
  ConnectionManager.performMigrations()

  // Activate database connection
  ConnectionManager.activateDatabaseConnection()

  // Start webserver
  embeddedServer(
    Netty,
    port = 8080,
    module = {
      install(ContentNegotiation) {
        json(Json {
          prettyPrint = true
          explicitNulls = false
          encodeDefaults = true
        })
      }
      install(Kompendium) {
        addCustomTypeSchema(
          LocalDateTime::class, FormattedSchema(
            format = "date-time", type =
            "string"
          )
        )
        spec = OpenApiSpec(
          info = Info(
            title = "Sourdough API",
            version = "0.0.1",
            description = "A delicious starter for your API",
            termsOfService = URI("https://gph.is/g/ZPJ1yyX"),
          ),
          servers = mutableListOf(Server(url = URI("/"), description = "This server"))
        )
      }
      routing {
        redoc(pageTitle = "Sourdough Docs")
        healthRoute()
        authorRoute()
        bookRoute()
      }
    }
  ).start(wait = true)
}
