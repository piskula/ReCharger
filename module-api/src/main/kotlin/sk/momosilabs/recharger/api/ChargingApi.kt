package sk.momosilabs.recharger.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import sk.momosilabs.recharger.api.model.charging.ChargingDTO
import sk.momosilabs.recharger.api.model.charging.ChargingMonthDTO
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO
import java.util.UUID

@Api("Charging")
interface ChargingApi {

    companion object {
        const val ENDPOINT_API_CHARGING = "/api/charging/byVehicleUuid/{vehicleUuid}"
    }

    @ApiOperation("List all chargings of vehicle")
    @ApiImplicitParams(
        ApiImplicitParam(paramType = "query", name = "page", dataType = "integer"),
        ApiImplicitParam(paramType = "query", name = "size", dataType = "integer"),
//        ApiImplicitParam(paramType = "query", name = "sort", dataType = "string")
    )
    @GetMapping(ENDPOINT_API_CHARGING)
    fun listCharging(@PathVariable vehicleUuid: UUID, pageable: PageableDTO): PageDTO<ChargingDTO>

    @ApiOperation("List all chargings of vehicle")
    @ApiImplicitParams(
        ApiImplicitParam(paramType = "query", name = "page", dataType = "integer"),
        ApiImplicitParam(paramType = "query", name = "size", dataType = "integer"),
//        ApiImplicitParam(paramType = "query", name = "sort", dataType = "string")
    )
    @GetMapping("$ENDPOINT_API_CHARGING/perMonth")
    fun listPerMonth(@PathVariable vehicleUuid: UUID, pageable: PageableDTO): PageDTO<ChargingMonthDTO>

}
