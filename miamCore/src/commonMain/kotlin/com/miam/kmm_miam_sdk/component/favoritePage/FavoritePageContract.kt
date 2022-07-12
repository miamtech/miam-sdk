package com.miam.kmm_miam_sdk.component.favoritePage

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

interface FavoritePageContract{

    sealed class Event : UiEvent {
        object LoadPage: FavoritePageContract.Event()
    }

    data class State(
        val favoritesRecipes: BasicUiState<List<Recipe>>,
        val currentPage: Int,
        val isFetchingNewPage: Boolean,
        val noMoreData: Boolean
    ) : UiState

    sealed class Effect : UiEffect {
    }

}