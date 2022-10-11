package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe


interface RecipeCarouselContract {

    sealed class Event : UiEvent {
        data class GetRecipeSuggestions(val productId: String, val numberOfResult: Int?) : Event()
    }

    data class State(val suggestions: BasicUiState<List<Recipe>>) : UiState

    sealed class Effect : UiEffect
}