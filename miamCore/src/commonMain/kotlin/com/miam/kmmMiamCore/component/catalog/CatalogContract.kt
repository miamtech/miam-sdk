package com.miam.kmmMiamCore.component.catalog

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Package

public enum class CatalogContent {
    WORD_SEARCH, FILTER_SEARCH, CATEGORIES_LIST, CATEGORY, FAVORITE
}

public enum class DialogContent {
    FILTER, PREFERENCES, SEARCH
}

public interface CatalogContract {

    public sealed class Event: UiEvent {
        public object GoToFavorite: Event()
        public object GoBack: Event()
    }

    public data class State(
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

    public sealed class Effect: UiEffect
}