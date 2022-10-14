package com.miam.kmm_miam_sdk.android.ui.components.recipeCarousel

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView

@Composable
fun RecipeCarouselSuccessView(suggestions: List<Recipe>, context: Context) {

    Column(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = suggestions
            ) { recipe ->
                Box(
                    modifier = Modifier
                        .width(350.dp)
                        .height(380.dp)
                ) {
                    val recipeCard = RecipeView(context)
                    recipeCard.bind(recipe = recipe)
                    recipeCard.Content()
                }
            }
        }
    }
}