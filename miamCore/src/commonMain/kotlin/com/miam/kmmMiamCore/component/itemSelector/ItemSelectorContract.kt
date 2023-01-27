package com.miam.kmmMiamCore.component.itemSelector

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine

public interface ItemSelectorContract {

    public sealed class Event: UiEvent {
        public object ReturnToBasketPreview: Event()
        public data class SetReturnToBasketPreview(val returnToPreview: () -> Unit): Event()
        public data class SetItemList(val items: List<BasketPreviewLine>): Event()
        public data class SetReplaceItemInPreview(val replace: (be: BasketEntry) -> Unit): Event()
        // TODO Romain: public data class SelectNewItem(val index: Int): Event()
    }

    public data class State(
        val selectedItem: BasicUiState<BasketPreviewLine>,
        val items: List<BasketPreviewLine> = mutableListOf(),
        val replaceItemInPreview: (be: BasketEntry) -> Unit,
        val returnToPreview: () -> Unit
    ): UiState

    public sealed class Effect: UiEffect
}

