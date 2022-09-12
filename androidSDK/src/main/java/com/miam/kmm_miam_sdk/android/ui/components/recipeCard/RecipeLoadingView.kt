package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RecipeLoadingView() {

    val shimerColors = listOf(
        Color.LightGray.copy(alpha = 0.6F),
        Color.LightGray.copy(alpha = 0.2F),
        Color.LightGray.copy(alpha = 0.6F)
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimerColors,
        start = Offset.Zero,
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        shimmerRecipeCard(brush)
    }
}

@Composable
fun shimmerRecipeCard(brush: Brush) {

    Column(Modifier.height(330.dp)) {
        Spacer(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(brush = brush)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center

        ) {
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .height(14.dp)
                    .width(200.dp)
                    .clip(RoundedCornerShape(100))
                    .background(brush = brush)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .height(20.dp)
                    .width(30.dp)
                    .clip(RoundedCornerShape(100))
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(30.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .height(20.dp)
                    .width(30.dp)
                    .clip(RoundedCornerShape(100))
                    .background(brush = brush)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(30.dp)
                    .width(200.dp)
                    .clip(RoundedCornerShape(100))
                    .background(brush = brush)
            )
        }
    }
}

