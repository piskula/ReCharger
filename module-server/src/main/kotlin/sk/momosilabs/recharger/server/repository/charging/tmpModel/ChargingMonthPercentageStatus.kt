package sk.momosilabs.recharger.server.repository.charging.tmpModel

data class ChargingMonthPercentageStatus(
    val year: Int,
    val month: Int,
    val percentageStatusAtEnd: Int,
)
