package io.bkbn.sourdough.persistence.entity

import io.bkbn.sourdough.domain.Book
import io.bkbn.sourdough.persistence.repository.AuthorRepository
import kotlinx.datetime.Instant
import org.komapper.annotation.KomapperCreatedAt
import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperTable
import org.komapper.annotation.KomapperUpdatedAt
import org.komapper.annotation.KomapperVersion
import java.util.UUID

@KomapperEntity(aliases = ["book"])
@KomapperTable(name = "book")
data class BookEntity(
  @KomapperId
  val id: UUID = UUID.randomUUID(),
  val authorId: UUID,
  val isbn: String,
  val title: String,
  val price: Float,
  @KomapperVersion
  val version: Int = 0,
  @KomapperCreatedAt
  val createdAt: Instant? = null,
  @KomapperUpdatedAt
  val updatedAt: Instant? = null,
) {
  suspend fun toBook(): Book = Book(
    id = id,
    author = AuthorRepository.read(authorId),
    isbn = isbn,
    title = title,
    price = price,
  )
}
