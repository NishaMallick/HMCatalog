package com.example.hmcatalog.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponseDto(
    val searchHits: SearchHitsDto?,
    val pagination: PaginationDto
)

@JsonClass(generateAdapter = true)
data class SearchHitsDto(
    val productList: List<ProductDto>?
)
