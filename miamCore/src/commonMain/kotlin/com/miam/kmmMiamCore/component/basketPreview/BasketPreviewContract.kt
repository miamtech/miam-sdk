package com.miam.kmmMiamCore.component.basketPreview


import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.Job


public interface BasketPreviewContract {

    public sealed class Event: UiEvent {
        public data class SetRecipeId(val newRecipeId: Int): Event()
        public data class SetLines(val newlines: BasketPreviewLine): Event()
        public data class AddEntry(val entry: BasketEntry): Event()
        public data class ReplaceItem(val entry: BasketEntry, val itemId: Int): Event()
        public data class OpenItemSelector(val bpl: BasketPreviewLine): Event()
        public data class RemoveRecipe(val recipeId: String): Event()
        public object CloseItemSelector: Event()
        public object KillJob: Event()
        public object ToggleLine: Event()
    }

    public data class State(
        val recipeId: Int?,
        val showLines: Boolean,
        val line: BasicUiState<BasketPreviewLine>, // ui state
        val bpl: BasketPreviewLine?, //service state
        val isReloading: Boolean,
        val updatingBasketEntryId: String? = null,
        val job: Job?
    ): UiState

    public sealed class Effect: UiEffect
}