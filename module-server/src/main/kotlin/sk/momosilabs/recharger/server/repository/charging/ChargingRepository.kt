package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.charging.ChargingEntity
import sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthPercentageStatus
import sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthDbRetrieve
import sk.momosilabs.recharger.server.repository.charging.tmpModel.KwhPercentageRatio
import java.util.UUID

@Repository
interface ChargingRepository : JpaRepository<ChargingEntity, Long> {

    fun findAllByVehicleUuid(vehicleUuid: UUID, pageable: Pageable): Page<ChargingEntity>

    @Query("""
        SELECT new kotlin.Pair(charging.vehicle.id, MAX(charging.mileage))
        FROM #{#entityName} charging
        WHERE charging.vehicle.id IN :vehicleIds
        GROUP BY charging.vehicle.id
    """)
    fun getCurrentMileagesForVehicles(vehicleIds: Set<Long>): List<Pair<Long, Int>>

    @Query("""
        SELECT
            charging.currentType AS currentType,
            SUM(charging.kwh) AS kwhTotal,
            SUM(charging.percentageTo - charging.percentageFrom) AS percentageTotal
        FROM #{#entityName} charging
        WHERE charging.kwh IS NOT NULL AND charging.currentType IS NOT NULL AND charging.vehicle.uuid = :vehicleUuid
        GROUP BY currentType
    """)
    fun getKwhToPercentageConstants(vehicleUuid: UUID): List<KwhPercentageRatio>

    @Query("""
        SELECT
            YEAR(time) AS year,
            MONTH(time) AS month,
            MAX(mileage) AS mileageMax,
            SUM(percentage_to - percentage_from) AS percentageSpent,
            SUM(IF(kwh IS NOT NULL, (percentage_to - percentage_from), 0)) AS percentageSpentWhenKwhKnown,
            COALESCE(SUM(kwh), 0) AS kwhKnown,
            SUM(price) AS priceTotal,
            COUNT(*) AS chargingCount
        FROM #{#entityName} charging
        WHERE vehicle_id = :vehicleId
        GROUP BY YEAR(time), MONTH(time)
    """,
        countQuery = "SELECT COUNT(*) FROM #{#entityName} charging WHERE vehicle_id = :vehicleId GROUP BY YEAR(time), MONTH(time)",
        nativeQuery = true,
    )
    fun listChargingOverviewPerMonth(vehicleId: Long, pageable: Pageable): Page<ChargingMonthDbRetrieve>

    @Query("""
        SELECT new sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthPercentageStatus(
            lastPercentageStatus.year,
            lastPercentageStatus.month,
            lastPercentageStatus.percentageTo
        ) FROM (
            SELECT
                YEAR(time) AS year,
                MONTH(time) AS month,
                percentageTo AS percentageTo,
                ROW_NUMBER() OVER (PARTITION BY YEAR(time), MONTH(time) ORDER BY time DESC) AS rowIndex
            FROM #{#entityName}
            WHERE vehicle.uuid = :vehicleUuid
        ) AS lastPercentageStatus
        WHERE lastPercentageStatus.rowIndex = 1
    """)
    fun getPercentageStatusAtEnd(vehicleUuid: UUID): List<ChargingMonthPercentageStatus>

}
