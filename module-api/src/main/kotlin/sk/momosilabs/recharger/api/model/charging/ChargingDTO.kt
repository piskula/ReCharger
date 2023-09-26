package sk.momosilabs.recharger.api.model.charging

import java.math.BigDecimal
import java.time.ZonedDateTime

data class ChargingDTO(
    val time: ZonedDateTime,
    val mileage: Int,
    val percentageFrom: BigDecimal,
    val percentageTo: BigDecimal,
    val price: BigDecimal?,
    val kWh: BigDecimal?,
    val provider: ProviderSimpleDTO?,
)
