package sk.momosilabs.recharger.server.service.vehicle

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle

interface VehiclePersistence {

    fun list(pageable: Pageable): Page<Vehicle>

    fun getCurrentMileagesForVehicles(vehicleIds: Set<Long>): Map<Long, Int>

}
