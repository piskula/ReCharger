package sk.momosilabs.recharger.server.entity.vehicle

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotNull
import sk.momosilabs.recharger.server.entity.account.AccountEntity
import java.util.UUID

@Entity(name = "vehicle")
class VehicleEntity(

    @Id
    @field:NotNull
    val id: Long = 0L,

    @field:NotNull
    val uuid: UUID = UUID.randomUUID(),

    @ManyToOne
    @field:NotNull
    val account: AccountEntity,

    @field:NotNull
    val name: String,

    @field:NotNull
    val mileageInitial: Int,

    @field:NotNull
    val percentageInitial: Int,

)
