package io.bkbn.sourdough.api.model

import kotlinx.serialization.Serializable

object HealthCheckModels {
  @Serializable
  data class Status(val alive: Boolean = true)
}
