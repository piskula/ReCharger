package sk.momosilabs.recharger.server.service.charging.model

import java.util.UUID

data class ProviderSimple(
    val uuid: UUID,
    val name: String,
)
