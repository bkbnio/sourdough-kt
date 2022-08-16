package io.bkbn.sourdough.api.controller

import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.sourdough.api.model.HealthCheckModels
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

object HealthCheckController {
  fun Route.healthCheckHandler() {
    route("/") {
      documentation()
      get {
        call.respond(status = HttpStatusCode.OK, HealthCheckModels.Status())
      }
    }
  }

  private fun Route.documentation() {
    install(NotarizedRoute()) {
      tags = setOf("Util")
      get = GetInfo.builder {
        summary("Health Check")
        description("Used predominantly for automated health checks")
        response {
          responseCode(HttpStatusCode.OK)
          responseType<HealthCheckModels.Status>()
          description("Indicates that server is up and running")
        }
      }
    }
  }
}
