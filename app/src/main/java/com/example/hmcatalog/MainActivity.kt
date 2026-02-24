package com.example.hmcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.hmcatalog.presentation.ui.ProductScreenRoute
import com.example.hmcatalog.ui.theme.HMCatalogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HMCatalogTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductScreenRoute()
                }
            }
        }
    }
}