package sk.momosilabs.recharger.server.service.charging.getChargingMonthList

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.charging.model.ChargingMonth
import java.util.UUID

interface GetChargingMonthListUseCase {

    fun getList(vehicleUuid: UUID, pageable: Pageable): Page<ChargingMonth>

}
