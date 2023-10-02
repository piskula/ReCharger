package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.charging.ChargingPersistence
import sk.momosilabs.recharger.server.service.charging.model.Charging
import java.util.UUID

@Repository
open class ChargingPersistenceProvider(
    private val repository: ChargingRepository,
) : ChargingPersistence {

    @Transactional(readOnly = true)
    override fun list(vehicleUuid: UUID, pageable: Pageable): Page<Charging> =
        repository.findAllByVehicleUuid(vehicleUuid, pageable).toModel()

}
