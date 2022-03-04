package com.miam.kmm_miam_sdk.android.ui.components.common


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnnimatedShimmer(){

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
        end= Offset(
                x= translateAnimation.value,
                y=translateAnimation.value
        )
    )

    shimmerGridItem(brush)
}

@Composable
fun shimmerGridItem(brush: Brush){
    
    Row(
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)) {
        Spacer(modifier = Modifier.padding(30.dp))
        Spacer(modifier = Modifier
            .height(72.dp)
            .width(72.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(brush = brush))
        Spacer(modifier = Modifier.padding(8.dp))
        
        Column() {
            Spacer(modifier = Modifier.height(16.dp).clip(RoundedCornerShape(8.dp)).fillMaxWidth(fraction = 0.7f).background(brush = brush))
            Spacer(modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.width(16.dp).clip(RoundedCornerShape(8.dp)).fillMaxWidth(fraction = 0.9f).background(brush = brush))
            Row( Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.width(16.dp).clip(RoundedCornerShape(8.dp)).fillMaxWidth(fraction = 0.4f).background(brush = brush))
                Spacer(modifier = Modifier.width(16.dp).clip(RoundedCornerShape(8.dp)).fillMaxWidth(fraction = 0.4f).background(brush = brush))
            }
        }
    }

}