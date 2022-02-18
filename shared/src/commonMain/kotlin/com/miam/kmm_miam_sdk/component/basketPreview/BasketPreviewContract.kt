package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine



import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry


interface BasketPreviewContract {

    sealed class Event : UiEvent {
        data class SetRecipeId(val newRecipeId: Int):  Event()
        data class SetLines(val newlines :List<BasketPreviewLine>):Event()
        data class BuildEntriesLines(val basketEntries: List<BasketEntry>):Event()
        object toogleLine :Event()
    }

    data class State(
        val recipeId: Int?,
        val showLines: Boolean,
        val lines: List<BasketPreviewLine>
    ) : UiState

    sealed class Effect : UiEffect {}
}