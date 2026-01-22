package com.dj.jetpackcomposeinternals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.measurements.SubcomposePagedRow
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInternalsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    var page by remember {
                        mutableIntStateOf(0)
                    }
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        SubcomposePagedRow(
                            page = 0,
                        ) {
                            (1..1000).forEach {
                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(Random.nextInt(300).dp)
                                        .background(Color(Random.nextInt()))
                                )
                            }
                        }
                        Button(onClick = {
                            page++
                        }) {
                            Text("Go to next page")
                        }
                    }

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
