package com.dj.jetpackcomposeinternals.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dj.jetpackcomposeinternals.R
import com.dj.jetpackcomposeinternals.ui.theme.JetpackComposeInternalsTheme

val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope?> { null }

@Composable
fun SharedTransitionAnimation(modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    SharedTransitionLayout(
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    this@Column.AnimatedVisibility(
                        visible = !isExpanded
                    ) {
                        RowListItem(
                            modifier = Modifier.sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "item-layout"),
                                animatedVisibilityScope = this
                            ),
                            animatedVisibilityScope = this,
                            onClick = {
                                isExpanded = !isExpanded
                            },
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    this@Column.AnimatedVisibility(
                        visible = isExpanded
                    ) {
                        ColumnListItem(
                            animatedVisibilityScope = this,
                            modifier = Modifier
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "item-layout"),
                                    animatedVisibilityScope = this
                                )
                        ) { isExpanded = !isExpanded }
                    }
                }

            }
        }
    }
}

@Composable
fun RowListItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
) {
    // example of using scope
    val scope = LocalSharedTransitionScope.current
    with(scope!!) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .height(IntrinsicSize.Min)
                .clickable {
                    onClick()
                },
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.cute), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size = 100.dp)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "image"), // ex) image-$index for real list items
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "List item",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "title"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                )
                Text(
                    text = "This is the list item description.",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "description"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                )
            }
        }
    }

}

@Composable
fun SharedTransitionScope.ColumnListItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.cute), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size = 240.dp)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "image"), // ex) image-$index for real list items
                    animatedVisibilityScope = animatedVisibilityScope,
                )
        )
        Text(
            text = "List item",
            fontSize = 20.sp,
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
        )
        Text(
            text = "This is the list item description.",
            fontSize = 14.sp,
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "description"),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SharedTransitionAnimationPreview() {
    JetpackComposeInternalsTheme {
        SharedTransitionAnimation()
    }
}