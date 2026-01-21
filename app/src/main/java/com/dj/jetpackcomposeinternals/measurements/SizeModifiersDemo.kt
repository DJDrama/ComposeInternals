package com.dj.jetpackcomposeinternals.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.measurements.utility.printConstraints
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun SizeModifiersDemo(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .printConstraints("Before 1. fillMaxWidth")
                //.fillMaxWidth(0.5f)
                .requiredWidth(300.dp)
                .background(Color.Green)
                .wrapContentWidth(
                    align = Alignment.CenterHorizontally
                )
                .printConstraints("After 1. fillMaxWidth")
                .background(Color.Yellow),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text("Hello world! this is a text!")
        }
//        Box(
//            modifier = Modifier
//                .height(100.dp)
//                .printConstraints("Before 2. fillMaxWidth")
//                //.fillMaxWidth(0.5f)
//                .requiredWidth(300.dp)
//                .wrapContentWidth()
//                .printConstraints("After 2. fillMaxWidth")
//                .background(Color.Green),
//            contentAlignment = Alignment.CenterEnd
//        ){
//            Text("Hello world! this is a text!")
//        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SizeModifiersDemoPreview() {
    JetpackComposeInternalsTheme {
        SizeModifiersDemo()
    }

}