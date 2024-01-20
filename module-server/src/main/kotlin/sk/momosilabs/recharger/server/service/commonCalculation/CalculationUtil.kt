package sk.momosilabs.recharger.server.service.commonCalculation

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.isZero() = this.compareTo(BigDecimal.ZERO) == 0

fun BigDecimal.divideSafe(divisor: BigDecimal): BigDecimal = if (divisor.isZero()) BigDecimal.ZERO else
    this.divide(divisor, 3, RoundingMode.HALF_UP)

fun calculateConsumption(
    percentageSpentTotal: Int,
    kwhPercentageRatio: BigDecimal,
    distanceDriven: Int,
): BigDecimal {
    val distancePerPercentageUnit = distanceDriven.toBigDecimal()
        .divide(percentageSpentTotal.toBigDecimal(), 6, RoundingMode.HALF_UP)
    return BigDecimal.valueOf(100L).divide(distancePerPercentageUnit, 6, RoundingMode.HALF_UP)
        .multiply(kwhPercentageRatio).setScale(1, RoundingMode.HALF_UP)
}
