package sk.momosilabs.recharger.server.controller.charging

import org.springframework.web.bind.annotation.RestController
import sk.momosilabs.recharger.api.ChargingApi
import sk.momosilabs.recharger.api.model.charging.ChargingDTO
import sk.momosilabs.recharger.api.model.charging.ChargingMonthDTO
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO
import sk.momosilabs.recharger.server.controller.common.toDto
import sk.momosilabs.recharger.server.controller.common.toModel
import sk.momosilabs.recharger.server.service.charging.getChargingList.GetChargingListUseCase
import sk.momosilabs.recharger.server.service.charging.getChargingMonthList.GetChargingMonthListUseCase
import java.util.UUID

@RestController
class ChargingController(
    private val getChargingList: GetChargingListUseCase,
    private val getChargingMonthList: GetChargingMonthListUseCase,
) : ChargingApi {

    override fun listCharging(vehicleUuid: UUID, pageable: PageableDTO): PageDTO<ChargingDTO> =
        getChargingList.getList(vehicleUuid = vehicleUuid, pageable.toModel())
            .toDto { chargingItem -> chargingItem.toDto() }

    override fun listPerMonth(vehicleUuid: UUID, pageable: PageableDTO): PageDTO<ChargingMonthDTO> =
        getChargingMonthList.getList(vehicleUuid = vehicleUuid, pageable.toModel())
            .toDto { chargingItem -> chargingItem.toDto() }

}
