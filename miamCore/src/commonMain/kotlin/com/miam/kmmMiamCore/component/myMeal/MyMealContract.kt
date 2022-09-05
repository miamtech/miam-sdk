package com.miam.kmmMiamCore.component.myMeal

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.Job

interface MyMealContract {
    sealed class Event : UiEvent {
        data class RemoveRecipe(val recipeId: String) : Event()
    }

    data class State(
        val lines: BasicUiState<List<BasketPreviewLine>>, // ui state
        val bpls: List<BasketPreviewLine>?, //service state
        val job: Job?
    ) : UiState

    sealed class Effect : UiEffect
}