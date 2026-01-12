package com.dj.jetpackcomposeinternals.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun LazyGridDemo(modifier: Modifier = Modifier) {
    /*LazyVerticalGrid(
        columns = GridCells.Adaptive(50.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(all = 16.dp),

    ) {
        items(100) {
            Box(
                modifier = Modifier
                    .width(
                        width = Random.nextInt(range = 1..200).dp
                    )
                    .size(size = 100.dp)
                    .background(Color(color = Random.nextInt()))
            )
        }
    }*/
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(count = 3),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        verticalItemSpacing = 16.dp,
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(100) {
            Box(
                modifier = Modifier
                    .height(
                        height = Random.nextInt(range = 1..200).dp
                    )
                    .clip(RoundedCornerShape(size = 12.dp))
                    .background(Color(color = Random.nextInt()))
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LazyGridDemoPreview() {
    JetpackComposeInternalsTheme {
        LazyGridDemo()
    }

}