package com.miam.kmmMiamCore.component.catalogFilter

import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions

interface CatalogFilterContract {

    sealed class Event : UiEvent {
        data class OnTimeFilterChanged(val timeFilter: CatalogFilterOptions) : Event()
        data class OnCostFilterChanged(val costFilter: CatalogFilterOptions) : Event()
        data class OnDifficultyChanged(val difficulty: CatalogFilterOptions) : Event()

    }

    data class State(
        val numberOfResult: Int,
        val difficulty: List<CatalogFilterOptions>,
        val cost: List<CatalogFilterOptions>,
        val time: List<CatalogFilterOptions>,
        val searchString: String? = null,
        val isFavorite: Boolean = false,
        val category: String? = null
    ) : UiState

    sealed class Effect : UiEffect {
        object OnUpdate : Effect()
    }
}