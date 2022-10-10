package com.miam.kmm_miam_sdk.android.ui.components.recipeCarousel

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeLoadingView

@Composable
fun RecipeCarouselLoadingView(size: Int) {

    val scrollState = rememberScrollState()

    Row(modifier = Modifier.horizontalScroll(scrollState)) {
        for (i in 1..size) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(380.dp)
            ) {
                RecipeLoadingView()
            }
        }
    }
}