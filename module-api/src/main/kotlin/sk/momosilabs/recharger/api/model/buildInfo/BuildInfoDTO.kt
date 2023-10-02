package sk.momosilabs.recharger.api.model.buildInfo

import java.time.ZonedDateTime

data class BuildInfoDTO(
    val version: String,
    val name: String,
    val time: ZonedDateTime,
)
