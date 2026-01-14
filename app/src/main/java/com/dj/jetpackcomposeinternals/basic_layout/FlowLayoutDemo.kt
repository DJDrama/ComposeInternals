@file:OptIn(ExperimentalLayoutApi::class)

package com.dj.jetpackcomposeinternals.basic_layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dj.jetpackcomposeinternals.R
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun FlowLayoutDemo(modifier: Modifier = Modifier) {
    val maxLines = remember { mutableIntStateOf(value = 4) }
    FlowRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround,
        //verticalArrangement = Arrangement.Center
        //maxItemsInEachRow = 3,
        maxLines = maxLines.value,
        //overflow = FlowRowOverflow.Clip
        overflow = FlowRowOverflow.expandOrCollapseIndicator(
            expandIndicator = {
                IconButton(onClick = {
                    maxLines.value = Int.MAX_VALUE
                }) {
                    Icon(
                        painter = painterResource(R.drawable.outline_keyboard_arrow_down_24),
                        contentDescription = "Expand"
                    )
                }
            },
            collapseIndicator = {
                IconButton(onClick = {
                    maxLines.value = 4
                }) {
                    Icon(
                        painter = painterResource(R.drawable.outline_keyboard_arrow_up_24),
                        contentDescription = "Shrink"
                    )
                }
            }
        )
    ) {
        for (i in 1..30) {
            AssistChip(
                onClick = {},
                label = {
                    Text("#Item $i")
                })
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun FlowLayoutDemoPreview() {
    JetpackComposeInternalsTheme {
        FlowLayoutDemo()
    }
}