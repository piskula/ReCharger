package sk.momosilabs.recharger.server.service.vehicle

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle
import sk.momosilabs.recharger.server.service.vehicle.model.VehicleDetail
import java.util.UUID

interface VehiclePersistence {

    fun list(userIdentifier: String, pageable: Pageable): Page<Vehicle>

    fun getByUuid(vehicleUuid: UUID): VehicleDetail

    fun getCurrentMileagesForVehicles(vehicleIds: Set<Long>): Map<Long, Int>

}
