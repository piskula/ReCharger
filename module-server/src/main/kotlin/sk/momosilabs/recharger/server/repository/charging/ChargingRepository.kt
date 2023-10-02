package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.charging.ChargingEntity
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

}
