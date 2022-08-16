package io.bkbn.sourdough.api.controller

import io.bkbn.kompendium.core.metadata.DeleteInfo
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.metadata.MethodInfo
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.metadata.PutInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.bkbn.sourdough.api.model.BookModels
import io.bkbn.sourdough.api.model.ExceptionModels
import io.bkbn.sourdough.api.service.BookService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.install
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

  private fun Route.rootDocumentation() {
    install(NotarizedRoute()) {
      tags = setOf("Book")
      post = PostInfo.builder {
        summary("Create Book")
        description("Will persist the provided book entity")
        request {
          requestType<BookModels.CreateRequest>()
          description("Information required to persist a new book entity")
        }
        response {
          responseCode(HttpStatusCode.Created)
          responseType<BookModels.Response>()
          description("Indicates that the book was persisted successfully")
        }
      }
    }
  }

  private fun Route.idDocumentation() {
    install(NotarizedRoute()) {
      tags = setOf("Book")
      parameters = listOf(
        Parameter(
          name = "id",
          `in` = Parameter.Location.path,
          schema = TypeDefinition.UUID
        )
      )
      get = GetInfo.builder {
        summary("Get Book by ID")
        description("Retrieves a book entity by it's ID or returns an error")
        response {
          responseCode(HttpStatusCode.OK)
          responseType<BookModels.Response>()
          description("The book was found successfully")
        }
        standardErrors()
      }
      put = PutInfo.builder {
        summary("Update Book by ID")
        description("Will update an existing book entity in place")
        request {
          requestType<BookModels.UpdateRequest>()
          description("Any provided non-null field will override the existing data")
        }
        response {
          responseCode(HttpStatusCode.Created)
          responseType<BookModels.Response>()
          description("The book was found successfully")
        }
        standardErrors()
      }
      delete = DeleteInfo.builder {
        summary("Delete Book by ID")
        description("Deletes the matching book entity")
        response {
          responseCode(HttpStatusCode.NoContent)
          responseType<Unit>()
          description("Indicates that the book was deleted successfully")
        }
        standardErrors()
      }
    }
  }

  private fun MethodInfo.Builder<*>.standardErrors() {
    canRespond {
      description("Indicates that the payload did not match the expected input")
      responseCode(HttpStatusCode.BadRequest)
      responseType<ExceptionModels.Standard>()
    }
    canRespond {
      description("The provided ID did not match any existing book entities")
      responseCode(HttpStatusCode.NotFound)
      responseType<ExceptionModels.Standard>()
    }
  }
}
