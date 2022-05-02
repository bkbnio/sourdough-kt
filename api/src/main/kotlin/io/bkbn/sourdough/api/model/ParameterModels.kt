package io.bkbn.sourdough.api.model

import io.bkbn.kompendium.annotations.Param
import io.bkbn.kompendium.annotations.ParamType
import io.bkbn.sourdough.api.serializer.UuidSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

object ParameterModels {

  @Serializable
  data class GetById(
    @Serializable(with = UuidSerializer::class)
    @Param(ParamType.PATH)
    val id: UUID
  )

}
