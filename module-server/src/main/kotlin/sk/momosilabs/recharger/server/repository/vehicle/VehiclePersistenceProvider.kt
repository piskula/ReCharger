package sk.momosilabs.recharger.server.repository.vehicle

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.repository.charging.ChargingRepository
import sk.momosilabs.recharger.server.service.vehicle.VehiclePersistence
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle
import sk.momosilabs.recharger.server.service.vehicle.model.VehicleDetail
import java.util.UUID

@Repository
open class VehiclePersistenceProvider(
    private val vehicleRepository: VehicleRepository,
    private val chargingRepository: ChargingRepository,
) : VehiclePersistence {

    @Transactional(readOnly = true)
    override fun list(userIdentifier: String, pageable: Pageable): Page<Vehicle> =
        vehicleRepository.findAllByAccountProviderIdentifier(userIdentifier, pageable).toModel()

    @Transactional(readOnly = true)
    override fun getByUuid(vehicleUuid: UUID): VehicleDetail =
        vehicleRepository.findByUuid(vehicleUuid).toDetailModel()

    @Transactional(readOnly = true)
    override fun getCurrentMileagesForVehicles(vehicleIds: Set<Long>): Map<Long, Int> =
        chargingRepository.getCurrentMileagesForVehicles(vehicleIds)
            .associate { Pair(it.first, it.second) }

}
