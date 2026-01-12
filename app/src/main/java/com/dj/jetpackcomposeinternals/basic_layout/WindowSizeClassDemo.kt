package com.dj.jetpackcomposeinternals.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun WindowSizeClassDemo(modifier: Modifier = Modifier) {
    val windowClass = currentWindowAdaptiveInfo().windowSizeClass
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (windowClass.windowWidthSizeClass) {
                WindowWidthSizeClass.COMPACT -> {
                    MyLazyList()
                }

                WindowWidthSizeClass.MEDIUM -> {
                    MyLazyList()
                }

                WindowWidthSizeClass.EXPANDED -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(3f)
                                .background(Color.Red)
                        ) {
                            Text("Menu Option1")
                            Text("Menu Option2")
                            Text("Menu Option3")
                        }
                        MyLazyList(modifier = Modifier.weight(7f))
                    }
                }
            }
        }
    }

}

@Composable
fun MyLazyList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(40) {
            Text(
                text = "Item $it",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

//@PreviewScreenSizes
@Preview(
    showBackground = true,
    device = Devices.NEXUS_10
)
@Composable
private fun WindowSizeClassDemoPreview() {
    JetpackComposeInternalsTheme {
        WindowSizeClassDemo()
    }

}