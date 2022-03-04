package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketEntryRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import org.koin.core.component.inject

@OptIn(InternalCoroutinesApi::class)
class BasketPreviewViewModel(val recipeId: Int?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val basketStore : BasketStore by inject()
    private val basketEntryRepo : BasketEntryRepositoryImp by inject()
    private val _guestChangeDebounceFlow = MutableSharedFlow<Pair<BasketPreviewLine,RecipeViewModel>>()
    private var isFillingEntry = false

    init {
        println("MIAM --> basket RecipeId : $recipeId ")
        if(recipeId != null){
            basketChange()
            launch {
                basketStore.observeSideEffect().collectLatest{
                    if (it == BasketEffect.BasketPreviewChange){
                        basketChange()
                    }
                }
            }
            countListener()
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

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId= null,
            showLines= false,
            line= BasicUiState.Loading,
            isReloading= false,
            isFillingEntry= false
        )

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeId(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.AddEntry -> addEntry(event.entry)
            is BasketPreviewContract.Event.UpdateBasketEntry -> updateBaskeyEntry(event.entry, event.deltaQty)
            is BasketPreviewContract.Event.RemoveEntry -> removeBasketEntry(event.entry)
            is BasketPreviewContract.Event.ReplaceItem -> replaceItem(event.entry,event.itemId)
            is BasketPreviewContract.Event.ToogleLine -> toogleLine()
            is BasketPreviewContract.Event.Reload -> setState { copy(isReloading = !uiState.value.isReloading)}
            is BasketPreviewContract.Event.BuildEntriesLines -> buildEntriesLines(event.bpl)
            is BasketPreviewContract.Event.CountChange -> launch { _guestChangeDebounceFlow.emit(Pair(event.bpl, event.recipeVm )) }
        }
    }

    private fun setRecipeId(newRecipeId :Int) {
        setState { copy(recipeId = newRecipeId)}
    }

    private fun setLines(newline: BasketPreviewLine) {
        println("Miam  --> SetLine ")
        setEvent(BasketPreviewContract.Event.BuildEntriesLines(newline))
    }

    private fun toogleLine() {
        setState { copy( showLines = !uiState.value.showLines)}
    }

    private  fun buildEntriesLines(bpl :BasketPreviewLine){
        println("Maim -> buildEntriesLines")
       if(isFillingEntry) return
       fillBasketEntries(bpl)
    }

    private fun addEntry(entry: BasketEntry){
        println("Miam --> add entry")
        basketStore.dispatch(BasketAction.AddBasketEntry(entry))
    }

    private fun removeBasketEntry(entry: BasketEntry){
        basketStore.dispatch(BasketAction.RemoveEntry(entry))
    }

    private fun updateBaskeyEntry(entry: BasketEntry, deltaQty:Int){
        basketStore.dispatch(BasketAction.UpdateBasketEntries(listOf(AlterQuantityBasketEntry(entry.id,deltaQty))))
    }

    private fun replaceItem(entry: BasketEntry, itemId: Int){
            basketStore.dispatch(BasketAction.ReplaceSelectedItem(entry,itemId)
        )
    }

    private fun basketChange(){
        println("MIAM --> basket change")
           val bpl = basketStore.observeState().value.basketPreview?.find { basketPreviewLine -> basketPreviewLine.id == recipeId }
            if(bpl != null) {
                setEvent(BasketPreviewContract.Event.SetLines(bpl))
            }
    }

    private fun fillBasketEntries(line: BasketPreviewLine) {
        setState { copy( isFillingEntry = true)}

        try {
            println("MIAM --> basket setFillBasketEntry  ${line.id}")
            val filledFoundBasketEntries : MutableList<BasketEntry> = mutableListOf()
            val filledRevovedBasketEntries : MutableList<BasketEntry> = mutableListOf()
            val filledOftenDeletedBasketEntry : MutableList<BasketEntry> = mutableListOf()
            val filledNotFoundBasketEntry : MutableList<BasketEntry> = mutableListOf()
            runBlocking {
                withContext(Dispatchers.Default) {
                    line.entries?.found?.forEach {
                        launch {
                            basketEntryRepo.getRelationships(it).collect {
                                filledFoundBasketEntries.add(it)
                            }
                        }
                    }
                    line.entries?.removed?.forEach {
                        launch {
                            basketEntryRepo.getRelationships(it).collect {
                                filledRevovedBasketEntries.add(it)
                            }
                        }
                }
                    line.entries?.oftenDeleted?.forEach {
                        launch {
                            basketEntryRepo.getRelationships(it).collect {
                                filledOftenDeletedBasketEntry.add(it)
                            }
                        }
                    }
                    line.entries?.notFound?.forEach {
                        launch {
                            basketEntryRepo.getRelationships(it).collect {
                                filledNotFoundBasketEntry.add(it)
                            }
                        }
                    }
                }
            }
                line.entries?.found?.clear()
                line.entries?.removed?.clear()
                line.entries?.oftenDeleted?.clear()
                line.entries?.notFound?.clear()
                line.entries?.found?.addAll(filledFoundBasketEntries)
                line.entries?.removed?.addAll(filledRevovedBasketEntries)
                line.entries?.oftenDeleted?.addAll(filledOftenDeletedBasketEntry)
                line.entries?.notFound?.addAll(filledNotFoundBasketEntry)
                setState { copy(line = BasicUiState.Success(line), isReloading= false, isFillingEntry = false) }

        } catch (cause: Throwable) {
            print(cause.toString())
            isFillingEntry = false
          // todo Throw error
        }
    }
}

