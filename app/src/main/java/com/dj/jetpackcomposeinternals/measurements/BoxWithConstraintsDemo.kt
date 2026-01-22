package com.dj.jetpackcomposeinternals.measurements

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun BoxWithConstraintsDemo(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier.wrapContentWidth()
    ) {
        if (constraints.hasFixedWidth) {
            Text("Fix width!") // when width is set (fixed dp, fillMaxWidth())
        } else {
            Text("Dynamic width!") // if width is not set (wrapContentWidth(), or not set)
        }

    }
}

@Preview
@Composable
private fun BoxWithConstraintsDemoPreview() {
    JetpackComposeInternalsTheme {
        BoxWithConstraintsDemo()
    }
}