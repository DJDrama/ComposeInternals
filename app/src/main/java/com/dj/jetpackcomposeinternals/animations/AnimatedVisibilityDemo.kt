package com.dj.jetpackcomposeinternals.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun AnimatedVisibilityDemo(modifier: Modifier = Modifier) {
    var toggle by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = {
            toggle = !toggle
        }) {
            Text(text = "Toggle")
        }

        //val easing = FastOutSlowInEasing
        val easing = EaseInExpo
        AnimatedVisibility(
            visible = toggle,
            enter = /*fadeIn(
                animationSpec = tween(
                    durationMillis = 1_000,
                    delayMillis = 300,
                    easing = easing
                )
            ) + */scaleIn(
                /*animationSpec = tween(
                    durationMillis = 1_000,
                    delayMillis = 300,
                    easing = easing
                )*/
                /*animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy
                )*/
                animationSpec = keyframes {
                    durationMillis = 5000
                    0.75f at 2500 using EaseInExpo
                    0.25f at 3750 using LinearEasing
                    1f at 5000 using FastOutSlowInEasing
                }
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 1_000,
                    delayMillis = 300,
                    easing = easing
                )
            ) + scaleOut(
                animationSpec = tween(
                    durationMillis = 1_000,
                    delayMillis = 300,
                    easing = easing
                )
            )
        ) {
            Text(
                text = "Hello world",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .border(width = 5.dp, color = Color.Red)
                    .wrapContentSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimatedVisibilityDemoPreview() {
    JetpackComposeInternalsTheme {
        AnimatedVisibilityDemo()
    }
}