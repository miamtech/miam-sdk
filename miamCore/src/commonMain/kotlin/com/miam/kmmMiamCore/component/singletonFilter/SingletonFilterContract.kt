package com.miam.kmmMiamCore.component.singletonFilter

import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions

public interface SingletonFilterContract {

    public sealed class Event : UiEvent {
        public data class OnTimeFilterChanged(val timeFilter: CatalogFilterOptions) : Event()
        public data class OnCostFilterChanged(val costFilter: CatalogFilterOptions) : Event()
        public data class OnDifficultyChanged(val difficulty: CatalogFilterOptions) : Event()
    }

    public data class State(
        val numberOfResult: Int,
        val difficulty: List<CatalogFilterOptions>,
        val cost: List<CatalogFilterOptions>,
        val time: List<CatalogFilterOptions>,
        val searchString: String? = null,
        val isFavorite: Boolean = false,
        val category: String? = null
    ) : UiState

    public sealed class Effect : UiEffect { public object OnUpdate : Effect() }
}