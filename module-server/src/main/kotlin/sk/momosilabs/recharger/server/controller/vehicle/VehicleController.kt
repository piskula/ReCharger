package sk.momosilabs.recharger.server.controller.vehicle

import org.springframework.web.bind.annotation.RestController
import sk.momosilabs.recharger.api.VehicleApi
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO
import sk.momosilabs.recharger.api.model.vehicle.VehicleDTO
import sk.momosilabs.recharger.server.controller.common.toDto
import sk.momosilabs.recharger.server.controller.common.toModel
import sk.momosilabs.recharger.server.service.vehicle.getVehicleList.GetVehicleListUseCase

@RestController
class VehicleController(
    private val getVehicleList: GetVehicleListUseCase,
) : VehicleApi {

    override fun listVehicle(pageable: PageableDTO): PageDTO<VehicleDTO> =
        getVehicleList.getList(pageable.toModel())
            .toDto { vehicle -> vehicle.toDto() }

}
