package com.example.hmcatalog.presentation.viewmodel

import com.example.hmcatalog.domain.models.Product
import com.example.hmcatalog.domain.models.ProductPage
import com.example.hmcatalog.domain.usecase.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductViewModelTest {

    private val useCase = mockk<GetProductsUseCase>()

    @Test
    fun `loadProducts updates state on success`() = runTest {

        val fakePage = ProductPage(
            products = listOf(
                Product("Jeans", "299", "url", emptyList())
            ),
            currentPage = 1,
            nextPage = 2,
            totalPages = 10
        )

        coEvery { useCase(any()) } returns Result.success(fakePage)

        val viewModel = ProductViewModel(useCase)

        assertEquals(1, viewModel.uiState.products.size)
        assertNull(viewModel.uiState.error)
    }

    @Test
    fun `loadProducts sets error on failure`() = runTest {

        coEvery { useCase(any()) } returns Result.failure(Exception())

        val viewModel = ProductViewModel(useCase)

        assertNotNull(viewModel.uiState.error)
        assertTrue(viewModel.uiState.products.isEmpty())
    }
}