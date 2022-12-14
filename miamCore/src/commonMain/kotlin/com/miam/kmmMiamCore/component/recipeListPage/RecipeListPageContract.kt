package com.miam.kmmMiamCore.component.recipeListPage

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState

import com.miam.kmmMiamCore.miam_core.model.Recipe


interface RecipeListPageContract {

    sealed class Event : UiEvent {
        object LoadPage : RecipeListPageContract.Event()
        data class InitPage(val title: String) : RecipeListPageContract.Event()
    }

    data class State(
        val recipes: BasicUiState<List<Recipe>>,
        val title: String,
        val filter: String,
        val currentPage: Int,
        val isFetchingNewPage: Boolean,
        val noMoreData: Boolean
    ) : UiState

    sealed class Effect : UiEffect
}