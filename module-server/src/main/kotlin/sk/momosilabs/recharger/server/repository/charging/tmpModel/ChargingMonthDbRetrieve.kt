package sk.momosilabs.recharger.server.repository.charging.tmpModel

import java.math.BigDecimal

interface ChargingMonthDbRetrieve {
    val year: Int
    val month: Int
    val distanceDriven: Int
    val percentageSpent: Int
    val kwhPercentageRatio: BigDecimal
    val priceTotal: BigDecimal
    val chargingCount: Int
}
