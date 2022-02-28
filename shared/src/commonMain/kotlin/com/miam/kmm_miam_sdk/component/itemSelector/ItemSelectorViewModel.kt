package com.miam.kmm_miam_sdk.component.itemSelector

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry


open class ItemSelectorViewModel() :
    BaseViewModel<ItemSelectorContract.Event, ItemSelectorContract.State, ItemSelectorContract.Effect>() {


    override fun createInitialState(): ItemSelectorContract.State = ItemSelectorContract.State(
              selectedItem = null,
              itemList =  mutableListOf()
        )

    override fun handleEvent(event: ItemSelectorContract.Event) {
        when (event) {
            is ItemSelectorContract.Event.close -> {
                // remove close function from android put in viewModel of modal
            }
            is ItemSelectorContract.Event.SetSelectedItem -> {
                setEvent(ItemSelectorContract.Event.SetItemList(
                    (event.item.record as BasketEntry)._relationships?.items
                        ?: emptyList()))
                setState { copy(selectedItem = event.item ) }
            }
            is ItemSelectorContract.Event.SetItemList -> {
                setState { copy(itemList = event.items) }
            }
        }
    }

}