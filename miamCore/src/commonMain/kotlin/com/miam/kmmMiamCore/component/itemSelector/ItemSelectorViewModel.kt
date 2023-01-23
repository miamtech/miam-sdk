package com.miam.kmmMiamCore.component.itemSelector

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.BasketAction
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object ItemSelectorInstance: KoinComponent {
    val instance: ItemSelectorViewModel by inject()
}

open class ItemSelectorViewModel: BaseViewModel<ItemSelectorContract.Event, ItemSelectorContract.State, ItemSelectorContract.Effect>(),
    KoinComponent {

    private val basketStore: BasketStore by inject()

    override fun createInitialState(): ItemSelectorContract.State = ItemSelectorContract.State(
        selectedItem = BasicUiState.Loading,
        items = mutableListOf(),
        replaceItemInPreview = fun(_: BasketEntry) {},
        returnToPreview = fun() {}
    )

    override fun handleEvent(event: ItemSelectorContract.Event) {
        when (event) {
            is ItemSelectorContract.Event.SetReturnToBasketPreview -> setState {
                copy(
                    returnToPreview = event.returnToPreview
                )
            }
            is ItemSelectorContract.Event.ReturnToBasketPreview -> uiState.value.returnToPreview()
            is ItemSelectorContract.Event.SetReplaceItemInPreview -> setState {
                copy(
                    replaceItemInPreview = event.replace
                )
            }
            is ItemSelectorContract.Event.SetItemList -> {
                setState { copy(items = event.items) }
            }
        }
    }

    fun setSelectedItem(item: BasketPreviewLine) {
        setState { copy(selectedItem = BasicUiState.Success(item), items = fillItem(item)) }
    }

    private fun fillItem(basketEntry: BasketPreviewLine): MutableList<BasketPreviewLine> {
        val itemList = mutableListOf<BasketPreviewLine>()
        (basketEntry.record as BasketEntry).relationships?.items?.data?.forEach { item ->
            if (item.id != basketEntry.record.selectedItem?.id) {
                itemList.add(BasketPreviewLine.fromBasketEntryItem(basketEntry.record, item))
            }
        }
        return itemList
    }

    fun choose(selectedItem: BasketPreviewLine, index: Int) {

        if (index >= currentState.items.size) return

        val be = (selectedItem.record as BasketEntry)
        val newBe = be.updateSelectedItem(currentState.items[index].id!!.toInt())
        basketStore.dispatch(
            BasketAction.ReplaceSelectedItem(
                be,
                currentState.items[index].id!!.toInt()
            )
        )

        currentState.items[index].id!!
        currentState.replaceItemInPreview(newBe)
    }

}