package sk.momosilabs.recharger.server.service.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.charging.model.Charging

interface ChargingPersistence {

    fun list(vehicleId: Long, pageable: Pageable): Page<Charging>

}
