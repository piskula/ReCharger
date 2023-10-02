package sk.momosilabs.recharger.server.service.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.charging.model.Charging
import java.util.UUID

interface ChargingPersistence {

    fun list(vehicleUuid: UUID, pageable: Pageable): Page<Charging>

}
