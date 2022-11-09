package com.miam.kmmMiamCore.component.myMealButton

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState

interface MyMealButtonContract {
    sealed class Event : UiEvent

    data class State(
        val recipeCount: BasicUiState<Int>, // ui state
    ) : UiState

    sealed class Effect : UiEffect
}