package sk.momosilabs.recharger.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO
import sk.momosilabs.recharger.api.model.vehicle.VehicleDTO

@Api("Vehicle")
interface VehicleApi {

    companion object {
        const val ENDPOINT_API_VEHICLE = "/api/vehicle"
    }

    @ApiOperation("List all vehicles of user")
    @ApiImplicitParams(
        ApiImplicitParam(paramType = "query", name = "page", dataType = "integer"),
        ApiImplicitParam(paramType = "query", name = "size", dataType = "integer"),
        ApiImplicitParam(paramType = "query", name = "sort", dataType = "string")
    )
    @GetMapping(ENDPOINT_API_VEHICLE)
    fun listVehicle(pageable: PageableDTO): PageDTO<VehicleDTO>

}
