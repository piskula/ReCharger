package sk.momosilabs.recharger.server.repository.charging.tmpModel

import java.math.BigDecimal

data class ChargingMonthTmp(
    val year: Int,
    val month: Int,
    val mileageMax: Int,
    val percentageSpent: Int,
    val percentageCharged: Int,
    val kwhKnown: BigDecimal,
    val percentageStatusAtEnd: Int,
    val priceTotal: BigDecimal,
    val chargingCount: Int,
)
