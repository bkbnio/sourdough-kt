package io.bkbn.sourdough.api.controller

import io.bkbn.kompendium.core.Notarized.notarizedDelete
import io.bkbn.kompendium.core.Notarized.notarizedGet
import io.bkbn.kompendium.core.Notarized.notarizedPost
import io.bkbn.kompendium.core.Notarized.notarizedPut
import io.bkbn.kompendium.core.metadata.RequestInfo
import io.bkbn.kompendium.core.metadata.ResponseInfo
import io.bkbn.kompendium.core.metadata.method.DeleteInfo
import io.bkbn.kompendium.core.metadata.method.GetInfo
import io.bkbn.kompendium.core.metadata.method.PostInfo
import io.bkbn.kompendium.core.metadata.method.PutInfo
import io.bkbn.sourdough.api.controller.AuthorController.TableOfContents.createAuthor
import io.bkbn.sourdough.api.controller.AuthorController.TableOfContents.deleteAuthor
import io.bkbn.sourdough.api.controller.AuthorController.TableOfContents.getAuthor
import io.bkbn.sourdough.api.controller.AuthorController.TableOfContents.updateAuthor
import io.bkbn.sourdough.api.model.AuthorModels
import io.bkbn.sourdough.api.model.ParameterModels
import io.bkbn.sourdough.api.service.AuthorService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.route
import java.util.UUID

object AuthorController {

  private object TableOfContents {
    val createAuthor = PostInfo<Unit, AuthorModels.CreateRequest, AuthorModels.Response>(
      summary = "Creates new author",
      requestInfo = RequestInfo(
        description = "Metadata required for author model"
      ),
      responseInfo = ResponseInfo(
        HttpStatusCode.Created,
        description = "Author was successfully created"
      ),
      tags = setOf("Author")
    )

    val getAuthor = GetInfo<ParameterModels.GetById, AuthorModels.Response>(
      summary = "Get author by id",
      responseInfo = ResponseInfo(
        status = HttpStatusCode.OK,
        description = "Author was retrieved successfully"
      ),
      tags = setOf("Author")
    )

    val updateAuthor = PutInfo<ParameterModels.GetById, AuthorModels.UpdateRequest, AuthorModels.Response>(
      summary = "Update an author",
      requestInfo = RequestInfo(
        description = "Fields that can be updated on an author model"
      ),
      responseInfo = ResponseInfo(
        HttpStatusCode.Created,
        description = "Author was updated successfully"
      ),
      tags = setOf("Author")
    )

    val deleteAuthor = DeleteInfo<ParameterModels.GetById, Unit>(
      summary = "Deletes an author",
      responseInfo = ResponseInfo(
        status = HttpStatusCode.NoContent,
        description = "Author was successfully deleted"
      ),
      tags = setOf("Author")
    )
  }

  fun Route.authorRoute() {
    route("/author") {
      notarizedPost(createAuthor) {
        val request = call.receive<AuthorModels.CreateRequest>()
        val result = AuthorService.create(request)
        call.respond(HttpStatusCode.Created, result)
      }
      route("/{id}") {
        notarizedGet(getAuthor) {
          val id = UUID.fromString(call.parameters["id"])
          val result = AuthorService.read(id)
          call.respond(HttpStatusCode.OK, result)
        }
        notarizedPut(updateAuthor) {
          val id = UUID.fromString(call.parameters["id"])
          val request = call.receive<AuthorModels.UpdateRequest>()
          val result = AuthorService.update(id, request)
          call.respond(HttpStatusCode.Created, result)
        }
        notarizedDelete(deleteAuthor) {
          val id = UUID.fromString(call.parameters["id"])
          AuthorService.delete(id)
          call.respond(HttpStatusCode.NoContent)
        }
      }
    }
  }
}
