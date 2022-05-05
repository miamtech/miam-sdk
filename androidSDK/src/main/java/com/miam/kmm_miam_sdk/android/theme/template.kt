package com.miam.kmm_miam_sdk.android.theme

import androidx.compose.runtime.Composable
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

object Template {

    /**
     * recipe allow you to access all recipe object property as image, title ...
     * vmRecipe is a view controller share between recipeCard and recipeDetail, you'll find state of the recipe
     * such as if the recipe is loading or if it's already in basket , also function like increment or decrement guest count
     * call look function 'll open recipe detail without adding recipe to basket
     * call buy function 'll open basket preview and add recipe to basket if it not already in
     */
    var recipeCardTemplate : (
    @Composable() (
        recipe: Recipe,
        vmRecipe: RecipeViewModel,
        look : () -> Unit,
        buy : () -> Unit
        ) -> Unit
    )? = null

/////////// Favorite Page //////////////////////////

    var loadingFavoritePage : (
    @Composable() () -> Unit
    )? = null

    var emptyFavoritePage : (
    @Composable() (
        visitCatalog : () -> Unit,
    ) -> Unit
    )? = null

}