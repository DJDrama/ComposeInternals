package com.dj.performance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    var counter
            by remember { mutableIntStateOf(0) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MyCounter(
            counter = counter,
            onClick = { counter++ }
        )
        Text(
            text = "Hello world",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun MyCounter(modifier: Modifier = Modifier, counter: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text("Counter: $counter")
    }
}

@Preview(showBackground = true)
@Composable
private fun MyCounterPreview() {
    JetpackComposeInternalsTheme {
        MyScreen()
    }

}