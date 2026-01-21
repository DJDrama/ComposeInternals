package com.dj.jetpackcomposeinternals.basic_modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun OffsetModifierDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .size(100.dp)
            .background(Color.Red)
    ) {
        Text(
            "Hello World!",
            modifier = Modifier
//                .padding(
//                    start = 50.dp,
//                    top = 20.dp
//                )
                .offset(x = 50.dp, y = 20.dp) // "offset" is helpful for animation
                .background(Color.Green)
        )

        Text(
            "Hello World!",
            modifier = Modifier.background(Color.Yellow)
        )
    }
}

@Preview
@Composable
private fun OffsetModifierDemoPreview() {
    JetpackComposeInternalsTheme {
        OffsetModifierDemo()
    }
}