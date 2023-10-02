package sk.momosilabs.recharger.server.controller.vehicle

import sk.momosilabs.recharger.api.model.vehicle.VehicleDTO
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle

fun Vehicle.toDto() = VehicleDTO(
    uuid = uuid,
    name = name,
    mileage = mileage,
)
