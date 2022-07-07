package com.miam.kmm_miam_sdk.component.catalogFilter

import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.CatalogFilterOptions

interface CatalogFilterContract {

    sealed class Event : UiEvent {
        data class OnTimeFilterChanged(val timeFilter: CatalogFilterOptions): CatalogFilterContract.Event()
        data class OnCostFilterChanged(val costFilter: CatalogFilterOptions): CatalogFilterContract.Event()
        data class OnDifficultyChanged(val difficulty: CatalogFilterOptions): CatalogFilterContract.Event()
        data class SetCategoryTitle(val catTitle:String): CatalogFilterContract.Event()
        data class SetSearchString(val searchString: String): CatalogFilterContract.Event()
        object SetFavorite: CatalogFilterContract.Event()

    }

    data class State(
        val numberOfResult : Int,
        val difficulty: List<CatalogFilterOptions>,
        val cost :  List<CatalogFilterOptions> ,
        val time :  List<CatalogFilterOptions> ,
        val searchString: String? = null,
        val isFavorite: Boolean = false,
        val category: String? = null
    ) : UiState

    sealed class Effect : UiEffect {}
}