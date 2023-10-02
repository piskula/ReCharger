package sk.momosilabs.recharger.server.controller.charging

import sk.momosilabs.recharger.api.model.charging.ChargingDTO
import sk.momosilabs.recharger.api.model.charging.ProviderSimpleDTO
import sk.momosilabs.recharger.server.service.charging.model.Charging
import sk.momosilabs.recharger.server.service.charging.model.ProviderSimple

fun Charging.toDto() = ChargingDTO(
    time = time,
    mileage = mileage,
    percentageFrom = percentageFrom.toBigDecimal(),
    percentageTo = percentageTo.toBigDecimal(),
    price = price,
    kWh = kWh,
    provider = provider?.toDto(),
)

fun ProviderSimple.toDto() = ProviderSimpleDTO(
    uuid = uuid,
    name = name,
)
