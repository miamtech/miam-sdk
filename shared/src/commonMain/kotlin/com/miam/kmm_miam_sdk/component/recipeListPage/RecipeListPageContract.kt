package com.miam.kmm_miam_sdk.component.recipeListPage

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState

import com.miam.kmm_miam_sdk.miam_core.model.Recipe


interface RecipeListPageContract {

    sealed class Event : UiEvent {
        object LoadPage: RecipeListPageContract.Event()
        data class InitPage(val title: String,val filter: String) : RecipeListPageContract.Event()
    }

    data class State(
        val recipes: BasicUiState<List<Recipe>>,
        val title: String,
        val filter: String,
        val currentPage: Int,
        val isFetchingNewPage: Boolean,
        val noMoreData: Boolean
    ) : UiState

    sealed class Effect : UiEffect {}
}