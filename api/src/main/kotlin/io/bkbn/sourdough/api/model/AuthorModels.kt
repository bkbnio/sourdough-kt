package io.bkbn.sourdough.api.model

import io.bkbn.sourdough.api.serializer.UuidSerializer
import io.bkbn.sourdough.domain.Author
import kotlinx.serialization.Serializable
import java.util.UUID

object AuthorModels {

  @Serializable
  data class CreateRequest(
    val name: String
  )

  @Serializable
  data class UpdateRequest(
    val name: String?
  )

  @Serializable
  data class Response(
    @Serializable(with = UuidSerializer::class)
    val id: UUID,
    val name: String
  ) {
    companion object {
      fun fromAuthor(a: Author): Response = Response(
        a.id,
        a.name
      )
    }
  }

}
