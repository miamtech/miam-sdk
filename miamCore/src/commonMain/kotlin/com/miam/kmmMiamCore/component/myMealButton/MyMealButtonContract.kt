package com.miam.kmmMiamCore.component.myMealButton

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState

public interface MyMealButtonContract {
    public sealed class Event : UiEvent

    public data class State(
        val recipeCount: BasicUiState<Int>, // ui state
    ) : UiState

    public sealed class Effect : UiEffect
}