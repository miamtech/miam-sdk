package com.miam.kmmMiamCore.component.recipeLike

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import kotlinx.coroutines.Job

public interface RecipeLikeContract {
    public sealed class Event: UiEvent

    public data class State(
        val recipeId: String? = null,
        val isLiked: BasicUiState<Boolean> = BasicUiState.Loading,
        val likeListenerJob: Job? = null
    ): UiState

    public sealed class Effect: UiEffect
}