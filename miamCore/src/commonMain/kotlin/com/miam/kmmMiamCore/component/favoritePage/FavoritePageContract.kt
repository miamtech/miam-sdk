package com.miam.kmmMiamCore.component.favoritePage

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe

interface FavoritePageContract {

    sealed class Event : UiEvent {
        object LoadPage : FavoritePageContract.Event()
        data class RemoveIndex(val index: Int) : FavoritePageContract.Event()
    }

    data class State(
        val favoritesRecipes: BasicUiState<List<Recipe>>,
        val currentPage: Int,
        val isFetchingNewPage: Boolean,
        val noMoreData: Boolean
    ) : UiState

    sealed class Effect : UiEffect

}