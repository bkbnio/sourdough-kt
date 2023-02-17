package io.bkbn.sourdough.domain

import java.util.UUID

data class Book(
  val id: UUID,
  val authorId: UUID,
  val isbn: String,
  val title: String,
  val price: Float
)
