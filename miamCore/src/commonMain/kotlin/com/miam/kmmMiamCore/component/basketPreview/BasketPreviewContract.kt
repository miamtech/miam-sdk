package com.miam.kmmMiamCore.component.basketPreview

import com.miam.kmmMiamCore.base.mvi.*
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine


import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import kotlinx.coroutines.Job


interface BasketPreviewContract {

    sealed class Event : UiEvent {
        data class SetRecipeId(val newRecipeId: Int):  Event()
        data class SetLines(val newlines: BasketPreviewLine):Event()
        data class AddGuest(val recipeVm: RecipeViewModel): Event()
        data class RemoveGuest(val recipeVm: RecipeViewModel): Event()
        data class AddEntry(val entry: BasketEntry):Event()
        data class RemoveEntry(val entry: BasketEntry):Event()
        data class UpdateBasketEntry(val entry: BasketEntry,val finalQty: Int): Event()
        data class ReplaceItem(val entry: BasketEntry, val itemId: Int):Event()
        data class OpenItemSelector(val bpl: BasketPreviewLine): Event()
        data class RemoveRecipe(val recipeId: String): Event()
        object CloseItemSelector: Event()
        object KillJob: Event()
        object ToggleLine :Event()
        object Reload: Event()
    }

    data class State(
        val recipeId: Int?,
        val showLines: Boolean,
        val line: BasicUiState<BasketPreviewLine>, // ui state
        val lineUpdates: List<BasketEntry>,
        val bpl: BasketPreviewLine?, //service state
        val isReloading: Boolean,
        val job: Job?
    ) : UiState

    sealed class Effect : UiEffect {}
}