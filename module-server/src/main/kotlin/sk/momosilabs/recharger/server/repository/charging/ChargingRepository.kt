package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.charging.ChargingEntity

@Repository
interface ChargingRepository : JpaRepository<ChargingEntity, Long> {

    fun findAllByVehicleId(vehicleId: Long, pageable: Pageable): Page<ChargingEntity>

}
