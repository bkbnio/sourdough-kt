package io.bkbn.sourdough.persistence.repository

import io.bkbn.sourdough.domain.Book
import io.bkbn.sourdough.persistence.entity.AuthorEntity
import io.bkbn.sourdough.persistence.entity.BookEntity
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object BookRepository {

  fun create(
    authorId: UUID,
    isbn: String,
    title: String,
    price: Float,
  ): Book = transaction {
    val entity = BookEntity.new {
      this.author = AuthorEntity.findById(authorId) ?: error("No author found with id: $authorId")
      this.isbn = isbn
      this.title = title
      this.price = price
    }
    entity.toBook()
  }

  fun read(id: UUID): Book = transaction {
    val entity = BookEntity.findById(id) ?: error("No book found with id: $id")
    entity.toBook()
  }

  fun update(
    id: UUID,
    authorId: UUID?,
    isbn: String?,
    title: String?,
    price: Float?,
  ): Book = transaction {
    val entity = BookEntity.findById(id) ?: error("No book found with id: $id")
    authorId?.let {
      entity.author = AuthorEntity.findById(it) ?: error("No author found with id: $it")
    }
    isbn?.let {
      entity.isbn = it
    }
    title?.let {
      entity.title = it
    }
    price?.let {
      entity.price = it
    }
    entity.toBook()
  }

  fun delete(id: UUID) = transaction {
    val entity = BookEntity.findById(id) ?: error("No book found with id: $id")
    entity.delete()
  }

}
