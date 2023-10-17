package sk.momosilabs.recharger.server.entity.account

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.util.UUID

@Entity(name = "account")
class AccountEntity(

    @Id
    @field:NotNull
    val id: Long = 0L,

    @field:NotNull
    val uuid: UUID = UUID.randomUUID(),

    @field:NotNull
    val provider: String,

    @field:NotNull
    val providerIdentifier: String,

    @field:NotNull
    val email: String,

)
