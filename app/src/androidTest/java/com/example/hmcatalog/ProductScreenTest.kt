package com.example.hmcatalog

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.hmcatalog.domain.models.Product
import com.example.hmcatalog.presentation.state.ProductUiState
import com.example.hmcatalog.presentation.ui.ProductScreen
import org.junit.Rule
import org.junit.Test

class ProductScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun products_are_displayed() {

        val fakeProducts = listOf(
            Product(
                name = "Test Jeans",
                price = "299 kr",
                imageUrl = "",
                colors = listOf("#FFFFFF")
            )
        )

        val state = ProductUiState(
            products = fakeProducts,
            isLoading = false
        )

        composeTestRule.setContent {
            ProductScreen(
                state = state,
                onLoadMore = {},
                onRetry = {},
                onScrollToTop = {}
            )
        }

        composeTestRule
            .onNodeWithText("Test Jeans")
            .assertIsDisplayed()
    }
}