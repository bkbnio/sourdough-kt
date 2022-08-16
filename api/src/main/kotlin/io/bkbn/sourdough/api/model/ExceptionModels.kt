package io.bkbn.sourdough.api.model

import kotlinx.serialization.Serializable

object ExceptionModels {
  @Serializable
  data class Standard(val message: String)
}
