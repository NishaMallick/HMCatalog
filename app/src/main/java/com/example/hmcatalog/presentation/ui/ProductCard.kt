package com.example.hmcatalog.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hmcatalog.R
import com.example.hmcatalog.domain.models.Product

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .semantics(mergeDescendants = true) {
                contentDescription =
                    "${product.name}, ${product.price}, " +
                            "${product.colors.size} available colors"
            }
    ) {
        Column {

            // PRODUCT IMAGE
            AsyncImage(
                model = product.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 4.2f)
            )

            // INFO SECTION
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F2F2))
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .heightIn(min = 140.dp)
            ) {

                //Brand / Collection (small text)
                Text(
                    text = stringResource(R.string.brand_text),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Product Name
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Price
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Color Swatches
                Row(verticalAlignment = Alignment.CenterVertically) {

                    val visibleColors = product.colors.take(3)
                    val remaining = product.colors.size - 3

                    visibleColors.forEach { hex ->
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 4.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor(hex)),
                                    shape = RectangleShape
                                )
                                .semantics { contentDescription = "Color option" }
                        )
                    }

                    if (remaining > 0) {
                        Text(
                            text = "+$remaining",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .semantics { contentDescription = "$remaining more color options" }
                        )
                    }
                }
            }
        }
    }
}