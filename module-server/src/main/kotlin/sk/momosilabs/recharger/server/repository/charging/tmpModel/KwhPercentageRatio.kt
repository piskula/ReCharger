package sk.momosilabs.recharger.server.repository.charging.tmpModel

import sk.momosilabs.recharger.server.service.charging.model.CurrentType
import java.math.BigDecimal

interface KwhPercentageRatio {
    val currentType: CurrentType
    val kwhTotal: BigDecimal
    val percentageTotal: BigDecimal
}
