package com.miam.kmm_miam_sdk.component.itemSelector

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine


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
                setState { copy(selectedItem = event.item ,itemList = fillItem(event.item)) }
            }
            is ItemSelectorContract.Event.SetItemList -> {
                setState { copy(itemList = event.items) }
            }
        }
    }

    private fun fillItem(basketEntry: BasketPreviewLine): MutableList<BasketPreviewLine> {

        val itemList = mutableListOf<BasketPreviewLine>()
        (basketEntry.record as BasketEntry)._relationships?.items?.forEach {
            item ->
            if(item.id != basketEntry.record.selectedItem?.id){
                itemList.add(BasketPreviewLine.fromBasketEntryItem(basketEntry.record,item))
            }
        }
        return itemList
    }

}