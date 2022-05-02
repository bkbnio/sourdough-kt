package io.bkbn.sourdough.api.service

import io.bkbn.sourdough.api.model.AuthorModels
import io.bkbn.sourdough.persistence.repository.AuthorRepository
import java.util.UUID

object AuthorService {

  fun create(request: AuthorModels.CreateRequest): AuthorModels.Response {
    val result = AuthorRepository.create(request.name)
    return AuthorModels.Response.fromAuthor(result)
  }

  fun read(id: UUID): AuthorModels.Response {
    val result = AuthorRepository.read(id)
    return AuthorModels.Response.fromAuthor(result)
  }

  fun update(id: UUID, request: AuthorModels.UpdateRequest): AuthorModels.Response {
    val result = AuthorRepository.update(id, request.name)
    return AuthorModels.Response.fromAuthor(result)
  }

  fun delete(id: UUID) {
    AuthorRepository.delete(id)
  }

}
