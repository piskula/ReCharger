package sk.momosilabs.recharger.server.service.charging.model

import java.math.BigDecimal
import java.time.ZonedDateTime

data class Charging(
    val time: ZonedDateTime,
    val mileage: Int,
    val percentageFrom: BigDecimal,
    val percentageTo: BigDecimal,
    val price: BigDecimal?,
    val kWh: BigDecimal?,
    val provider: ProviderSimple?,
)
