package com.dj.jetpackcomposeinternals.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun CanvasModifiersDemo(modifier: Modifier = Modifier) {

    /*Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        drawCircle(color = Color.Red)
    }*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .drawWithCache {// for caching
                onDrawWithContent {
                    drawCircle(Color.Red)
                    drawContent()
                    drawCircle(
                        color = Color.Yellow,
                        radius = 10.dp.toPx()
                    )
                }
            }
        /* .drawWithContent {
             drawCircle(Color.Red)
             drawContent() // displays Text
             drawCircle(
                 color = Color.Yellow,
                 radius = 10.dp.toPx()
             )

         }*/
        /*.drawBehind { // draw behind
            withTransform(
                transformBlock = {
                    rotate(degrees = 90f)
                },
                drawBlock = {
                    drawLine(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Yellow,
                                Color.Red
                            )
                        ),
                        start = Offset.Zero,
                        end = this.center,
                        strokeWidth = 10.dp.toPx()
                    )
                }
            )
            //drawCircle(color = Color.Red)
        }*/,
        contentAlignment = Alignment.Center
    ) {
        Text("Hello World")
    }

}

@Preview(showBackground = true)
@Composable
private fun CanvasModifiersDemoPreview() {
    JetpackComposeInternalsTheme {
        CanvasModifiersDemo()
    }

}