package com.miam.kmmMiamCore.component.catalog

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmmMiamCore.miam_core.model.Package

enum class CatalogContent {
    RECIPE_LIST, DEFAULT
}

interface CatalogContract {

    sealed class Event : UiEvent {
        object GoToDefault : CatalogContract.Event()
        object GoToFavorite : CatalogContract.Event()
        object GoToRecipeList : CatalogContract.Event()
        object ToggleFilter : CatalogContract.Event()
        object ToggleSearch : CatalogContract.Event()
        object OnFilterValidation : CatalogContract.Event()
        object OnSearchLaunch : CatalogContract.Event()
        data class GoToRecipeListFromCategory(val categoryId: String, val title: String = "") :
            CatalogContract.Event()
    }

    data class State(
        val categories: BasicUiState<List<Package>>,
        val content: CatalogContent,
        val catalogFilterVM: SingletonFilterViewModel,
        val recipePageVM: RecipeListPageViewModel,
        val filterOpen: Boolean,
        val searchOpen: Boolean
    ) : UiState

    sealed class Effect : UiEffect
}