package io.bkbn.sourdough.persistence.repository

import io.bkbn.sourdough.domain.Author
import io.bkbn.sourdough.persistence.ConnectionManager
import io.bkbn.sourdough.persistence.entity.AuthorEntity
import io.bkbn.sourdough.persistence.entity.author
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.andThen
import org.komapper.core.dsl.query.single
import java.util.UUID

object AuthorRepository {
  private val db = ConnectionManager.database
  private val resource = Meta.author

  suspend fun create(name: String): Author = db.withTransaction {
    db.runQuery {
      QueryDsl.insert(resource).single(
        AuthorEntity(
          name = name
        )
      )
    }
  }.toAuthor()

  suspend fun createMany(names: List<String>): List<Author> = db.withTransaction {
    db.runQuery {
      QueryDsl.insert(resource).multiple(
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
      val query = QueryDsl.from(resource).where { resource.id eq id }
      query.single()
    }
    result.toAuthor()
  }

  suspend fun update(id: UUID, name: String?) = db.withTransaction {
    db.runQuery {
      QueryDsl.update(resource)
        .set {
          it.name to name
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
  }.toAuthor()

  suspend fun delete(id: UUID) = db.withTransaction {
    db.runQuery {
      QueryDsl.delete(resource)
        .where {
          resource.id eq id
        }
    }
  }
}
