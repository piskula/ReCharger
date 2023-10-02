package sk.momosilabs.recharger.api.model.vehicle

import java.util.UUID

data class VehicleDTO(
    val uuid: UUID,
    val name: String,
    val mileage: Int,
)
