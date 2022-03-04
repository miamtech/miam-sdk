package com.miam.kmm_miam_sdk.component.itemSelector

import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.miam_core.model.Item

interface ItemSelectorContract {

    sealed class Event : UiEvent {
        object close: ItemSelectorContract.Event()
        data class SetItemList(val items: List<BasketPreviewLine>) : ItemSelectorContract.Event()
        data class SetSelectedItem(val item: BasketPreviewLine) :ItemSelectorContract.Event()
    }

    data class State(
        val selectedItem: BasketPreviewLine? = null,
        val itemList: List<BasketPreviewLine>? = mutableListOf()
    ) : UiState

    sealed class Effect : UiEffect {

    }
}

