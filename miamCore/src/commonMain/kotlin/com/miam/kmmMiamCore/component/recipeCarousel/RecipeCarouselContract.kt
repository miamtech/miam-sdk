package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria


interface RecipeCarouselContract {

    sealed class Event : UiEvent {
        data class SetProductIdAndResultNumber(
            val productId: String,
            val numberOfResult: Int
        ) : RecipeCarouselContract.Event()

        data class SetProductId(val productId: String) : RecipeCarouselContract.Event()
    }

    data class State(
        val productId: BasicUiState<List<SuggestionsCriteria>>,
        val numberOfResult: Int
    ) : UiState

    sealed class Effect : UiEffect
}