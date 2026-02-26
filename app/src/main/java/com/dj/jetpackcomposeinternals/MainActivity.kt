package com.dj.jetpackcomposeinternals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dj.jetpackcomposeinternals.performance.MovableContent
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInternalsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    MovableContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    /*KeysCustomLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )*/
                    /*  PhotoPickerScreen(compressor = remember {
                          BitmapCompressor(context = applicationContext)
                      }, modifier = Modifier
                          .fillMaxSize()
                          .padding(innerPadding))*/
                    /*   LazyListPerformance(
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(innerPadding))*/
                    /* var toggle by remember {
                         mutableStateOf(false)
                     }
                     if (!toggle)
                         DisposableEffectDemo()
                     Button(
                         onClick = {
                             toggle = !toggle
                         }, modifier = Modifier
                             .padding(innerPadding)
                             .fillMaxSize()
                             .wrapContentSize()
                     ) {
                         Text("Toggle")
                     }*/
                    /*val mindMapItems = remember {
                        listOf(
                            MindMapItem(
                                title = "Hello World 1",
                                percentageOffset = Offset(0f, 0f)
                            ),
                            MindMapItem(
                                title = "Hello World 2",
                                percentageOffset = Offset(1f, 0f)
                            ),
                            MindMapItem(
                                title = "Hello World 3",
                                percentageOffset = Offset(0.3f, -0.5f)
                            ),
                            MindMapItem(
                                title = "Hello World 4",
                                percentageOffset = Offset(-0.2f, 1.5f)
                            ),

                            )
                    }
                    var mindMapOffset by remember {
                        mutableStateOf(IntOffset.Zero)
                    }
                    LazyMindMap(
                        items = mindMapItems,
                        mindMapOffset = mindMapOffset,
                        onDrag = { delta ->
                            mindMapOffset += delta
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )*/
                    /* var page by remember {
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
                     }*/

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
