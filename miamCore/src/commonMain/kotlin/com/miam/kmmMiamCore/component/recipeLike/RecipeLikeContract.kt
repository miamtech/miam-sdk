package com.miam.kmmMiamCore.component.recipeLike

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState

interface RecipeLikeContract {
    sealed class Event: UiEvent

    data class State(
        val recipeId: String? = null,
        val isLiked: BasicUiState<Boolean> = BasicUiState.Loading
    ): UiState

    sealed class Effect: UiEffect
}