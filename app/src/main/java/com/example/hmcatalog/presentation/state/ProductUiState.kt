package com.example.hmcatalog.presentation.state

import com.example.hmcatalog.domain.models.Product

data class ProductUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val hasReachedEnd: Boolean = false
)
