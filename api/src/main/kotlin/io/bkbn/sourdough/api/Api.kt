package io.bkbn.sourdough.api

import io.bkbn.sourdough.api.Config.defaultPort
import io.bkbn.sourdough.api.Config.myDesiredNumberOfHeys
import io.bkbn.sourdough.lib.SourdoughLibrary
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

private object Config {
  const val myDesiredNumberOfHeys = 5
  const val defaultPort = 8080
}

fun main() {
  embeddedServer(
    Netty,
    port = defaultPort,
    module = {
      routing {
        route("/") {
          get {
            call.respond(HttpStatusCode.OK, SourdoughLibrary.coolFeature(myDesiredNumberOfHeys))
          }
        }
      }
    }
  ).start(wait = true)
}
