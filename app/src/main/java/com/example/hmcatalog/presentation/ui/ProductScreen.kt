package com.example.hmcatalog.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.hmcatalog.R
import com.example.hmcatalog.presentation.state.ProductUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductScreen(
    state: ProductUiState,
    onLoadMore: () -> Unit,
    onRetry: () -> Unit,
    onScrollToTop: () -> Unit,
) {
    val gridState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxSize()) {

        // PRODUCT GRID
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState,
            modifier = Modifier
                .fillMaxSize()
                .testTag("product_grid")
                .semantics {
                    contentDescription =
                        "Grid of products, ${state.products.size} items"
                }
        ) {

            items(state.products) { product ->
                ProductCard(product)
            }
        }

        // EMPTY STATE
        if (!state.isLoading &&
            state.products.isEmpty() &&
            state.error == null
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics {
                        contentDescription = "No products found"
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_products_found),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // PAGINATION TRIGGER
        LaunchedEffect(gridState, state.products.size) {
            snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
                .collectLatest { visibleItems ->

                    if (state.products.isEmpty()) return@collectLatest

                    val lastVisible = visibleItems.lastOrNull()?.index ?: 0

                    if (lastVisible >= state.products.size - 4 &&
                        !state.isLoading &&
                        !state.hasReachedEnd
                    ) {
                        onLoadMore()
                    }
                }
        }

        // SCROLL TO TOP FAB
        if (state.currentPage > 3) {
            FloatingActionButton(
                onClick = onScrollToTop,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .semantics { contentDescription = "scroll to top" }
                    .testTag("scroll_to_top")
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.scroll_to_top)
                )
            }
        }
    }

    // ERROR STATE
    state.error?.let { errorMessage ->

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.testTag("error_message")
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onRetry,
                    modifier = Modifier.semantics {
                        contentDescription = "Retry loading products"
                    }) {
                    Text(stringResource(R.string.retry))
                }
            }
        }
    }
}