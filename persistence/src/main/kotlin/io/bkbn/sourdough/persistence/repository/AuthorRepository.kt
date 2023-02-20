package io.bkbn.sourdough.persistence.repository

import io.bkbn.sourdough.domain.Author
import io.bkbn.sourdough.persistence.ConnectionManager
import io.bkbn.sourdough.persistence.entity.AuthorEntity
import io.bkbn.sourdough.persistence.entity.author
import io.bkbn.sourdough.persistence.entity.book
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.andThen
import org.komapper.core.dsl.query.single
import java.util.UUID

object AuthorRepository {
  private val AUTHOR = Meta.author
  private val BOOK = Meta.book

  private val db = ConnectionManager.database

  suspend fun create(name: String): Author = db.withTransaction {
    db.runQuery {
      QueryDsl.insert(AUTHOR).single(
        AuthorEntity(
          name = name
        )
      )
    }
  }.toAuthor()

  suspend fun createMany(names: List<String>): List<Author> = db.withTransaction {
    db.runQuery {
      QueryDsl.insert(AUTHOR).multiple(
        names.map {
          AuthorEntity(
            name = it
          )
        }
      )
    }
  }.map { it.toAuthor() }

  suspend fun read(id: UUID) = db.withTransaction {
    val result = db.runQuery {
      val query = QueryDsl.from(AUTHOR).where { AUTHOR.id eq id }
      query.single()
    }
    result.toAuthor()
  }

  suspend fun readBooks(authorId: UUID) = db.withTransaction {
    val query = QueryDsl.from(AUTHOR)
      .innerJoin(BOOK) {
        BOOK.authorId eq AUTHOR.id
      }
      .where {
        AUTHOR.id eq authorId
      }
      .includeAll()

    val store = db.runQuery(query)
    store[BOOK].toList()
  }.map { it.toBook() }

  suspend fun update(id: UUID, name: String?) = db.withTransaction {
    db.runQuery {
      QueryDsl.update(AUTHOR)
        .set {
          it.name to name
        }
        .where {
          AUTHOR.id eq id
        }
        .andThen(
          QueryDsl.from(AUTHOR)
            .where {
              AUTHOR.id eq id
            }.single()
        )
    }
  }.toAuthor()

  suspend fun delete(id: UUID) = db.withTransaction {
    db.runQuery {
      QueryDsl.delete(AUTHOR)
        .where {
          AUTHOR.id eq id
        }
    }
  }
}
