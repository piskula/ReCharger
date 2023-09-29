package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.charging.ChargingPersistence
import sk.momosilabs.recharger.server.service.charging.model.Charging

@Repository
open class ChargingPersistenceProvider(
    val repository: ChargingRepository,
) : ChargingPersistence {

    @Transactional(readOnly = true)
    override fun list(vehicleId: Long, pageable: Pageable): Page<Charging> =
        repository.findAllByVehicleId(vehicleId, pageable).toModel()

}
