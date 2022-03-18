package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorContract
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketEntryRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.miam_core.model.LineEntries
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.inject
import java.lang.Thread


class BasketPreviewViewModel(val recipeId: Int?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val basketStore : BasketStore by inject()
    private val basketEntryRepo : BasketEntryRepositoryImp by inject()
    private val itemSelectorViewModel : ItemSelectorViewModel by inject()
    private val _guestChangeDebounceFlow = MutableSharedFlow<Pair<BasketPreviewLine,RecipeViewModel>>()
    private val lineEntriesSubject  = MutableSharedFlow<LineEntries>()
    var lastEntriesUpdate: LineEntries? = null
    private var isFillingEntry = false

    init {
        if(recipeId != null){
            // println("Miam --> basket RecipeId : $recipeId ")
            basketChange()
            launch {
                basketStore.observeSideEffect().filter { basketEffect -> basketEffect == BasketEffect.BasketPreviewChange  }.collect{
                    basketChange()
                }
            }
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

    private  fun listenEntriesChanges() {
       launch {
           lineEntriesSubject.debounce(1000).collect{ entries ->
               lastEntriesUpdate = null
                println("update ui $entries")
               setState { copy(line = BasicUiState.Success( currentState.bpl!!.copy(
                   entries =  entries ,
                   price = updatePrice(entries.found).toString(),

               ))) }
            }
        }
    }

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId= null,
            showLines= false,
            line= BasicUiState.Loading,
            bpl= null,
            isReloading= false,
            isFillingEntry= false,
            firstEntriesBuildDone = false,
            showItemSelector= false,

        )

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeId(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.AddEntry -> addEntry(event.entry)
            is BasketPreviewContract.Event.UpdateBasketEntry ->launch { updateBasketEntry(event.entry, event.deltaQty)}
            is BasketPreviewContract.Event.RemoveEntry -> removeBasketEntry(event.entry)
            is BasketPreviewContract.Event.ReplaceItem -> replaceItem(event.entry)
            is BasketPreviewContract.Event.ToggleLine -> toggleLine()
            is BasketPreviewContract.Event.Reload -> setState { copy(isReloading = !uiState.value.isReloading)}
            is BasketPreviewContract.Event.BuildEntriesLines -> buildEntriesLines(event.bpl)
            is BasketPreviewContract.Event.UpdateEntriesLines -> updateEntriesLines(event.bpl)
            is BasketPreviewContract.Event.CountChange -> launch { _guestChangeDebounceFlow.emit(Pair(event.bpl, event.recipeVm )) }
            is BasketPreviewContract.Event.OpenItemSelector -> openItemSelector(event.bpl)
            is BasketPreviewContract.Event.CloseItemSelector ->  setState { copy(showItemSelector = false)}
        }
    }

    private fun setRecipeId(newRecipeId :Int) {
        setState { copy(recipeId = newRecipeId)}
    }

    private fun setLines(newline: BasketPreviewLine) {
        println("Miam  --> SetLine  ${currentState.firstEntriesBuildDone}")
       if(!currentState.firstEntriesBuildDone) {
            setState { copy(firstEntriesBuildDone = true)}
           println("Miam  --> BuildEntriesLines  ${currentState.firstEntriesBuildDone}")
            setEvent(BasketPreviewContract.Event.BuildEntriesLines(newline))
        } else {
           println("Miam  --> UpdateEntriesLines  ${currentState.firstEntriesBuildDone}")
            setEvent(BasketPreviewContract.Event.UpdateEntriesLines(newline))
        }
    }

    private fun toggleLine() {
        setState { copy(showLines = !uiState.value.showLines)}
    }

    private fun buildEntriesLines(bpl :BasketPreviewLine){
        println("Miam -> buildEntriesLines   $isFillingEntry")
       if(isFillingEntry) return
       fillBasketEntries(bpl)
    }

    private fun updateEntriesLines(bpl :BasketPreviewLine) {
        println("Miam -> updateEntriesLines   $isFillingEntry")
        if(isFillingEntry) return
        isFillingEntry = true

      launch {
            withContext(ioDispatcher){
                val coco = fillMissing( currentState.bpl?.entries?.found , bpl.entries?.found)
                val tata = fillMissing( currentState.bpl?.entries?.removed , bpl.entries?.removed)
                currentState.bpl?.entries?.found?.addAll(coco)
                currentState.bpl?.entries?.removed?.addAll(tata)
            }
        }
        isFillingEntry = false
        setState { copy( line = BasicUiState.Success(bpl), bpl = bpl, isReloading= false, isFillingEntry = false) }
    }

    private suspend fun fillMissing( oldArray : List<BasketEntry>? = emptyList(), newArray: MutableList<BasketEntry>? = mutableListOf()) : MutableList<BasketEntry> {
        val missingFilledBasketEntries = mutableListOf<BasketEntry>()
        val newProducts = newArray!!.filter { be ->
            oldArray!!.find { it.id == be.id } == null
        }

        if(newProducts.isEmpty()) return missingFilledBasketEntries

        newProducts.map {
               async {
                   println("Miam  async ${Thread.currentThread().name} ")
                   missingFilledBasketEntries.add(basketEntryRepo.getRelationships(it).first())
                }
            }.awaitAll()

        return  missingFilledBasketEntries
    }

    private fun addEntry(entry: BasketEntry){
        println("Miam --> add entry")
        currentState.bpl?.entries?.found?.add(entry)
        currentState.bpl?.entries?.found?.sortedBy { basketEntry -> basketEntry.id }
        currentState.bpl?.entries?.oftenDeleted?.removeAll { be -> be.id == entry.id }
        currentState.bpl?.entries?.removed?.removeAll { be -> be.id == entry.id }

        if(currentState.bpl != null ) {
            setState { copy(line = BasicUiState.Success(currentState.bpl!!.copy(
                entries = currentState.bpl!!.entries!!.copy(),
                price = updatePrice(currentState.bpl!!.entries!!.found).toString()
            )))}
            basketStore.dispatch(BasketAction.AddBasketEntry(entry))
        }

    }

    private fun removeBasketEntry(entry: BasketEntry){
        currentState.bpl?.entries?.found?.removeAll { be -> be.id == entry.id }
        currentState.bpl?.entries?.removed?.add(entry)
        if(currentState.bpl != null ) {
            setState { copy(line = BasicUiState.Success(currentState.bpl!!.copy(
                entries = currentState.bpl!!.entries!!.copy(),
                price = updatePrice(currentState.bpl!!.entries!!.found).toString()
                )
            ) ) }
            basketStore.dispatch(BasketAction.RemoveEntry(entry))
        }
    }

    private suspend fun updateBasketEntry(entry: BasketEntry, deltaQty:Int){
            val entries = lastEntriesUpdate ?: currentState.bpl?.entries
        mutableListOf(*entries!!.found.toTypedArray())


            val idx = entries?.found?.indexOfFirst { be -> be.id == entry.id }
            if(idx != -1 && idx!= null) {
                entries.found[idx] = entries.found[idx].copy(attributes = entries.found[idx].attributes.copy(
                    quantity =  (entries.found[idx].attributes.quantity ?: 0 ) + deltaQty))
                entries.found[idx]._relationships = entry._relationships
                lastEntriesUpdate = entries.copy()
                lineEntriesSubject.emit(lastEntriesUpdate!!)
            }
        basketStore.dispatch(BasketAction.UpdateBasketEntries(listOf(AlterQuantityBasketEntry(entry.id,deltaQty))))
    }

    private fun replaceItem(entry: BasketEntry){
            val idx = currentState.bpl?.entries?.found?.indexOfFirst { be -> be.id == entry.id }
            if(idx != -1 && idx!= null){
                currentState.bpl!!.entries!!.found[idx] = entry
                setState { copy(line = BasicUiState.Success( currentState.bpl!!.copy(
                    entries = currentState.bpl!!.entries!!.copy(),
                    price = updatePrice(currentState.bpl!!.entries!!.found).toString()
                ))) }
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

    private fun fillBasketEntries(line: BasketPreviewLine) {
        setState { copy( isFillingEntry = true)}
                try {
                    println("Miam --> basket setFillBasketEntry  ${line.id}")

                    launch{
                        withContext(ioDispatcher) {
                            println("Miam --> Start runing block")
                            println("Miam --> ${Thread.currentThread().name}")

                            // launch all retrieve async
                            val foundEntries = retrieveBasketEntries(line.entries?.found)
                            val removedEntries = retrieveBasketEntries(line.entries?.removed)
                            val oftenDeletedEntries = retrieveBasketEntries(line.entries?.removed)
                            val notFoundEntries = retrieveBasketEntries(line.entries?.notFound)

                            //fill the result, will wait synchronous job ended
                            replaceBasketEntries(line.entries?.found, foundEntries.awaitAll())
                            line.entries?.found?.sortedBy { basketEntry -> basketEntry.id }
                            replaceBasketEntries(line.entries?.removed, removedEntries.awaitAll())
                            replaceBasketEntries(line.entries?.removed, oftenDeletedEntries.awaitAll())
                            replaceBasketEntries(line.entries?.notFound, notFoundEntries.awaitAll())

                            println("Miam will set state")
                            setState { copy(line = BasicUiState.Success(line),bpl = line, isReloading= false, isFillingEntry = false) }
                        }
                    }

                } catch (cause: Throwable) {
                    print(cause.toString())
                    isFillingEntry = false
                    // todo Throw error
                }
    }

    private suspend fun retrieveBasketEntries(list :MutableList<BasketEntry>? = mutableListOf(), id: String = ""): List<Deferred<BasketEntry>> {
        return list!!.map {
            async {
                basketEntryRepo.getRelationships(it).first()
            }
        }
    }

    private fun replaceBasketEntries(inputList :MutableList<BasketEntry>? = mutableListOf(), resList: List<BasketEntry>) {
        inputList!!.clear()
        inputList.addAll(resList)
    }

    private fun updatePrice(foundEntries: MutableList<BasketEntry>) : Double{
        var total = 0.0
        foundEntries.forEach { fe ->
            val beItem = fe.attributes.basketEntriesItems?.find { bei ->bei.itemId ==  fe.attributes.selectedItemId }
            val price = if(beItem?.unitPrice != null && fe.attributes.quantity != null ) beItem.unitPrice * fe.attributes.quantity else 0.0
                total+= price
            }
        return total
    }

    private fun openItemSelector(bpl: BasketPreviewLine){
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetSelectedItem(bpl))
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetReplaceItemInPreview( replace= fun (be: BasketEntry) { this.replaceItem(be) }))
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetReturnToBasketPreview( returnToPreview = {setState { copy(showItemSelector = false)}}))
        setState { copy(showItemSelector = true)}
    }
}

