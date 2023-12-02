package sk.momosilabs.recharger.server.repository.charging.tmpModel

import java.math.BigDecimal

interface ChargingMonthDbRetrieve {
    val year: Int
    val month: Int
    val mileageMax: Int
    val percentageSpent: Int
    val percentageSpentWhenKwhKnown: Int
    val kwhKnown: BigDecimal
    val priceTotal: BigDecimal
    val chargingCount: Int
}
