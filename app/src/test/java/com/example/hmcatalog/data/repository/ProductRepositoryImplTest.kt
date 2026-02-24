package com.example.hmcatalog.data.repository

import com.example.hmcatalog.data.api.HmApi
import com.example.hmcatalog.data.dto.ImageDto
import com.example.hmcatalog.data.dto.PaginationDto
import com.example.hmcatalog.data.dto.PriceDto
import com.example.hmcatalog.data.dto.ProductDto
import com.example.hmcatalog.data.dto.ProductResponseDto
import com.example.hmcatalog.data.dto.SearchHitsDto
import com.example.hmcatalog.data.dto.SwatchDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductRepositoryImplTest {
    private val api = mockk<HmApi>()
    private val repository = ProductRepositoryImpl(api)

    @Test
    fun `maps api response to domain correctly`() = runBlocking {

        val response = ProductResponseDto(
            searchHits = SearchHitsDto(
                productList = listOf(
                    ProductDto(
                        productName = "Jeans",
                        prices = listOf(PriceDto("299 kr")),
                        images = listOf(ImageDto("image-url")),
                        swatches = listOf(SwatchDto("FFFFFF"))
                    )
                )
            ),
            pagination = PaginationDto(1, 2, 10)
        )

        coEvery { api.searchProducts(any(), any(), any()) } returns response

        val result = repository.getProducts(1).getOrNull()

        assertEquals(1, result?.products?.size)
        assertEquals("Jeans", result?.products?.first()?.name)
        assertEquals("#FFFFFF", result?.products?.first()?.colors?.first())
    }
}