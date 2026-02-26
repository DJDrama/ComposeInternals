package com.dj.jetpackcomposeinternals.performance

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlinx.coroutines.launch

@Composable
fun DeferredStateReads(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val fabOffset by remember {
        derivedStateOf {
            val percentage = (1f - (state.firstVisibleItemIndex / 10f))
            (percentage * 100.dp).coerceIn(
                minimumValue = 0.dp,
                maximumValue = 100.dp
            )
        }
    }
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            MovingFloatingActionButton(
                offset = { fabOffset },
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = state,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }

}

@Composable
fun MovingFloatingActionButton(
    modifier: Modifier = Modifier,
    // offset: Dp, // this wil cause multiple times of recomposition

    offset: () -> Dp, // lambda does not change (static lambda) - no recomposition
    onClick: () -> Unit,
) {
    val density = LocalDensity.current
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            //.offset(y = offset)
            .offset {// lambda: independent from recomposition
                IntOffset(
                    x = 0,
                    y = with(density) { offset().roundToPx() }
                )
            }
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Scroll to top"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DeferredStateReadsPreview() {
    JetpackComposeInternalsTheme {
        DeferredStateReads()
    }

}