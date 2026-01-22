package com.dj.jetpackcomposeinternals.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMaxOfOrNull
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun SubcomposePagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    SubcomposeLayout(
        modifier = modifier,
    ) { constraints ->
        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        val measurables = subcompose("content", content)
        var i = 0
        for (measurable in measurables) {
            val placeable = measurable.measure(constraints.copy(minWidth = 0, minHeight = 0))
            i++
            if (currentPageWidth + placeable.width > constraints.maxWidth) {
                if (pages.size == page) {
                    break
                }
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }
        println("We measured $i composables.")

        if (currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }

        val pageItems = pages.getOrNull(index = page) ?: emptyList()
        val maxHeight = pageItems.fastMaxOfOrNull { it.height } ?: 0

        layout(width = constraints.maxWidth, height = maxHeight) {
            var xOffset = 0
            pageItems.forEach { placeable ->
                placeable.place(xOffset, 0)
                xOffset += placeable.width
            }
        }
    }
}

@Preview
@Composable
private fun SubcomposePagedRowPreview() {
    JetpackComposeInternalsTheme {
        SubcomposePagedRow(
            page = 0
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(150.dp)
                    .background(Color.Yellow)
            )
            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(120.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}