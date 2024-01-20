package sk.momosilabs.recharger.server.service.charging.getChargingMonthList

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.charging.ChargingPersistence
import sk.momosilabs.recharger.server.service.charging.model.ChargingMonth
import sk.momosilabs.recharger.server.service.commonCalculation.calculateConsumption
import java.util.UUID

@Service
open class GetChargingMonthList(
    private val persistence: ChargingPersistence,
) : GetChargingMonthListUseCase {

    companion object {
        private val defaultSort = Sort.by(listOf(
            Sort.Order.desc("year"),
            Sort.Order.desc("month")
        ))
    }

    @Transactional(readOnly = true)
    override fun getList(vehicleUuid: UUID, pageable: Pageable): Page<ChargingMonth> {
        val fromNewest = PageRequest.of(pageable.pageNumber, pageable.pageSize, defaultSort)
        val list = persistence.listPerMonth(vehicleUuid, fromNewest)

        return list.map {
            ChargingMonth(
                year = it.year,
                month = it.month,
                mileageDriven = it.distanceDriven,
                averageConsumption = calculateConsumption(
                    percentageSpentTotal = it.percentageSpent,
                    kwhPercentageRatio = it.kwhPercentageRatio,
                    distanceDriven = it.distanceDriven,
                ),
                priceTotal = it.priceTotal,
                chargingCount = it.chargingCount,
            )
        }
    }

}
