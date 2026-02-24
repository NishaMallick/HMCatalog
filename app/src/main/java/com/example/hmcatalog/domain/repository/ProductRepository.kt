package com.example.hmcatalog.domain.repository

import com.example.hmcatalog.domain.models.ProductPage

interface ProductRepository {
    suspend fun getProducts(page: Int): Result<ProductPage>
}