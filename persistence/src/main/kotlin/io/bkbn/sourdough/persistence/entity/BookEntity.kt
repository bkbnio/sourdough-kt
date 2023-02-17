package io.bkbn.sourdough.persistence.entity

import kotlinx.datetime.LocalDateTime
import org.komapper.annotation.KomapperCreatedAt
import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperTable
import org.komapper.annotation.KomapperUpdatedAt
import org.komapper.annotation.KomapperVersion
import java.util.UUID

//@Suppress("MagicNumber")
//object BookTable : UUIDTable("book") {
//  val author = reference("author_id", AuthorTable)
//  val isbn = varchar("isbn", 13)
//  val title = varchar("title", 255)
//  val price = float("price")
//  val createdAt = timestamp("created_at").default(Clock.System.now())
//  val updatedAt = timestamp("updated_at").default(Clock.System.now())
//}
//
//class BookEntity(id: EntityID<UUID>) : UUIDEntity(id) {
//  var author by AuthorEntity referencedOn BookTable.author
//  var isbn by BookTable.isbn
//  var title by BookTable.title
//  var price by BookTable.price
//
//  companion object : UUIDEntityClass<BookEntity>(BookTable)
//
//  fun toBook(): Book = Book(
//    id = this.id.value,
//    author = this.author.toAuthor(),
//    isbn = this.isbn,
//    title = this.title,
//    price = this.price
//  )
//}

@KomapperEntity(aliases = ["book"])
@KomapperTable(name = "book")
data class BookEntity(
  @KomapperId
  val id: UUID = UUID.randomUUID(),
  val authorId: UUID,
  val name: String,
  @KomapperVersion
  val version: Int = 0,
  @KomapperCreatedAt
  val createdAt: LocalDateTime? = null,
  @KomapperUpdatedAt
  val updatedAt: LocalDateTime? = null,
)
