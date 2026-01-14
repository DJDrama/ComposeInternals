package com.dj.jetpackcomposeinternals.basic_layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.R
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun BoxDemo(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(200.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.cute),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .matchParentSize() // match the size of the parent (200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                    )
                )
        )
        IconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_star_24),
                contentDescription = null,
                tint = Color.Yellow
            )
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF
)
@Composable
private fun BoxDemoPreview() {
    JetpackComposeInternalsTheme {
        BoxDemo()
    }
}