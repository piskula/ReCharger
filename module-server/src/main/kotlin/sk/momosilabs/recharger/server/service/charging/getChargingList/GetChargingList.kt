package sk.momosilabs.recharger.server.service.charging.getChargingList

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.charging.ChargingPersistence
import sk.momosilabs.recharger.server.service.charging.model.Charging
import java.util.UUID

@Service
open class GetChargingList(
    private val persistence: ChargingPersistence,
) : GetChargingListUseCase {

    @Transactional(readOnly = true)
    override fun getList(vehicleUuid: UUID, pageable: Pageable): Page<Charging> {
        val newestOnTop = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(Sort.Order(Sort.Direction.DESC, "id")))
        return persistence.list(vehicleUuid, newestOnTop)
    }

}
