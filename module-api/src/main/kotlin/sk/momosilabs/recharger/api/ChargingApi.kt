package sk.momosilabs.recharger.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import sk.momosilabs.recharger.api.model.charging.ChargingDTO
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO

@Api("Charging")
interface ChargingApi {

    companion object {
        const val endpointApiCharging = "/api/charging"
    }

    @ApiOperation("List all chargings")
    @ApiImplicitParams(
        ApiImplicitParam(paramType = "query", name = "page", dataType = "integer"),
        ApiImplicitParam(paramType = "query", name = "size", dataType = "integer"),
//        ApiImplicitParam(paramType = "query", name = "sort", dataType = "string")
    )
    @GetMapping(endpointApiCharging)
    fun getCalls(pageable: PageableDTO): PageDTO<ChargingDTO>

}
