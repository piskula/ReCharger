package sk.momosilabs.recharger.server.repository.vehicle

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.vehicle.VehicleEntity
import java.util.UUID

@Repository
interface VehicleRepository : JpaRepository<VehicleEntity, Long> {

    fun findAllByAccountProviderIdentifier(userIdentifier: String, pageable: Pageable): Page<VehicleEntity>

    fun findByUuid(uuid: UUID): VehicleEntity

}
