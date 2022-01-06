package com.miam.kmm_miam_sdk.component.recipeCard

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.network.model.Recipe

interface RecipeCardContract {
    sealed class Event : UiEvent {
        data class OnGetRecipe(val idRecipe: Int) : Event()
        object Retry : Event()
    }

    data class State(
        val recipeCard: BasicUiState<Recipe>,
    ) : UiState

    sealed class Effect : UiEffect {
    }
}