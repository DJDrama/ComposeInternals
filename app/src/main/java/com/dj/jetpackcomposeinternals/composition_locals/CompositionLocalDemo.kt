@file:OptIn(ExperimentalMaterial3Api::class)

package com.dj.jetpackcomposeinternals.composition_locals

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun CompositionLocalDemo(modifier: Modifier = Modifier) {
    /* LocalConfiguration.current.layoutDirection // get layoutDirection
    LocalConfiguration.current.setLocale() // set locale(language)
    */
    // LocalContext.current // get context
    // LocalClipboard.current // copy text
    // LocalSoftwareKeyboardController.current // show hide keyboard

    val textStyle = LocalTextStyle.current

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red // changes the LocalContentColor for the inside "RowScope"
        )
    ) {
        CompositionLocalProvider(value = LocalContentColor provides Color.Green) {
            val contentColor = LocalContentColor.current // refers to Green
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check",
            )
        }
        Text(text = "Hello World")
    }
}

@Composable
fun MyCustomTopAppBar(modifier: Modifier = Modifier, title: @Composable () -> Unit) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            )
        ) { }
        title()
    }

}

@Preview(showBackground = true)
@Composable
private fun CompositionLocalDemoPreview() {
    JetpackComposeInternalsTheme {
        //CompositionLocalDemo()
        MyCustomTopAppBar(title = {
            Text("Hello World")
        })
    }

}