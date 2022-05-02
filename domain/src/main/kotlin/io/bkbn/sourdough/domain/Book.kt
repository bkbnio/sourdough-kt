package io.bkbn.sourdough.domain

import java.util.UUID

data class Book(
  val id: UUID,
  val author: Author,
  val isbn: String,
  val title: String,
  val price: Float
)
