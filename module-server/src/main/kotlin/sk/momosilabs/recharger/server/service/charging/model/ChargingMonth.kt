package sk.momosilabs.recharger.server.service.charging.model

import java.math.BigDecimal

data class ChargingMonth(
    val year: Int,
    val month: Int,
    val mileageDriven: Int,
    val averageConsumption: BigDecimal,
    val priceTotal: BigDecimal,
    val chargingCount: Int,
)
