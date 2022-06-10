package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorContract
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.inject


class BasketPreviewViewModel(val recipeId: String?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println(" [ERROR][Miam][Basket preview] $exception ${exception.stackTraceToString()}")
    }

    private val basketStore : BasketStore by inject()
    private val groceriesListStore: GroceriesListStore by inject()
    private val itemSelectorViewModel : ItemSelectorViewModel by inject()
    private val _guestChangeDebounceFlow = MutableSharedFlow<Pair<BasketPreviewLine,RecipeViewModel>>()
    private val lineEntriesSubject  = MutableSharedFlow<List<BasketEntry>>()

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId= null,
            showLines= false,
            line= BasicUiState.Loading,
            bpl= null,
            isReloading= false,
            job = null
        )

    init {
        if(recipeId != null){
            basketChange()
            val job = launch(coroutineHandler) {
                basketStore.observeSideEffect().filter { basketEffect -> basketEffect == BasketEffect.BasketPreviewChange }.collect{
                    basketChange()
                }
            }
            setState { copy(job = job) }
            countListener()
            listenEntriesChanges()
        }
    }

    private fun countListener() {
        launch(coroutineHandler) {
            _guestChangeDebounceFlow.debounce(500).collect {
                println("Miam Emmit ${it.first.count}")
                setEvent(BasketPreviewContract.Event.Reload)
                it.second.setEvent(RecipeContract.Event.UpdateGuest(it.first.count))
            }
        }
    }

    private fun listenEntriesChanges() {
       launch(coroutineHandler) {
           lineEntriesSubject.debounce(500).collect { entries ->
               val newBpl = updateBplEntries(entries)
               setState { copy(line = BasicUiState.Success(newBpl), bpl = newBpl) }
               // create a copy of the list so you can clear it here
               basketStore.dispatch(
                   BasketAction.UpdateBasketEntries(
                       mutableListOf(*entries.toTypedArray())
                   )
               )
            }
        }
    }

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeId(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.AddEntry -> addEntry(event.entry)
            is BasketPreviewContract.Event.UpdateBasketEntry ->launch(coroutineHandler) { updateBasketEntry(event.entry, event.finalQty)}
            is BasketPreviewContract.Event.RemoveEntry -> launch(coroutineHandler) { removeBasketEntry(event.entry) }
            is BasketPreviewContract.Event.ReplaceItem -> replaceItem(event.entry)
            is BasketPreviewContract.Event.ToggleLine -> toggleLine()
            is BasketPreviewContract.Event.Reload -> setState { copy(isReloading = !uiState.value.isReloading)}
            is BasketPreviewContract.Event.CountChange -> launch(coroutineHandler) { _guestChangeDebounceFlow.emit(Pair(event.bpl, event.recipeVm )) }
            is BasketPreviewContract.Event.OpenItemSelector -> openItemSelector(event.bpl)
            is BasketPreviewContract.Event.KillJob -> uiState.value.job?.cancel()
            is BasketPreviewContract.Event.RemoveRecipe ->  groceriesListStore.dispatch(GroceriesListAction.RemoveRecipe(recipeId = currentState.recipeId.toString() ))
        }
    }

    private fun setRecipeId(newRecipeId :Int) {
        setState { copy(recipeId = newRecipeId)}
    }

    /**
     * BPL comes from the basket store that does not get relathionships
     * Will get them all at need only and then fill the entryLines with this basket entries
     * TODO : if we have include request working the get of relationships can be done in basket store directly
     */
    private fun setLines(newline: BasketPreviewLine) {
        LogHandler.debug("[Miam] newline $newline")
        setState { copy(line = BasicUiState.Success(newline),bpl = newline, isReloading= false) }
    }

    private fun toggleLine() {
        setState { copy(showLines = !uiState.value.showLines)}
    }

    private fun updateBplEntries(basketEntries: List<BasketEntry>): BasketPreviewLine {
        val newBplEntries = currentState.bpl!!.entries!!.updateBasketEntries(basketEntries)
        return currentState.bpl!!.copy(entries = newBplEntries, price = newBplEntries.totalPrice().toString())
    }

    private fun addEntry(entry: BasketEntry){
        LogHandler.debug("[Miam] add entry $entry")
        if (currentState.bpl == null || currentState.bpl!!.entries == null) {
            LogHandler.error("Trying to add entry with null bpl")
            return
        }

        val currentEntries = currentState.bpl!!.entries!!
        val newEntries = currentEntries.copy(
            found = currentEntries.found.toMutableList().plus(entry).sortedBy { basketEntry -> basketEntry.id },
            oftenDeleted = currentEntries.oftenDeleted.toMutableList().filter { e -> e.id != entry.id },
            removed = currentEntries.removed.toMutableList().filter { e -> e.id != entry.id }
        )
        val newBpl = currentState.bpl!!.copy(entries = newEntries, price = newEntries.totalPrice().toString())
        setState { copy(bpl = newBpl, line = BasicUiState.Success(newBpl)) }
        basketStore.dispatch(BasketAction.AddBasketEntry(entry))
    }

    private suspend fun removeBasketEntry(entry: BasketEntry){
        LogHandler.debug("[Miam] remove entry $entry")
        if (currentState.bpl == null || currentState.bpl!!.entries == null) {
            LogHandler.error("Trying to add entry with null bpl")
            return
        }

        val currentEntries = currentState.bpl!!.entries!!
        val newEntries = currentEntries.copy(
            found = currentEntries.found.toMutableList().filter { e -> e.id != entry.id }.sortedBy { basketEntry -> basketEntry.id },
            removed = currentEntries.removed.toMutableList().plus(entry)
        )
        val newBpl = currentState.bpl!!.copy(entries = newEntries, price = newEntries.totalPrice().toString())
        setState { copy(bpl = newBpl, line = BasicUiState.Success(newBpl)) }
        basketStore.dispatch(BasketAction.AddBasketEntry(entry))
        updateBasketEntry(entry, 0)
    }

    private suspend fun updateBasketEntry(entry: BasketEntry, finalQty:Int){
        LogHandler.debug("[Miam] update entry $entry")
        lineEntriesSubject.collect { entries ->
            val newEntries = entries.filter { be -> be.id != entry.id }.plus(entry)
            lineEntriesSubject.emit(newEntries)
        }
    }

    private fun replaceItem(entry: BasketEntry){
        val newBpl = updateBplEntries(listOf(entry))
        setState { copy(line = BasicUiState.Success(newBpl), bpl = newBpl) }
        setEvent(BasketPreviewContract.Event.CloseItemSelector)
    }

    private fun basketChange(){
        launch(coroutineHandler) {
            val bpl = basketStore.observeState().first {
                it.basketPreview != null && it.basketPreview.isNotEmpty()
            }.basketPreview?.find {
                basketPreviewLine -> basketPreviewLine.id == recipeId.toString()
            }
            LogHandler.debug("[Miam] bpl $bpl")
            if(bpl != null) {
                setEvent(BasketPreviewContract.Event.SetLines(bpl))
            }
        }
    }


    private fun openItemSelector(bpl: BasketPreviewLine){
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetSelectedItem(bpl))
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetReplaceItemInPreview( replace= fun (be: BasketEntry) { this.replaceItem(be) }))
    }
}

