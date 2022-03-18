package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine


import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.LineEntries


interface BasketPreviewContract {

    sealed class Event : UiEvent {
        data class SetRecipeId(val newRecipeId: Int):  Event()
        data class SetLines(val newlines: BasketPreviewLine):Event()
        data class BuildEntriesLines(val bpl: BasketPreviewLine):Event()
        data class UpdateEntriesLines(val bpl: BasketPreviewLine):Event()
        data class CountChange(val bpl: BasketPreviewLine, val recipeVm: RecipeViewModel): Event()
        data class AddEntry(val entry: BasketEntry):Event()
        data class RemoveEntry(val entry: BasketEntry):Event()
        data class UpdateBasketEntry(val entry: BasketEntry,val finalQty: Int): Event()
        data class ReplaceItem(val entry: BasketEntry, val itemId: Int):Event()
        data class OpenItemSelector(val bpl: BasketPreviewLine): Event()
        object CloseItemSelector: Event()
        object ToggleLine :Event()
        object Reload: Event()
    }

    data class State(
        val recipeId: Int?,
        val showLines: Boolean,
        val line: BasicUiState<BasketPreviewLine>, // ui state
        val bpl: BasketPreviewLine?, //service state
        val isReloading: Boolean,
        val isFillingEntry: Boolean,
        val firstEntriesBuildDone : Boolean,
        val showItemSelector: Boolean
    ) : UiState

    sealed class Effect : UiEffect {}
}