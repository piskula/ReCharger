package sk.momosilabs.recharger.server.service.charging.getChargingList

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.charging.model.Charging
import java.util.UUID

interface GetChargingListUseCase {

    fun getList(vehicleUuid: UUID, pageable: Pageable): Page<Charging>

}
