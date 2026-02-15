package com.dj.jetpackcomposeinternals.measurements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

fun Modifier.improvedNegativePadding(horizontal: Dp): Modifier {
    return this.then(other = NegativePaddingElement(horizontal = horizontal))
}

data class NegativePaddingElement(
    private val horizontal: Dp,
) : ModifierNodeElement<NegativePaddingNode>() {
    override fun create(): NegativePaddingNode {
        return NegativePaddingNode(horizontal = horizontal)
    }

    override fun update(node: NegativePaddingNode) {
        //node.invalidateMeasurement() // won't change even though horizontal padding changes
        node.horizontal = horizontal // need to update when changed
    }

}

// Modifier Factories
class NegativePaddingNode(
    var horizontal: Dp, // should be able to change from outside
) : LayoutModifierNode, Modifier.Node(), CompositionLocalConsumerModifierNode {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val density =
            currentValueOf(local = LocalDensity) // way to get composable inside nonComposable
        val px = with(density) {
            horizontal.roundToPx()
        }
        val placeable = measurable.measure(
            constraints = constraints.copy(
                minWidth = constraints.minWidth + 2 * px, // left and right, so multiply by 2
                maxWidth = constraints.maxWidth + 2 * px,
            )
        )
        return layout(width = placeable.width, height = placeable.height) {
            placeable.place(x = 0, y = 0)
        }
    }

}

@Composable
fun Modifier.negativePadding(
    horizontal: Dp,
): Modifier {
    val density = LocalDensity.current
    val px = with(density) {
        horizontal.roundToPx()
    }
    return layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints = constraints.copy(
                minWidth = constraints.minWidth + 2 * px, // left and right, so multiply by 2
                maxWidth = constraints.maxWidth + 2 * px,
            )
        )
        layout(width = placeable.width, height = placeable.height) {
            placeable.place(x = 0, y = 0)
        }
    }
}

@Composable
fun MyList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        var toggle by remember {
            mutableStateOf(true)
        }
        Text(
            text = "This is item 1",
            modifier = Modifier
                .clickable {}
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier.improvedNegativePadding(horizontal = 16.dp)
        )
        Text(
            text = "This is another item",
            modifier = Modifier
                .clickable {
                    toggle = !toggle
                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .improvedNegativePadding(
                    horizontal = if (toggle) 16.dp else 32.dp
                )
        )
        HorizontalDivider(
            modifier = Modifier.improvedNegativePadding(horizontal = 16.dp)
        )
        Text(
            text = "Add another one",
            modifier = Modifier
                .clickable {

                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        ) {
            Button(onClick = {}) {
                Text(text = "Button 1")
            }
            Button(onClick = {}) {
                Text(text = "Button 2")
            }
            Button(onClick = {}) {
                Text(text = "Button 3")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposeCustomModifiersPreview() {
    JetpackComposeInternalsTheme {
        MyList()
    }

}