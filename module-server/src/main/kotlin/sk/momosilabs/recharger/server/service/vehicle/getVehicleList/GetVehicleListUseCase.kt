package sk.momosilabs.recharger.server.service.vehicle.getVehicleList

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle

interface GetVehicleListUseCase {

    fun getList(pageable: Pageable): Page<Vehicle>

}
