package com.dj.jetpackcomposeinternals.state_management.number_guess

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class NumberGuessViewModel : ViewModel() {

    private val _state = MutableStateFlow(NumberGuessState())
    private var attempts = 0
    val state = _state.asStateFlow()

    private var number = Random.nextInt(1, 101)

    fun onAction(action: NumberGuessAction) {
        when (action) {
            NumberGuessAction.OnGuessClick -> {
                val guess = state.value.numberText.toIntOrNull()
                if (guess != null) {
                    attempts++
                }
                _state.update {
                    it.copy(
                        guessText = when {
                            guess == null -> "Please enter a number."
                            number > guess -> "Nope, my number is larger."
                            number < guess -> "Nope, my number is smaller."
                            else -> "That was it! You needed $attempts attemtps."
                        },
                        isGuessCorrect = guess == number,
                        numberText = ""
                    )
                }
            }

            is NumberGuessAction.OnNumberTextChange -> {
                _state.update {
                    it.copy(
                        numberText = action.numberText
                    )
                }
            }

            NumberGuessAction.OnStartNewGameButtonClick -> {
                number = Random.nextInt(1, 101)
                attempts = 0
                _state.update {
                    it.copy(
                        numberText = "",
                        guessText = null,
                        isGuessCorrect = false
                    )
                }
            }
        }
    }
}