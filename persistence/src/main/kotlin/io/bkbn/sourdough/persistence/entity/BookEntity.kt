package io.bkbn.sourdough.persistence.entity

import io.bkbn.sourdough.domain.Book
import kotlinx.datetime.Clock
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import java.util.UUID

@Suppress("MagicNumber")
object BookTable : UUIDTable("book") {
  val author = reference("author_id", AuthorTable)
  val isbn = varchar("isbn", 13)
  val title = varchar("title", 255)
  val price = float("price")
  val createdAt = timestamp("created_at").default(Clock.System.now())
  val updatedAt = timestamp("updated_at").default(Clock.System.now())
}

class BookEntity(id: EntityID<UUID>) : UUIDEntity(id) {
  var author by AuthorEntity referencedOn BookTable.author
  var isbn by BookTable.isbn
  var title by BookTable.title
  var price by BookTable.price

  companion object : UUIDEntityClass<BookEntity>(BookTable)

  fun toBook(): Book = Book(
    id = this.id.value,
    author = this.author.toAuthor(),
    isbn = this.isbn,
    title = this.title,
    price = this.price
  )
}
