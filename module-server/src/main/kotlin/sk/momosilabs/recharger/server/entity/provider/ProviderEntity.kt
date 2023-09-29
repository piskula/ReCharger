package sk.momosilabs.recharger.server.entity.provider

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.util.UUID

@Entity(name = "provider")
class ProviderEntity(

    @Id
    @field:NotNull
    val id: Long = 0L,

    @field:NotNull
    val uuid: UUID = UUID.randomUUID(),

    @field:NotNull
    val name: String,
)
