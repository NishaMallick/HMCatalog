package com.example.hmcatalog.data.dto

import com.squareup.moshi.JsonClass

data class ProductDto(
    val productName: String?,
    val prices: List<PriceDto>?,
    val images: List<ImageDto>?,
    val swatches: List<SwatchDto>?
)

@JsonClass(generateAdapter = true)
data class PriceDto(
    val formattedPrice: String?
)

@JsonClass(generateAdapter = true)
data class ImageDto(
    val url: String?
)

@JsonClass(generateAdapter = true)
data class SwatchDto(
    val colorCode: String?
)
