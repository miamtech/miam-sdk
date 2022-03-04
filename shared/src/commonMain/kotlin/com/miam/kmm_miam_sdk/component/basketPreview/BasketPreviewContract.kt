package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine



import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry


interface BasketPreviewContract {

    sealed class Event : UiEvent {
        data class SetRecipeId(val newRecipeId: Int):  Event()
        data class SetLines(val newlines :BasketPreviewLine):Event()
        data class BuildEntriesLines(val bpl: BasketPreviewLine):Event()
        data class CountChange(val bpl: BasketPreviewLine, val recipeVm:RecipeViewModel): Event()
        data class AddEntry(val entry:BasketEntry):Event()
        data class RemoveEntry(val entry:BasketEntry):Event()
        data class UpdateBasketEntry(val entry: BasketEntry,val deltaQty:Int): Event()
        object ToogleLine :Event()
        object Reload: Event()
    }

    data class State(
        val recipeId: Int?,
        val showLines: Boolean,
        val line: BasicUiState<BasketPreviewLine>,
        val isReloading: Boolean,
        val isFillingEntry: Boolean
    ) : UiState

    sealed class Effect : UiEffect {}
}