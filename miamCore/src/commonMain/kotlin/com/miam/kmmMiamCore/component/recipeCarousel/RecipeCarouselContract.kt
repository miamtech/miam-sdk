package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe


interface RecipeCarouselContract {

    sealed class Event : UiEvent {
        data class GetSuggestionsFromIdAndSize(val productId: String, val numberOfResult: Int) : RecipeCarouselContract.Event()
        data class GetSuggestionFromId(val productId: String) : RecipeCarouselContract.Event()
    }

    data class State(
        val suggestions: BasicUiState<List<Recipe>>
    ) : UiState

    sealed class Effect : UiEffect
}