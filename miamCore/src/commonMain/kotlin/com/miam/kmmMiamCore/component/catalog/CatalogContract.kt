package com.miam.kmmMiamCore.component.catalog

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Package

enum class CatalogContent {
    WORD_SEARCH, FILTER_SEARCH, CATEGORIES_LIST, CATEGORY, FAVORITE
}

enum class DialogContent {
    FILTER, PREFERENCES, SEARCH
}

interface CatalogContract {

    sealed class Event: UiEvent {
        object GoToFavorite: Event()
        object GoBack: Event()
    }

    data class State(
        val categories: BasicUiState<List<Package>>,
        val content: CatalogContent,
        val dialogIsOpen: Boolean,
        val dialogContent: DialogContent,
        val enableFilters: Boolean,
        val enablePreferences: Boolean,
        val openedCategoryId: String,
        val openedCategoryTitle: String,
        val subtitle: String? = null
    ): UiState

    sealed class Effect: UiEffect
}