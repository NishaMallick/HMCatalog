package com.example.hmcatalog.data.repository

import android.net.http.HttpException
import com.example.hmcatalog.data.api.HmApi
import com.example.hmcatalog.domain.models.Product
import com.example.hmcatalog.domain.models.ProductPage
import com.example.hmcatalog.domain.repository.ProductRepository
import java.io.IOException

class ProductRepositoryImpl(
    private val api: HmApi
) : ProductRepository {

    override suspend fun getProducts(page: Int): Result<ProductPage> {
        return try {

            val response = api.searchProducts(page = page)

            val products = response.searchHits
                ?.productList
                .orEmpty()

            val mappedProducts = products.map { dto ->

                val formattedPrice = dto.prices
                    ?.firstOrNull()
                    ?.formattedPrice
                    .orEmpty()

                val colors = dto.swatches
                    ?.mapNotNull { it.colorCode }
                    ?.map { "#$it" }
                    .orEmpty()

                Product(
                    name = dto.productName.orEmpty(),
                    price = formattedPrice,
                    imageUrl = dto.images
                        ?.firstOrNull()
                        ?.url
                        .orEmpty(),
                    colors = colors
                )
            }

            Result.success(
                ProductPage(
                    products = mappedProducts,
                    currentPage = response.pagination.currentPage,
                    nextPage = response.pagination.nextPageNum,
                    totalPages = response.pagination.totalPages
                )
            )

        } catch (e: IOException) {
            Result.failure(Exception("No internet connection"))
        } catch (e: HttpException) {
            Result.failure(Exception("Server error"))
        } catch (e: Exception) {
            Result.failure(Exception("Something went wrong"))
        }
    }
}