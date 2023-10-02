package sk.momosilabs.recharger.server.repository.vehicle

import org.springframework.data.domain.Page
import sk.momosilabs.recharger.server.entity.vehicle.VehicleEntity
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle

fun Page<VehicleEntity>.toModel() = map {
    Vehicle(
        id = it.id,
        uuid = it.uuid,
        name = it.name,
        mileage = it.mileage,
    )
}
