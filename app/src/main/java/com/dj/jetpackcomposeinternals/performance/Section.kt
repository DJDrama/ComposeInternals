package com.dj.jetpackcomposeinternals.performance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun KeysCustomLayout(modifier: Modifier = Modifier) {
    var sections by remember {
        mutableStateOf(
            (1..3).map {
                Section(
                    id = it,
                    header = "Section $it Header",
                    description = "Section $it description"
                )
            }
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // static key
        key("static") {
            // Composable()
        }
        for (section in sections) {
            // key for preventing recomposition
            key(section.id) {
                Section(section)
            }
        }
        Button(onClick = {
            sections = sections.shuffled()
        }) {
            Text("Shuffle")
        }
    }
}

data class Section(
    val id: Int,
    val header: String,
    val description: String,
)

@Composable
fun Section(section: Section, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = section.header,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = section.description)
    }
}

@Preview(showBackground = true)
@Composable
private fun KeysCustomLayoutPreview() {
    JetpackComposeInternalsTheme {
        KeysCustomLayout()
    }

}