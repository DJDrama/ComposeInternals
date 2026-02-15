package com.dj.jetpackcomposeinternals.side_effects

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlinx.coroutines.launch

@Composable
fun LaunchedEffectDemo(modifier: Modifier = Modifier) {
    var counter by remember {
        mutableIntStateOf(value = 0)
    }
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    // LaunchedEffect(Unit or true){} // triggers when first launches this composable
    /*LaunchedEffect(key1 = counter) { // cancels previous when key1 changes
        if (counter % 2 == 0) {
                snackbarHostState.showSnackbar("The number is even")
        }
    }*/
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Button(
            onClick = {
                counter++
                if (counter % 2 == 0) {
                    scope.launch {
                        snackbarHostState.showSnackbar("The number is even")
                    }
                }
            }, modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .wrapContentSize()
        ) {
            Text("Counter: $counter")
        }
    }

}

@Preview
@Composable
private fun LaunchedEffectDemoPreview() {
    JetpackComposeInternalsTheme {
        LaunchedEffectDemo()
    }
}