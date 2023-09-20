package io.bkbn.sourdough.api.documentation

import io.bkbn.kompendium.core.metadata.DeleteInfo
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.metadata.PutInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.bkbn.sourdough.api.documentation.DocumentationUtils.standardErrors
import io.bkbn.sourdough.api.model.AuthorModels
import io.bkbn.sourdough.api.model.BookModels
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.install
import io.ktor.server.routing.Route

object AuthorDocumentation {
  fun Route.rootDocumentation() {
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

  fun Route.idDocumentation() {
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

  fun Route.booksDocumentation() {
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
}
