package com.miam.kmm_miam_sdk.component.catalog

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.component.catalogFilter.CatalogFilterViewModel
import com.miam.kmm_miam_sdk.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Package

enum class CatalogContent {
    RECIPE_LIST, DEFAULT
}

interface CatalogContract {

    sealed class Event : UiEvent {
        object GoToDefault: CatalogContract.Event()
        object GoToFavorite: CatalogContract.Event()
        object GoToRecipeList: CatalogContract.Event()
        object ToggleFilter : CatalogContract.Event()
        object ToggleSearch : CatalogContract.Event()
        object OnFilterValidation  : CatalogContract.Event()
        object OnSearchLaunch: CatalogContract.Event()
        data class GoToRecipeListFromCategory(val category: Package): CatalogContract.Event()
    }

    data class State(
        val categories: BasicUiState<List<Package>>,
        val content: CatalogContent,
        val catalogFilterVM: CatalogFilterViewModel,
        val recipePageVM : RecipeListPageViewModel,
        val filterOpen: Boolean,
        val searchOpen:Boolean
    ) : UiState

    sealed class Effect : UiEffect {}
}