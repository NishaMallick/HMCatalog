package com.example.hmcatalog.domain.models

data class ProductPage(
    val products: List<Product>,
    val currentPage: Int,
    val nextPage: Int?,
    val totalPages: Int
)
