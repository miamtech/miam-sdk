package com.miam.kmmMiamCore.component.favoritePage

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe

public interface FavoritePageContract {

    public sealed class Event : UiEvent {
        public object LoadPage : FavoritePageContract.Event()
    }

    public data class State(
        val favoritesRecipes: BasicUiState<List<Recipe>>,
        val currentPage: Int,
        val isFetchingNewPage: Boolean,
        val noMoreData: Boolean
    ) : UiState

    public sealed class Effect : UiEffect

}