package sk.momosilabs.recharger.server.service.charging.getChargingList

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.charging.model.Charging
import sk.momosilabs.recharger.server.service.charging.model.ProviderSimple
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.random.Random

@Service
class GetChargingList() : GetChargingListUseCase {

    @Transactional(readOnly = true)
    override fun getList(pageable: Pageable): Page<Charging> {
        return PageImpl(
            listOf(
                randomCharging(),
                randomCharging(),
                randomCharging(),
                randomCharging(),
            ),
            pageable,
            157,
        )
    }

    companion object {

        private fun randomCharging() = Charging(
            time = ZonedDateTime.now().minusDays(Random.nextLong(5, 100))
                .plusHours(Random.nextLong(0, 23))
                .plusMinutes(Random.nextLong(0, 59)),
            mileage = Random.nextInt(22000, 30000),
            percentageFrom = BigDecimal.valueOf(Random.nextLong(1, 50)),
            percentageTo = BigDecimal.valueOf(Random.nextLong(51, 100)),
            price = BigDecimal.valueOf(Random.nextLong(800, 6200), 2),
            kWh = BigDecimal.valueOf(Random.nextLong(400, 7900), 2),
            provider = providers.random(),
        )

        private val providers = listOf(
            ProviderSimple(1L, "GreenWay"),
            ProviderSimple(2L, "ZSE"),
            ProviderSimple(3L, "Billa"),
            ProviderSimple(4L, "Home"),
            null,
        )

    }

}
