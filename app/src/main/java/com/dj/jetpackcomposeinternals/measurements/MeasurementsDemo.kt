package com.dj.jetpackcomposeinternals.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

// clear boundaries => bounded
private val boundedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 100,
    minHeight = 70,
    maxHeight = 150
)

// no boundaries => unbounded (Default)
private val unboundedConstraints = Constraints()

// exact
private val exactConstraints = Constraints(
    minWidth = 50,
    maxWidth = 50,
    minHeight = 100,
    maxHeight = 100
)

// height is bounded, can grow
private val combinedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 50,
    minHeight = 50,
    maxHeight = Constraints.Infinity
)

@Composable
fun MeasurementsDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(Color.Red)
            .padding(16.dp)
    ) {
        Text(
            "This is a text",
            modifier = Modifier.background(Color.Yellow)
        )
        Text(
            "This is another text",
            modifier = Modifier.background(Color.Green)
        )
    }
}

@Preview
@Composable
private fun MeasurementsDemoPreview() {
    JetpackComposeInternalsTheme {
        MeasurementsDemo()
    }

}