package sk.momosilabs.recharger.server.repository.charging

import org.springframework.data.domain.Page
import sk.momosilabs.recharger.server.entity.charging.ChargingEntity
import sk.momosilabs.recharger.server.entity.provider.ProviderEntity
import sk.momosilabs.recharger.server.service.charging.model.Charging
import sk.momosilabs.recharger.server.service.charging.model.ProviderSimple
import java.time.ZoneOffset

fun Page<ChargingEntity>.toModel() = map {
    Charging(
        time = it.time.atOffset(ZoneOffset.UTC).toZonedDateTime(),
        mileage = it.mileage,
        percentageFrom = it.percentageFrom,
        percentageTo = it.percentageTo,
        price = it.price,
        kWh = it.kWh,
        provider = it.provider?.toModel(),
    )
}

fun ProviderEntity.toModel() = ProviderSimple(
    id = id,
    name = name,
)
