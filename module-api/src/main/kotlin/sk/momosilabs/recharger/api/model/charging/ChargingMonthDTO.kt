package sk.momosilabs.recharger.api.model.charging

import java.math.BigDecimal

data class ChargingMonthDTO(
    val year: Int,
    val month: Int,
    val mileageDriven: Int,
    val averageConsumption: BigDecimal,
    val priceTotal: BigDecimal,
    val chargingCount: Int,
)
