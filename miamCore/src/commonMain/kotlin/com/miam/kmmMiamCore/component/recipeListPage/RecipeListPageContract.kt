package com.miam.kmmMiamCore.component.recipeListPage

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState

import com.miam.kmmMiamCore.miam_core.model.Recipe


public interface RecipeListPageContract {

    public sealed class Event: UiEvent {
        public object LoadPage: Event()
        public data class InitPage(val title: String): Event()
    }

    public data class State(
        val recipes: BasicUiState<List<Recipe>>,
        val title: String,
        val filter: Map<String, String>,
        val currentPage: Int,
        val isFetchingNewPage: Boolean,
        val noMoreData: Boolean
    ): UiState

    public sealed class Effect: UiEffect
}