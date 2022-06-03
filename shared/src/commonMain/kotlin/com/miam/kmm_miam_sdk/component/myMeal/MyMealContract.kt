package com.miam.kmm_miam_sdk.component.myMeal

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.Job

interface MyMealContract {
    sealed class Event : UiEvent {
        data class RemoveRecipe(val recipeId: String): Event()
    }

    data class State(
        val lines: BasicUiState<List<BasketPreviewLine>>, // ui state
        val bpls: List<BasketPreviewLine>?, //service state
        val job : Job?
    ) : UiState

    sealed class Effect : UiEffect {
    }
}