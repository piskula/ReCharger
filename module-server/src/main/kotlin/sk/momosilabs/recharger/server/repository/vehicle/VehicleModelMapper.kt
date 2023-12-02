package sk.momosilabs.recharger.server.repository.vehicle

import org.springframework.data.domain.Page
import sk.momosilabs.recharger.server.entity.vehicle.VehicleEntity
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle
import sk.momosilabs.recharger.server.service.vehicle.model.VehicleDetail

fun Page<VehicleEntity>.toModel() = map {
    Vehicle(
        id = it.id,
        uuid = it.uuid,
        name = it.name,
        mileage = 0,
    )
}

fun VehicleEntity.toDetailModel() = VehicleDetail(
    id = id,
    uuid = uuid,
    name = name,
    mileageInitial = mileageInitial,
    percentageInitial = percentageInitial,
)
