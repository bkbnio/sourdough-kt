package io.bkbn.sourdough.persistence.repository

import io.bkbn.sourdough.domain.Author
import io.bkbn.sourdough.persistence.entity.AuthorEntity
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object AuthorRepository {

  fun create(name: String): Author = transaction {
    val entity = AuthorEntity.new {
      this.name = name
    }
    entity.toAuthor()
  }

  fun createMany(names: List<String>): List<Author> = transaction {
    names.map { name ->
      AuthorEntity.new {
        this.name = name
      }.toAuthor()
    }
  }

  fun read(id: UUID): Author = transaction {
    val entity = AuthorEntity.findById(id) ?: error("No author found with id: $id")
    entity.toAuthor()
  }

  fun update(id: UUID, name: String?): Author = transaction {
    val entity = AuthorEntity.findById(id) ?: error("No author found with id: $id")
    name?.let {
      entity.name = it
    }
    entity.toAuthor()
  }

  fun delete(id: UUID) = transaction {
    val entity = AuthorEntity.findById(id) ?: error("No author found with id: $id")
    entity.delete()
  }
}
