package io.bkbn.sourdough.api.controller

import io.bkbn.kompendium.core.metadata.DeleteInfo
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.metadata.MethodInfo
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.metadata.PutInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.bkbn.sourdough.api.model.AuthorModels
import io.bkbn.sourdough.api.model.BookModels
import io.bkbn.sourdough.api.model.ExceptionModels
import io.bkbn.sourdough.api.service.AuthorService
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

  private fun Route.rootDocumentation() {
    install(NotarizedRoute()) {
      tags = setOf("Author")
      post = PostInfo.builder {
        summary("Create Author")
        description("Will persist the provided author entity")
        request {
          requestType<AuthorModels.CreateRequest>()
          description("Information required to persist a new author entity")
        }
        response {
          responseCode(HttpStatusCode.Created)
          responseType<AuthorModels.Response>()
          description("Indicates that the author was persisted successfully")
        }
      }
    }
  }

  private fun Route.idDocumentation() {
    install(NotarizedRoute()) {
      tags = setOf("Author")
      parameters = listOf(
        Parameter(
          name = "id",
          `in` = Parameter.Location.path,
          schema = TypeDefinition.UUID
        )
      )
      get = GetInfo.builder {
        summary("Get Author by ID")
        description("Retrieves a author entity by it's ID or returns an error")
        response {
          responseCode(HttpStatusCode.OK)
          responseType<AuthorModels.Response>()
          description("The author was found successfully")
        }
        standardErrors()
      }
      put = PutInfo.builder {
        summary("Update Author by ID")
        description("Will update an existing author entity in place")
        request {
          requestType<AuthorModels.UpdateRequest>()
          description("Any provided non-null field will override the existing data")
        }
        response {
          responseCode(HttpStatusCode.Created)
          responseType<AuthorModels.Response>()
          description("The author was found successfully")
        }
        standardErrors()
      }
      delete = DeleteInfo.builder {
        summary("Delete Author by ID")
        description("Deletes the matching author entity")
        response {
          responseCode(HttpStatusCode.NoContent)
          responseType<Unit>()
          description("Indicates that the author was deleted successfully")
        }
        standardErrors()
      }
    }
  }

  private fun Route.booksDocumentation() {
    install(NotarizedRoute()) {
      tags = setOf("Author")
      parameters = listOf(
        Parameter(
          name = "id",
          `in` = Parameter.Location.path,
          schema = TypeDefinition.UUID
        )
      )
      get = GetInfo.builder {
        summary("Get Author's Books by ID")
        description("Retrieves a list of books by the author's ID or returns an error")
        response {
          responseCode(HttpStatusCode.OK)
          responseType<List<BookModels.Response>>()
          description("The authors books")
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
      description("The provided ID did not match any existing author entities")
      responseCode(HttpStatusCode.NotFound)
      responseType<ExceptionModels.Standard>()
    }
  }
}
