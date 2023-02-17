package io.bkbn.sourdough.api.model

import io.bkbn.sourdough.api.serializer.UuidSerializer
import io.bkbn.sourdough.domain.Book
import kotlinx.serialization.Serializable
import java.util.UUID

object BookModels {

  @Serializable
  data class CreateRequest(
    @Serializable(with = UuidSerializer::class)
    val authorId: UUID,
    val isbn: String,
    val title: String,
    val price: Float
  )

  @Serializable
  data class UpdateRequest(
    @Serializable(with = UuidSerializer::class)
    val authorId: UUID? = null,
    val isbn: String? = null,
    val title: String? = null,
    val price: Float? = null
  )

  @Serializable
  data class Response(
    @Serializable(with = UuidSerializer::class)
    val id: UUID,
    @Serializable(with = UuidSerializer::class)
    val author: UUID,
    val isbn: String,
    val title: String,
    val price: Float
  ) {
    companion object {
      fun fromBook(b: Book): Response = Response(
        b.id,
        b.authorId,
        b.isbn,
        b.title,
        b.price
      )
    }
  }

}
