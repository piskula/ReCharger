package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.charging.ChargingEntity
import sk.momosilabs.recharger.server.repository.charging.tmpModel.ChargingMonthDbRetrieve
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
            YEAR(time) AS year,
            MONTH(time) AS month,
            SUM(distance_driven) AS distanceDriven,
            SUM(percentage_spent) AS percentageSpent,
            SUM(kwh)/SUM(CASE WHEN kwh IS NULL THEN 0 ELSE (percentage_to - percentage_from) END) AS kwhPercentageRatio,
            SUM(price) AS priceTotal,
            COUNT(*) AS chargingCount
        FROM #{#entityName}
        WHERE vehicle_id = :vehicleId
        GROUP BY YEAR(time), MONTH(time)
    """,
        countQuery = "SELECT COUNT(*) FROM #{#entityName} charging WHERE vehicle_id = :vehicleId GROUP BY YEAR(time), MONTH(time)",
        nativeQuery = true,
    )
    fun listChargingOverviewPerMonth(vehicleId: Long, pageable: Pageable): Page<ChargingMonthDbRetrieve>

}
