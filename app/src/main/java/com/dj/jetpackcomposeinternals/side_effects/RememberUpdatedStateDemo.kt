package com.dj.jetpackcomposeinternals.side_effects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlinx.coroutines.delay

@Composable
fun LaunchAnimation(
    snackbarHostState: SnackbarHostState,
    username: String,
) {
    val updatedUserName by rememberUpdatedState(username)
    LaunchedEffect(true) {
        delay(5_000L)
        snackbarHostState.showSnackbar(
            "Welcome to the app, $updatedUserName!"
        )
    }
}

@Composable
fun RememberUpdatedStateDemo(modifier: Modifier = Modifier) {
    var username by remember {
        mutableStateOf("")
    }
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LaunchAnimation(
                snackbarHostState = snackbarHostState,
                username = username
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray),
                placeholder = {
                    Text("Enter something")
                })
        }
    }
}

@Preview
@Composable
private fun RememberUpdatedStateDemoPreview() {

    JetpackComposeInternalsTheme {
        RememberUpdatedStateDemo()
    }

}