package sk.momosilabs.recharger.server.controller.common

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import sk.momosilabs.recharger.api.model.common.PageDTO
import sk.momosilabs.recharger.api.model.common.PageableDTO

inline fun <T, U> Page<T>.toDto(transform: (T) -> U): PageDTO<U> =
    PageDTO(
        totalElements = totalElements,
        totalPages = totalPages,
        number = number,
        size = size,
        numberOfElements = numberOfElements,
        content = content.map { transform.invoke(it) },
    )

fun PageableDTO.toModel(): Pageable = PageRequest.of(page, size)
