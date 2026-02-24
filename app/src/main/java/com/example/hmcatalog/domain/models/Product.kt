package com.example.hmcatalog.domain.models

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String,
    val colors: List<String>
)
