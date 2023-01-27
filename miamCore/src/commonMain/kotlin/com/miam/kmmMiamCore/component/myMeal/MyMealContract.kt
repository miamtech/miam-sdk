package com.miam.kmmMiamCore.component.myMeal

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.Job

public interface MyMealContract {
    public sealed class Event : UiEvent {
        public data class RemoveRecipe(val recipeId: String) : Event()
    }

    public data class State(
        val lines: BasicUiState<List<BasketPreviewLine>>, // ui state
        val bpls: List<BasketPreviewLine>?, //service state
        val job: Job?
    ) : UiState

    public sealed class Effect : UiEffect
}