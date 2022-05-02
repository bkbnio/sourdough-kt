package io.bkbn.sourdough.api.controller

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

object HealthCheckController {
  fun Route.healthRoute() {
    route("/") {
      get {
        call.respondText(status = HttpStatusCode.OK) { "Alive" }
      }
    }
  }
}
