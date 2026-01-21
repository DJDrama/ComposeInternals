package com.dj.jetpackcomposeinternals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dj.jetpackcomposeinternals.basic_modifiers.FocusManagementModifiers
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInternalsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeGestures
                ) { innerPadding ->
                    FocusManagementModifiers()
//                    SpacingModifierDemo(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .consumeWindowInsets(
//                                innerPadding
//                            )
//                        modifier = Modifier
//                            .statusBarsPadding()
//                            .navigationBarsPadding()
//                            .safeGesturesPadding()
//                            .safeDrawingPadding()
//                            .safeContentPadding()
                    //)
                }
            }
        }
    }
}
