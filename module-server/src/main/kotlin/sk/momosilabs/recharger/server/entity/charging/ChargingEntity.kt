package sk.momosilabs.recharger.server.entity.charging

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotNull
import sk.momosilabs.recharger.server.entity.provider.ProviderEntity
import sk.momosilabs.recharger.server.entity.vehicle.VehicleEntity
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID

@Entity(name = "charging")
class ChargingEntity(

    @Id
    @field:NotNull
    val id: Long = 0L,

    @field:NotNull
    val uuid: UUID = UUID.randomUUID(),

    @ManyToOne
    @field:NotNull
    val vehicle: VehicleEntity,

    @field:NotNull
    val time: LocalDateTime,
    @field:NotNull
    val mileage: Int,
    @Column(columnDefinition = "TINYINT")
    @field:NotNull
    val percentageFrom: Int,
    @Column(columnDefinition = "TINYINT")
    @field:NotNull
    val percentageTo: Int,
    @field:NotNull
    val price: BigDecimal,
    @Column(name = "kwh")
    val kWh: BigDecimal?,

    @ManyToOne
    val provider: ProviderEntity?,
)
