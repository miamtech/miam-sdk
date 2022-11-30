package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria


interface RecipeCarouselContract {

    sealed class Event : UiEvent {
        data class GetRecipeSuggestionsFromId(val productId: String, val numberOfResult: Int = 4) : Event()
        data class GetRecipeSuggestionsFromCriteria(val criteria: SuggestionsCriteria, val numberOfResult: Int = 4) : Event()
    }

    data class State(val suggestions: BasicUiState<List<Recipe>>) : UiState

    sealed class Effect : UiEffect
}