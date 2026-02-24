package com.example.hmcatalog.domain.usecase

import com.example.hmcatalog.domain.repository.ProductRepository

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(page: Int) =
        productRepository.getProducts(page)
}