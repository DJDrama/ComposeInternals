package com.dj.jetpackcomposeinternals.side_effects

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(modifier: Modifier = Modifier) {
    val counter by produceState(initialValue = 0) {
        while (true) {
            delay(timeMillis = 1_000L)
            value++
        }
    }
    /*var counter by remember { mutableIntStateOf(value = 0) }
    LaunchedEffect(true) {
        while (true) {
            delay(timeMillis = 1_000L)
            counter++
        }
    }*/
    Text(
        text = "$counter",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
private fun ProduceStateDemoPreview() {
    JetpackComposeInternalsTheme {
        ProduceStateDemo()
    }

}