package io.bkbn.sourdough.api.controller

import io.bkbn.sourdough.api.documentation.BookDocumentation.idDocumentation
import io.bkbn.sourdough.api.documentation.BookDocumentation.rootDocumentation
import io.bkbn.sourdough.api.model.BookModels
import io.bkbn.sourdough.api.service.BookService
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

object BookController {
  fun Route.bookHandler() {
    route("/book") {
      rootDocumentation()
      post {
        val request = call.receive<BookModels.CreateRequest>()
        val result = BookService.create(request)
        call.respond(HttpStatusCode.Created, result)
      }
      route("/{id}") {
        idDocumentation()
        get {
          val id = UUID.fromString(call.parameters["id"])
          val result = BookService.read(id)
          call.respond(HttpStatusCode.OK, result)
        }
        put {
          val id = UUID.fromString(call.parameters["id"])
          val request = call.receive<BookModels.UpdateRequest>()
          val result = BookService.update(id, request)
          call.respond(HttpStatusCode.Created, result)
        }
        delete {
          val id = UUID.fromString(call.parameters["id"])
          BookService.delete(id)
          call.respond(HttpStatusCode.NoContent)
        }
      }
    }
  }
}
