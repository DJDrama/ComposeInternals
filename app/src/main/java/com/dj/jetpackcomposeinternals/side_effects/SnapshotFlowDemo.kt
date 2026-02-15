package com.dj.jetpackcomposeinternals.side_effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel : ViewModel() {
    // Better than below code
    private val _state = MutableStateFlow(0)
    val state = _state.asStateFlow()

    /* var state by mutableIntStateOf(0)
         private set

     private val myStateAsFlow = snapshotFlow {
         state
     }.stateIn(
         viewModelScope,
         SharingStarted.WhileSubscribed(5000),
         0
     )*/
}

@Composable
fun MyComposable(modifier: Modifier = Modifier) {
    val viewModel = viewModel<MyViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
}