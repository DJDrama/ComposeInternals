package com.dj.jetpackcomposeinternals.performance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun ImageLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(100) {
            Box(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = "https://cdn.pixabay.com/photo/2012/10/26/09/42/climb-63281_1280.jpg",
                    contentDescription = null,
                    modifier = if (it % 2 == 0) Modifier.fillParentMaxWidth() else Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageLoadingPreview() {
    JetpackComposeInternalsTheme {
        ImageLoading()
    }

}