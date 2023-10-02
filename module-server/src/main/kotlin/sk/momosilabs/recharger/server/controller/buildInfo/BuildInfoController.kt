package sk.momosilabs.recharger.server.controller.buildInfo

import org.springframework.boot.info.BuildProperties
import org.springframework.web.bind.annotation.RestController
import sk.momosilabs.recharger.api.BuildInfoApi
import sk.momosilabs.recharger.api.model.buildInfo.BuildInfoDTO
import java.time.ZoneOffset

@RestController
class BuildInfoController(
    private val buildProperties: BuildProperties,
) : BuildInfoApi {

    override fun get(): BuildInfoDTO = BuildInfoDTO(
        name = buildProperties.name,
        version = buildProperties.version,
        time = buildProperties.time.atOffset(ZoneOffset.UTC).toZonedDateTime(),
    )

}
