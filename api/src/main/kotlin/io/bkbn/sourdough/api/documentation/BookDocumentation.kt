package io.bkbn.sourdough.api.documentation

import io.bkbn.kompendium.core.metadata.DeleteInfo
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.metadata.PutInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.bkbn.sourdough.api.documentation.DocumentationUtils.standardErrors
import io.bkbn.sourdough.api.model.BookModels
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.install
import io.ktor.server.routing.Route

object BookDocumentation {
  fun Route.rootDocumentation() {
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

  fun Route.idDocumentation() {
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
}
