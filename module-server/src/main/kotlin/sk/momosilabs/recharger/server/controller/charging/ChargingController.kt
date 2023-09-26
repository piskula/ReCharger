package sk.momosilabs.recharger.server.controller.charging

import org.springframework.web.bind.annotation.RestController
import sk.momosilabs.recharger.api.ChargingApi
import sk.momosilabs.recharger.api.model.charging.ChargingDTO
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO
import sk.momosilabs.recharger.server.controller.common.toDto
import sk.momosilabs.recharger.server.controller.common.toModel
import sk.momosilabs.recharger.server.service.charging.getChargingList.GetChargingListUseCase

@RestController
class ChargingController(
    private val getChargingList: GetChargingListUseCase,
) : ChargingApi {

    override fun getCalls(pageable: PageableDTO): PageDTO<ChargingDTO> =
        getChargingList.getList(pageable.toModel()).toDto { chargingItem -> chargingItem.toDto() }

}
