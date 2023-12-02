package sk.momosilabs.recharger.server.service.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthTmp
import sk.momosilabs.recharger.server.service.charging.model.Charging
import sk.momosilabs.recharger.server.service.charging.model.CurrentType
import java.math.BigDecimal
import java.util.UUID

interface ChargingPersistence {

    fun list(vehicleUuid: UUID, pageable: Pageable): Page<Charging>

    fun listPerMonth(vehicleUuid: UUID, pageable: Pageable): Page<ChargingMonthTmp>

//    fun getPercentageToKwhRatios(vehicleUuid: UUID): Map<CurrentType?, BigDecimal>

}
