package com.dj.jetpackcomposeinternals.state_management.number_guess

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun NumberGuessScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<NumberGuessViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    NumberGuessScreen(state = state, onAction = viewModel::onAction)
}

@Composable
fun NumberGuessScreen(
    state: NumberGuessState,
    onAction: (NumberGuessAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        TextField(
            value = state.numberText,
            onValueChange = { newText ->
                onAction(NumberGuessAction.OnNumberTextChange(numberText = newText))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Button(
            onClick = {
                onAction(NumberGuessAction.OnGuessClick)
            }
        ) {
            Text("Make guess!")
        }

        if (state.guessText != null) {
            Text(
                text = state.guessText
            )
        }
        if (state.isGuessCorrect) {
            Button(onClick = {
                onAction(NumberGuessAction.OnStartNewGameButtonClick)
            }) {
                Text(text = "Restart game")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun NumberGuessScreenPreview() {
    JetpackComposeInternalsTheme {
        NumberGuessScreen(
            state = NumberGuessState(numberText = "123"),
            onAction = {

            }
        )
    }

}