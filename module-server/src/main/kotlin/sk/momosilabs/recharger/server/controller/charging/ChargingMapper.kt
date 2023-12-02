package sk.momosilabs.recharger.server.controller.charging

import sk.momosilabs.recharger.api.model.charging.ChargingDTO
import sk.momosilabs.recharger.api.model.charging.ChargingMonthDTO
import sk.momosilabs.recharger.api.model.charging.ProviderSimpleDTO
import sk.momosilabs.recharger.server.service.charging.model.Charging
import sk.momosilabs.recharger.server.service.charging.model.ChargingMonth
import sk.momosilabs.recharger.server.service.charging.model.ProviderSimple
import java.math.BigDecimal

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

fun ChargingMonth.toDto() = ChargingMonthDTO(
    year = year,
    month = month,
    mileageDriven = mileageDriven,
    averageConsumption = averageConsumption,
    priceTotal = priceTotal,
    chargingCount = chargingCount,
)
