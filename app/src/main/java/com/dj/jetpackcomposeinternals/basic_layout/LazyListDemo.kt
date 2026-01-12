@file:OptIn(ExperimentalFoundationApi::class)

package com.dj.jetpackcomposeinternals.basic_layout

import android.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme
import kotlin.random.Random

@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()/*.padding(16.dp)*/
            .background(Color.Red),
         // reverseLayout = true, // for chat
        verticalArrangement = Arrangement.spacedBy(space=8.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //SampleOne()
        items(100) {
            Text("Item $it")
        }
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(100) {
            Box(
                modifier = Modifier.size(100.dp)
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

fun LazyListScope.SampleOne(modifier: Modifier = Modifier) {
    stickyHeader {
        Text(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue),
            text = "Sticky Header!",
            color = Color.White
        )
    }
    items(100) {
        Text("Item $it")
    }
    stickyHeader {
        Text(
            modifier = Modifier.fillMaxWidth().background(color = Color.Green),
            text = "Sticky Header2!",
            color = Color.White
        )
    }
    items(100) {
        Text("Item $it")
    }
    item {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Reached the end!",
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LazyListDemoPreview() {
    JetpackComposeInternalsTheme {
        LazyListDemo()
    }
}