package sk.momosilabs.recharger.server.service.commonCalculation

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.isZero() = this.compareTo(BigDecimal.ZERO) == 0

fun BigDecimal.divideSafe(divisor: BigDecimal): BigDecimal = if (divisor.isZero()) BigDecimal.ZERO else
    this.divide(divisor, 3, RoundingMode.HALF_UP)
