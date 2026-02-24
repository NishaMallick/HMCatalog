package com.example.hmcatalog.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hmcatalog.presentation.viewmodel.ProductViewModel


@Composable
fun ProductScreenRoute(
    viewModel: ProductViewModel = hiltViewModel()
) {
    ProductScreen(
        state = viewModel.uiState,
        onLoadMore = { viewModel.loadProducts() },
        onRetry = { viewModel.loadProducts() },
        onScrollToTop = { viewModel.resetToTop() }
    )
}