package com.miam.kmmMiamCore.component.itemSelector

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasketAction
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object ItemSelectorInstance: KoinComponent {
    val instance: ItemSelectorViewModel by inject()
}

open class ItemSelectorViewModel() :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<ItemSelectorContract.Event, ItemSelectorContract.State, ItemSelectorContract.Effect>() ,KoinComponent {

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
        LogHandler.debug(""+(basketEntry.record as BasketEntry).relationships?.items?.data?.size)
        val itemList = mutableListOf<BasketPreviewLine>()
        (basketEntry.record as BasketEntry).relationships?.items?.data?.forEach {
            item ->
            if(item.id != basketEntry.record.selectedItem?.id){
                itemList.add(BasketPreviewLine.fromBasketEntryItem(basketEntry.record,item))
            }
        }
        return itemList
    }

     fun choose(index :Int){
         val be = (currentState.selectedItem!!.record  as BasketEntry)
         val newBe = be.updateSelectedItem(currentState.itemList!![index].id!!.toInt())
        basketStore.dispatch(
            BasketAction.ReplaceSelectedItem(
                be,
            currentState.itemList!![index].id!!.toInt()
        ))

         currentState.itemList!![index].id!!
        currentState.replaceItemInPreview(newBe)
    }

}