package com.miam.kmmMiamCore.component.itemSelector

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine

interface ItemSelectorContract {

    sealed class Event: UiEvent {
        object ReturnToBasketPreview: Event()
        data class SetReturnToBasketPreview(val returnToPreview: () -> Unit): Event()
        data class SetItemList(val items: List<BasketPreviewLine>): Event()
        data class SetReplaceItemInPreview(val replace: (be: BasketEntry) -> Unit): Event()
        data class SelectNewItem(val index: Int): Event()
    }

    data class State(
        val selectedItem: BasicUiState<BasketPreviewLine>,
        val items: List<BasketPreviewLine> = mutableListOf(),
        val replaceItemInPreview: (be: BasketEntry) -> Unit,
        val returnToPreview: () -> Unit
    ): UiState

    sealed class Effect: UiEffect
}

