package sk.momosilabs.recharger.server.repository.vehicle

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.vehicle.VehicleEntity

@Repository
interface VehicleRepository : JpaRepository<VehicleEntity, Long> {
}
