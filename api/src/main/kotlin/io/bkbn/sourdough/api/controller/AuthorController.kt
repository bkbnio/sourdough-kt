package io.bkbn.sourdough.api.controller

import io.bkbn.sourdough.api.documentation.AuthorDocumentation.booksDocumentation
import io.bkbn.sourdough.api.documentation.AuthorDocumentation.idDocumentation
import io.bkbn.sourdough.api.documentation.AuthorDocumentation.rootDocumentation
import io.bkbn.sourdough.api.model.AuthorModels
import io.bkbn.sourdough.api.service.AuthorService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import java.util.UUID

object AuthorController {

  fun Route.authorHandler() {
    route("/author") {
      rootDocumentation()
      post {
        val request = call.receive<AuthorModels.CreateRequest>()
        val result = AuthorService.create(request)
        call.respond(HttpStatusCode.Created, result)
      }
      route("/{id}") {
        idDocumentation()
        get {
          val id = UUID.fromString(call.parameters["id"])
          val result = AuthorService.read(id)
          call.respond(HttpStatusCode.OK, result)
        }
        put {
          val id = UUID.fromString(call.parameters["id"])
          val request = call.receive<AuthorModels.UpdateRequest>()
          val result = AuthorService.update(id, request)
          call.respond(HttpStatusCode.Created, result)
        }
        delete {
          val id = UUID.fromString(call.parameters["id"])
          AuthorService.delete(id)
          call.respond(HttpStatusCode.NoContent)
        }
        route("/books") {
          booksDocumentation()
          get {
            val id = UUID.fromString(call.parameters["id"])
            val result = AuthorService.readBooks(id)
            call.respond(HttpStatusCode.OK, result)
          }
        }
      }
    }
  }
}
