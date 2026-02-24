package com.example.hmcatalog.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hmcatalog.domain.usecase.GetProductsUseCase
import com.example.hmcatalog.presentation.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ProductUiState())
        private set

    init {
        loadProducts()
    }

    fun loadProducts() {
        if (uiState.isLoading || uiState.hasReachedEnd) return

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            getProductsUseCase(uiState.currentPage).fold(
                onSuccess = { page ->
                    uiState = uiState.copy(
                        products = uiState.products + page.products,
                        isLoading = false,
                        currentPage = page.nextPage ?: uiState.currentPage,
                        hasReachedEnd = page.nextPage == null
                    )
                },
                onFailure = { exception ->
                    uiState = uiState.copy(
                        isLoading = false,
                        error = exception.message ?: "Unexpected error"
                    )
                }
            )
        }
    }

    fun resetToTop() {
        uiState = ProductUiState()
        loadProducts()
    }
}