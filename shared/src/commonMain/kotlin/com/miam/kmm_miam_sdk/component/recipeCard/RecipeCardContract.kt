package com.miam.kmm_miam_sdk.component.recipeCard

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.network.model.Recipe

interface RecipeCardContract {

    sealed class Event : UiEvent {
        data class OnGetRecipe(val idRecipe: Int) : Event()
        data class setHeader(val header: String) : Event()
        object addRecipe: Event()
        object Retry : Event()
    }

      data class State(
        val recipeCard: BasicUiState<Recipe>,
        val headerText: String,
        val analyticsEventSent: Boolean,
        val isPriceDisplayed: Boolean,
        val isInViewport : Boolean,
    ) : UiState

    sealed class Effect : UiEffect {
        object HideCard : Effect()
    }
}