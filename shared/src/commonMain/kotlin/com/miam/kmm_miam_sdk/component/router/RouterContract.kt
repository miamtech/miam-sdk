package com.miam.kmm_miam_sdk.component.router

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel

enum class RouterContent {
    RECIPE_DETAIL, RECIPE_HELPER, RECIPE_SPONSOR, BASKET_PREVIEW , ITEMS_SELECTOR
}

interface RouterContract {

        sealed class Event : UiEvent {
            object GoToHelper: RouterContract.Event()
            object GoToSponsor: RouterContract.Event()
            object OpenDialog : RouterContract.Event()
            object CloseDialog : RouterContract.Event()
            object GoToItemSelector: RouterContract.Event()
            data class GoToPreview(val recipeId: Int, val vm: RecipeViewModel): RouterContract.Event()
            data class GoToDetail (val vm: RecipeViewModel) :RouterContract.Event()
        }

        data class State(
            val content: RouterContent,
            val vm: RecipeViewModel?,
            val recipeId: Int?,
            val isOpen: Boolean
        ) : UiState

        sealed class Effect : UiEffect {
        }
}

