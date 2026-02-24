@file:OptIn(ExperimentalAnimatableApi::class)

package com.dj.jetpackcomposeinternals.animations

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ApproachLayoutModifierNode
import androidx.compose.ui.layout.ApproachMeasureScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

@Composable
fun LookaheadLayoutAnimations(modifier: Modifier = Modifier) {
    var horizontalArrangement by remember {
        mutableStateOf(Arrangement.Start)
    }
    var dpIncrement by remember {
        mutableStateOf(0.dp)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AnimatedFlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = horizontalArrangement
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp + dpIncrement)
                    .animateLayoutChanges(this)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(100.dp + dpIncrement)
                    .animateLayoutChanges(this)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(100.dp + dpIncrement)
                    .animateLayoutChanges(this)
                    .background(Color.Blue)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                horizontalArrangement = Arrangement.Start
            }) {
                Text(text = "Start")
            }
            Button(onClick = {
                horizontalArrangement = Arrangement.Center
            }) {
                Text(text = "Center")
            }
            Button(onClick = {
                horizontalArrangement = Arrangement.End
            }) {
                Text(text = "End")
            }
            Button(onClick = {
                horizontalArrangement = Arrangement.SpaceBetween
            }) {
                Text(text = "SpaceBetween")
            }
            Button(onClick = {
                horizontalArrangement = Arrangement.SpaceAround
            }) {
                Text(text = "SpaceAround")
            }
            Button(onClick = {
                horizontalArrangement = Arrangement.SpaceEvenly
            }) {
                Text(text = "SpaceEvenly")
            }
            Button(onClick = {
                dpIncrement -= 10.dp
            }) {
                Text(text = "Dec DP")
            }
            Button(onClick = {
                dpIncrement += 10.dp
            }) {
                Text(text = "Inc DP")
            }
        }
    }
}

@Composable
fun AnimatedFlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable LookaheadScope.() -> Unit,
) {
    LookaheadScope {

        FlowRow(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = horizontalArrangement
        ) {
            content()
        }
    }
}

fun Modifier.animateLayoutChanges(
    scope: LookaheadScope,
    positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> = DeferredTargetAnimation(
        IntOffset.VectorConverter
    ),
    sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D> = DeferredTargetAnimation(
        IntSize.VectorConverter
    ),
): Modifier {
    return then(
        AnimateLayoutChangesElement(
            scope,
            positionAnimation,
            sizeAnimation
        )
    )
}

@OptIn(ExperimentalAnimatableApi::class)
data class AnimateLayoutChangesElement(
    private val scope: LookaheadScope,
    private val positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> = DeferredTargetAnimation(
        IntOffset.VectorConverter
    ),
    private val sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D> = DeferredTargetAnimation(
        IntSize.VectorConverter
    ),
) : ModifierNodeElement<AnimateLayoutChangesNode>() {
    override fun create(): AnimateLayoutChangesNode {
        return AnimateLayoutChangesNode(
            scope = scope,
            positionAnimation = positionAnimation,
            sizeAnimation = sizeAnimation
        )
    }

    override fun update(node: AnimateLayoutChangesNode) = Unit

}

@OptIn(ExperimentalAnimatableApi::class)
class AnimateLayoutChangesNode(
    private val scope: LookaheadScope,
    private val positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D>,
    private val sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D>,
) : ApproachLayoutModifierNode, Modifier.Node() {
    override fun Placeable.PlacementScope.isPlacementApproachInProgress(lookaheadCoordinates: LayoutCoordinates): Boolean {
        val targetOffset = with(scope) {
            lookaheadScopeCoordinates.localLookaheadPositionOf(lookaheadCoordinates)
        }
        positionAnimation.updateTarget(targetOffset.round(), coroutineScope, tween(1_500))
        return !positionAnimation.isIdle // false: in progress
    }

    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean {
        sizeAnimation.updateTarget(lookaheadSize, coroutineScope)
        return !sizeAnimation.isIdle // false: in progress
    }

    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val (width, height) = sizeAnimation.updateTarget(
            target = lookaheadSize,
            coroutineScope = coroutineScope,
            animationSpec = tween(1_500)
        )
        val animatedConstraints = Constraints.fixed(width, height)
        val placeable = measurable.measure(animatedConstraints)
        return with(scope) {
            layout(placeable.width, placeable.height) {
                coordinates?.let {
                    val targetOffset = lookaheadScopeCoordinates.localLookaheadPositionOf(it)
                    val animatedOffset = positionAnimation.updateTarget(
                        target = targetOffset.round(),
                        coroutineScope = coroutineScope,
                        animationSpec = tween(1_500)
                    )
                    val currentOffset = lookaheadScopeCoordinates.localPositionOf(
                        sourceCoordinates = it
                    )
                    val (x, y) = animatedOffset - currentOffset.round()
                    placeable.place(x = x, y = y)
                } ?: placeable.place(x = 0, y = 0)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun LookaheadLayoutAnimationsPreview() {
    JetpackComposeInternalsTheme {
        LookaheadLayoutAnimations()
    }

}