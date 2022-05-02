package io.bkbn.sourdough.persistence.entity

import io.bkbn.sourdough.domain.Author
import kotlinx.datetime.Clock
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import java.util.UUID

object AuthorTable : UUIDTable("author") {
  val name = text("name")
  val createdAt = timestamp("created_at").default(Clock.System.now())
  val updatedAt = timestamp("updated_at").default(Clock.System.now())
}

class AuthorEntity(id: EntityID<UUID>) : UUIDEntity(id) {
  var name by AuthorTable.name
  val books by BookEntity referrersOn BookTable.author

  companion object : UUIDEntityClass<AuthorEntity>(AuthorTable)

  fun toAuthor(): Author = Author(
    id = this.id.value,
    name = this.name
  )
}
