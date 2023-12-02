package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthTmp
import sk.momosilabs.recharger.server.service.charging.ChargingPersistence
import sk.momosilabs.recharger.server.service.charging.model.Charging
import sk.momosilabs.recharger.server.service.charging.model.CurrentType
import sk.momosilabs.recharger.server.service.charging.model.Month
import sk.momosilabs.recharger.server.service.commonCalculation.divideSafe
import java.math.BigDecimal
import java.util.UUID

@Repository
open class ChargingPersistenceProvider(
    private val repository: ChargingRepository,
) : ChargingPersistence {

    @Transactional(readOnly = true)
    override fun list(vehicleUuid: UUID, pageable: Pageable): Page<Charging> =
        repository.findAllByVehicleUuid(vehicleUuid, pageable).toModel()

    @Transactional(readOnly = true)
    override fun listPerMonth(vehicleUuid: UUID, pageable: Pageable): Page<ChargingMonthTmp> {
        val list = repository.listChargingOverviewPerMonth(1L, pageable)
        val statusesAtEndOfMonth = repository.getPercentageStatusAtEnd(vehicleUuid)
            .associate { Pair(Month(it.year, it.month), it.percentageStatusAtEnd) }

        return list.map { charging ->
            ChargingMonthTmp(
                year = charging.year,
                month = charging.month,
                mileageMax = charging.mileageMax,
                percentageSpent = charging.percentageSpent,
//                percentageSpentWhenKwhKnown = charging.percentageSpentWhenKwhKnown,
//                kwhKnown = charging.kwhKnown,
                percentageStatusAtEnd = statusesAtEndOfMonth[Month(charging.year, charging.month)]!!,
                priceTotal = charging.priceTotal,
                chargingCount = charging.chargingCount,
            )
        }
    }

//    @Transactional(readOnly = true)
//    override fun getPercentageToKwhRatios(vehicleUuid: UUID): Map<CurrentType?, BigDecimal> {
//        val constants = repository.getKwhToPercentageConstants(vehicleUuid)
//        return constants.associate { Pair(it.currentType, it.kwhTotal.divideSafe(it.percentageTotal)) }
//            .plus(Pair(null, constants.sumOf { it.kwhTotal }.divideSafe(constants.sumOf { it.percentageTotal })))
//    }

}
