package sk.momosilabs.recharger.server.service.vehicle.model

import java.util.UUID

data class Vehicle(
    val id: Long,
    val uuid: UUID,
    val name: String,
    var mileage: Int,
)
