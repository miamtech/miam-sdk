package com.miam.kmm_miam_sdk.component.itemSelector

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasketAction
import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import org.koin.core.component.inject

open class ItemSelectorViewModel() :
    BaseViewModel<ItemSelectorContract.Event, ItemSelectorContract.State, ItemSelectorContract.Effect>() {

    private val basketStore: BasketStore by inject()

    override fun createInitialState(): ItemSelectorContract.State = ItemSelectorContract.State(
              selectedItem = null,
              itemList =  mutableListOf(),
              replaceItemInPreview = fun (_: BasketEntry){},
              returnToPreview = fun (){}
        )

    override fun handleEvent(event: ItemSelectorContract.Event) {
        when (event) {
            is ItemSelectorContract.Event.SetReturnToBasketPreview -> setState { copy (returnToPreview= event.returnToPreview ) }
            is ItemSelectorContract.Event.ReturnToBasketPreview -> uiState.value.returnToPreview()
            is ItemSelectorContract.Event.SelectNewItem -> choose(event.index)
            is ItemSelectorContract.Event.SetReplaceItemInPreview ->  setState { copy(replaceItemInPreview = event.replace ) }
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

     fun choose(index :Int){
         val be = (currentState.selectedItem!!.record  as BasketEntry)
         val newBe = be.copy(attributes = be.attributes.copy(
             selectedItemId = currentState.itemList!![index].id!!
         ))
         newBe._relationships = be._relationships
        basketStore.dispatch(
            BasketAction.ReplaceSelectedItem(
                be,
            currentState.itemList!![index].id!!
        ))

         currentState.itemList!![index].id!!
        currentState.replaceItemInPreview(newBe)
    }

}