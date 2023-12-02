package sk.momosilabs.recharger.server.service.charging.getChargingMonthList

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthTmp
import sk.momosilabs.recharger.server.repository.charging.tmpModel.PreviousChargingMonthTmp
import sk.momosilabs.recharger.server.service.charging.ChargingPersistence
import sk.momosilabs.recharger.server.service.charging.model.ChargingMonth
import sk.momosilabs.recharger.server.service.charging.model.Month
import sk.momosilabs.recharger.server.service.vehicle.VehiclePersistence
import sk.momosilabs.recharger.server.service.vehicle.model.VehicleDetail
import java.math.BigDecimal
import java.util.UUID

@Service
open class GetChargingMonthList(
    private val persistence: ChargingPersistence,
    private val vehiclePersistence: VehiclePersistence,
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

        val nextMonthMeta = if (list.hasNext())
            persistence.listPerMonth(vehicleUuid, fromNewest.next()).first().onlyFollowingMonthRelatives()
        else
            vehiclePersistence.getByUuid(vehicleUuid).onlyFollowingMonthRelatives()

//        val ratios = persistence.getPercentageToKwhRatios(vehicleUuid)

        val calculatedList = list.zipWithNext { current, next -> ChargingMonth(
            year = current.year,
            month = current.month,
            mileageDriven = current.mileageMax - next.mileageMax,
            // TODO we need to change DB model to have percentage spent
            averageConsumption = BigDecimal.valueOf(current.percentageSpent.toLong()),
            priceTotal = current.priceTotal,
            chargingCount = current.chargingCount,
        ) }.plus(
            with(list.last()) {
                ChargingMonth(
                    year = year,
                    month = month,
                    mileageDriven = mileageMax - nextMonthMeta.mileageMax,
                    // TODO we need to change DB model to have percentage spent
                    averageConsumption = BigDecimal.valueOf(percentageSpent.toLong()),
                    priceTotal = priceTotal,
                    chargingCount = chargingCount,
                )
            }
        ).associateBy { Month(it.year, it.month) }

        return list.map { calculatedList[Month(it.year, it.month)] }
    }

    private fun ChargingMonthTmp.onlyFollowingMonthRelatives() = PreviousChargingMonthTmp(
        mileageMax = mileageMax,
        lastPercentageStatus = percentageStatusAtEnd,
    )

    private fun VehicleDetail.onlyFollowingMonthRelatives() = PreviousChargingMonthTmp(
        mileageMax = mileageInitial,
        lastPercentageStatus = percentageInitial,
    )

}
