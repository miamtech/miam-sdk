package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorContract
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.inject


class BasketPreviewViewModel(val recipeId: Int?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val basketStore : BasketStore by inject()
    private val itemSelectorViewModel : ItemSelectorViewModel by inject()
    private val _guestChangeDebounceFlow = MutableSharedFlow<Pair<BasketPreviewLine,RecipeViewModel>>()
    private val lineEntriesSubject  = MutableSharedFlow<MutableList<BasketEntry>>()
    var lastEntriesUpdate: MutableList<BasketEntry> = mutableListOf()

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId= null,
            showLines= false,
            line= BasicUiState.Loading,
            bpl= null,
            isReloading= false,
            showItemSelector= false,
            job = null
        )

    init {
        if(recipeId != null){
            // println("Miam --> basket RecipeId : $recipeId ")
            basketChange()
          val job = launch {
                basketStore.observeSideEffect().filter { basketEffect -> basketEffect == BasketEffect.BasketPreviewChange  }.collect{
                    basketChange()
                }
            }
            setState { copy(job = job) }
            countListener()
            listenEntriesChanges()
        }
    }

    private fun countListener() {
        launch {
            _guestChangeDebounceFlow.debounce(500).collect {
                println("Miam Emmit ${it.first.count}")
                setEvent(BasketPreviewContract.Event.Reload)
                it.second.setEvent(RecipeContract.Event.UpdateGuest(it.first.count))
            }
        }
    }

    private fun listenEntriesChanges() {
       launch {
           lineEntriesSubject.debounce(500).collect { entries ->
            //    println("Miam listenEntriesChanges debounced with $entries")
            //    println("Miam update ui $entries")
               setState {
                   copy(
                       line = BasicUiState.Success(updateBplEntries(entries))
                   )
               }
               // println("Miam listenEntriesChanges will dispatch with $entries")
               // create a copy of the list so you can clear it here
               basketStore.dispatch(
                   BasketAction.UpdateBasketEntries(
                       mutableListOf(*entries.toTypedArray())
                   )
               )
               lastEntriesUpdate.clear()
            }
        }
    }

    private fun updateBplEntries(basketEntries: MutableList<BasketEntry>): BasketPreviewLine {
        currentState.bpl!!.entries!!.updateBasketEntries(basketEntries)
        return  currentState.bpl!!.updateEntries()
    }

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeId(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.AddEntry -> addEntry(event.entry)
            is BasketPreviewContract.Event.UpdateBasketEntry ->launch { updateBasketEntry(event.entry, event.finalQty)}
            is BasketPreviewContract.Event.RemoveEntry -> removeBasketEntry(event.entry)
            is BasketPreviewContract.Event.ReplaceItem -> replaceItem(event.entry)
            is BasketPreviewContract.Event.ToggleLine -> toggleLine()
            is BasketPreviewContract.Event.Reload -> setState { copy(isReloading = !uiState.value.isReloading)}
            is BasketPreviewContract.Event.CountChange -> launch { _guestChangeDebounceFlow.emit(Pair(event.bpl, event.recipeVm )) }
            is BasketPreviewContract.Event.OpenItemSelector -> openItemSelector(event.bpl)
            is BasketPreviewContract.Event.CloseItemSelector ->  setState { copy(showItemSelector = false)}
            is BasketPreviewContract.Event.KillJob -> uiState.value.job?.cancel()
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
        setState { copy(line = BasicUiState.Success(newline),bpl = newline, isReloading= false) }
    }

    private fun toggleLine() {
        setState { copy(showLines = !uiState.value.showLines)}
    }

    private fun addEntry(entry: BasketEntry){
        println("Miam --> add entry")
        currentState.bpl?.entries?.found?.add(entry)
        currentState.bpl?.entries?.found?.sortedBy { basketEntry -> basketEntry.id }
        currentState.bpl?.entries?.oftenDeleted?.removeAll { be -> be.id == entry.id }
        currentState.bpl?.entries?.removed?.removeAll { be -> be.id == entry.id }

        if(currentState.bpl != null ) {
            setState {
                copy(
                    line = BasicUiState.Success(
                        currentState.bpl!!.updateEntries()
                    )
                )
            }
            basketStore.dispatch(BasketAction.AddBasketEntry(entry))
        }

    }

    private fun removeBasketEntry(entry: BasketEntry){
        currentState.bpl?.entries?.found?.removeAll { be -> be.id == entry.id }
        currentState.bpl?.entries?.removed?.add(entry)
        if(currentState.bpl != null ) {
            setState {
                copy(
                    line = BasicUiState.Success(
                        currentState.bpl!!.updateEntries()
                    )
                )
            }
            basketStore.dispatch(BasketAction.RemoveEntry(entry))
        }
    }

    private suspend fun updateBasketEntry(entry: BasketEntry, finalQty:Int){
        // println("Miam updateBasketEntry $BasketEntry $finalQty")
        // println("Miam updateBasketEntry already got $lastEntriesUpdate")
        val existingEntryIdx = lastEntriesUpdate.indexOfFirst{ be ->
            be.id == entry.id
        }
        if (existingEntryIdx >= 0) {
            // println("Miam updateBasketEntry updating")
            var existingEntry = lastEntriesUpdate[existingEntryIdx]
            existingEntry = existingEntry.updateQuantity(finalQty)
            lastEntriesUpdate[existingEntryIdx] = existingEntry
        } else {
            // println("Miam updateBasketEntry creating")
            val newEntry = entry.updateQuantity(finalQty)
            lastEntriesUpdate.add(newEntry)
        }
        // println("Miam updateBasketEntry will emit $lastEntriesUpdate")
        lineEntriesSubject.emit(lastEntriesUpdate)
    }

    private fun replaceItem(entry: BasketEntry){
        val idx = currentState.bpl?.entries?.found?.indexOfFirst { be -> be.id == entry.id }
        if(idx != -1 && idx!= null){
            currentState.bpl!!.entries!!.found[idx] = entry
            setState {
                copy(
                    line = BasicUiState.Success(
                        currentState.bpl!!.updateEntries()
                    )
                )
            }
        }
        setEvent(BasketPreviewContract.Event.CloseItemSelector)
    }

    private fun basketChange(){
        // println("Miam --> basket change")
        launch {
            val bpl = basketStore.observeState().first { it.basketPreview != null && it.basketPreview.isNotEmpty() }.basketPreview?.find { basketPreviewLine -> basketPreviewLine.id == recipeId }
            if(bpl != null) {
                setEvent(BasketPreviewContract.Event.SetLines(bpl))
            }
        }
    }


    private fun openItemSelector(bpl: BasketPreviewLine){
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetSelectedItem(bpl))
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetReplaceItemInPreview( replace= fun (be: BasketEntry) { this.replaceItem(be) }))
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetReturnToBasketPreview( returnToPreview = {setState { copy(showItemSelector = false)}}))
        setState { copy(showItemSelector = true)}
    }
}

