package sk.momosilabs.recharger.server.service.vehicle.getVehicleList

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.account.currentUser.CurrentUserService
import sk.momosilabs.recharger.server.service.vehicle.VehiclePersistence
import sk.momosilabs.recharger.server.service.vehicle.model.Vehicle

@Service
open class GetVehicleList(
    private val persistence: VehiclePersistence,
    private val currentUserService: CurrentUserService,
) : GetVehicleListUseCase {

    @Transactional(readOnly = true)
    override fun getList(pageable: Pageable): Page<Vehicle> {
        val list = persistence.list(currentUserService.identifier(), pageable)
        val mileagesByVehicleId = list.content.mapTo(HashSet()) { it.id }
            .let { usedVehicleIds -> persistence.getCurrentMileagesForVehicles(usedVehicleIds) }

        return list.fillInCurrentMileages(mileagesByVehicleId)
    }

    private fun Page<Vehicle>.fillInCurrentMileages(mileageByVehicle: Map<Long, Int>) = onEach {
        if (mileageByVehicle.containsKey(it.id))
            it.mileage = mileageByVehicle[it.id]!!
    }

}
