package com.dj.jetpackcomposeinternals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dj.jetpackcomposeinternals.measurements.SizeModifiersDemo
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInternalsTheme {
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    contentWindowInsets = WindowInsets.safeGestures
//                ) { innerPadding ->
                SizeModifiersDemo()
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
                //            }
            }
        }
    }
}
