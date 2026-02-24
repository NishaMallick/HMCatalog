package com.example.hmcatalog.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationDto(
    val currentPage: Int,
    val nextPageNum: Int?,
    val totalPages: Int
)