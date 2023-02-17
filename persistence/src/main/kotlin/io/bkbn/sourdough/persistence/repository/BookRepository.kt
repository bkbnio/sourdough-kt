package io.bkbn.sourdough.persistence.repository

import io.bkbn.sourdough.persistence.ConnectionManager
import io.bkbn.sourdough.persistence.entity.BookEntity
import io.bkbn.sourdough.persistence.entity.book
import kotlinx.datetime.Clock
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.andThen
import org.komapper.core.dsl.query.single
import java.util.UUID

object BookRepository {

  private val db = ConnectionManager.database
  private val resource = Meta.book

  data class BookCreate(
    val authorId: UUID,
    val isbn: String,
    val title: String,
    val price: Float,
  )

  suspend fun create(
    authorId: UUID,
    isbn: String,
    title: String,
    price: Float,
  ) = db.withTransaction {
    db.runQuery {
      QueryDsl.insert(resource).single(
        BookEntity(
          authorId = authorId,
          isbn = isbn,
          title = title,
          price = price,
        )
      )
    }
  }.toBook()

  suspend fun createMany(requests: List<BookCreate>) = db.withTransaction {
    db.runQuery {
      QueryDsl.insert(resource).multiple(
        requests.map {
          BookEntity(
            authorId = it.authorId,
            isbn = it.isbn,
            title = it.title,
            price = it.price,
          )
        }
      )
    }
  }.map { it.toBook() }

  suspend fun read(id: UUID) = db.withTransaction {
    val result = db.runQuery {
      val query = QueryDsl.from(resource).where { resource.id eq id }
      query.single()
    }
    result.toBook()
  }

  suspend fun update(id: UUID, authorId: UUID?, isbn: String?, title: String?, price: Float?) = db.withTransaction {
    db.runQuery {
      QueryDsl.update(resource)
        .set {entity ->
          authorId?.let { entity.authorId to it }
          isbn?.let { entity.isbn to it }
          title?.let { entity.title to it }
          price?.let { entity.price to it }
          entity.updatedAt to Clock.System.now()
        }
        .where {
          resource.id eq id
        }
        .andThen(
          QueryDsl.from(resource)
            .where {
              resource.id eq id
            }.single()
        )
    }
  }.toBook()

  suspend fun delete(id: UUID) = db.withTransaction {
    db.runQuery {
      QueryDsl.delete(resource)
        .where {
          resource.id eq id
        }
    }
  }
}
