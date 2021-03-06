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
import io.bkbn.sourdough.api.controller.BookController.TableOfContents.createBook
import io.bkbn.sourdough.api.controller.BookController.TableOfContents.deleteBook
import io.bkbn.sourdough.api.controller.BookController.TableOfContents.getBook
import io.bkbn.sourdough.api.controller.BookController.TableOfContents.updateBook
import io.bkbn.sourdough.api.model.BookModels
import io.bkbn.sourdough.api.model.ParameterModels
import io.bkbn.sourdough.api.service.BookService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.route
import java.util.UUID

object BookController {

  private object TableOfContents {
    val createBook = PostInfo<Unit, BookModels.CreateRequest, BookModels.Response>(
      summary = "Create new book",
      requestInfo = RequestInfo(
        description = "Metadata required for book model"
      ),
      responseInfo = ResponseInfo(
        HttpStatusCode.Created,
        description = "Book was successfully created"
      ),
      tags = setOf("Book")
    )

    val getBook = GetInfo<ParameterModels.GetById, BookModels.Response>(
      summary = "Get book by id",
      responseInfo = ResponseInfo(
        HttpStatusCode.OK,
        description = "Book was retrieved successfully"
      ),
      tags = setOf("Book")
    )

    val updateBook = PutInfo<ParameterModels.GetById, BookModels.UpdateRequest, BookModels.Response>(
      summary = "Update book",
      requestInfo = RequestInfo(
        description = "Fields that can be updated on an Book model"
      ),
      responseInfo = ResponseInfo(
        HttpStatusCode.Created,
        description = "Book was updated successfully"
      ),
      tags = setOf("Book")
    )

    val deleteBook = DeleteInfo<ParameterModels.GetById, Unit>(
      summary = "Deletes a book",
      responseInfo = ResponseInfo(
        status = HttpStatusCode.NoContent,
        description = "Book was successfully deleted"
      ),
      tags = setOf("Book")
    )
  }

  fun Route.bookRoute() {
    route("/book") {
      notarizedPost(createBook) {
        val request = call.receive<BookModels.CreateRequest>()
        val result = BookService.create(request)
        call.respond(HttpStatusCode.Created, result)
      }
      route("/{id}") {
        notarizedGet(getBook) {
          val id = UUID.fromString(call.parameters["id"])
          val result = BookService.read(id)
          call.respond(HttpStatusCode.OK, result)
        }
        notarizedPut(updateBook) {
          val id = UUID.fromString(call.parameters["id"])
          val request = call.receive<BookModels.UpdateRequest>()
          val result = BookService.update(id, request)
          call.respond(HttpStatusCode.Created, result)
        }
        notarizedDelete(deleteBook) {
          val id = UUID.fromString(call.parameters["id"])
          BookService.delete(id)
          call.respond(HttpStatusCode.NoContent)
        }
      }
    }
  }
}
