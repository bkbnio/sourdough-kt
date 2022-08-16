package io.bkbn.sourdough.api.controller

object AuthorController {

  /*
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
   */
}
