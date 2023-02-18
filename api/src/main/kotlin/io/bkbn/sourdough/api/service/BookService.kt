package io.bkbn.sourdough.api.service

import io.bkbn.sourdough.api.model.BookModels
import io.bkbn.sourdough.persistence.repository.BookRepository
import java.util.UUID

object BookService {

  fun create(request: BookModels.CreateRequest): BookModels.Response {
    val result = BookRepository.create(
      authorId = request.authorId,
      isbn = request.isbn,
      title = request.title,
      price = request.price
    )
    return BookModels.Response.fromBook(result)
  }

  fun read(id: UUID): BookModels.Response {
    val result = BookRepository.read(id)
    return BookModels.Response.fromBook(result)
  }

  fun update(id: UUID, request: BookModels.UpdateRequest): BookModels.Response {
    val result = BookRepository.update(
      id = id,
      authorId = request.authorId,
      isbn = request.isbn,
      title = request.title,
      price = request.price
    )
    return BookModels.Response.fromBook(result)
  }

  fun delete(id: UUID) {
    BookRepository.delete(id)
  }

}
