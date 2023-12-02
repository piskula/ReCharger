package sk.momosilabs.recharger.server.service.vehicle.model

import java.util.UUID

data class VehicleDetail(
    val id: Long,
    val uuid: UUID,
    val name: String,
    val mileageInitial: Int,
    val percentageInitial: Int,
)
