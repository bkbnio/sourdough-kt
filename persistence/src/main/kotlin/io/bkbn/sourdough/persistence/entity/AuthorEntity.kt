package io.bkbn.sourdough.persistence.entity

import kotlinx.datetime.LocalDateTime
import org.komapper.annotation.KomapperCreatedAt
import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperTable
import org.komapper.annotation.KomapperUpdatedAt
import org.komapper.annotation.KomapperVersion
import java.util.UUID

@KomapperEntity(aliases = ["author"])
@KomapperTable(name = "author")
data class AuthorEntity(
  @KomapperId
  val id: UUID = UUID.randomUUID(),
  val name: String,
  @KomapperVersion
  val version: Int = 0,
  @KomapperCreatedAt
  val createdAt: LocalDateTime? = null,
  @KomapperUpdatedAt
  val updatedAt: LocalDateTime? = null,
)
