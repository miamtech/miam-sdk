package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.BasketEffect
import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketEntryRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.inject

@OptIn(InternalCoroutinesApi::class)
class BasketPreviewViewModel(val recipeId: Int?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val basketStore : BasketStore by inject()
    private val basketEntryRepo : BasketEntryRepositoryImp by inject()

    init {
        if(recipeId != null){
            basketChange()
            launch {
                basketStore.observeSideEffect().collectLatest{
                    if (it == BasketEffect.BasketPreviewChange){
                        basketChange()
                    }
                }
            }

        }else {

        }
    }

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId= null,
            showLines= false,
            line= BasicUiState.Loading,
        )

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeid(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.toogleLine -> toogleLine()
            is BasketPreviewContract.Event.BuildEntriesLines -> buildEntriesLines(event.bpl)
        }
    }

    private fun setRecipeid(newRecipeId :Int) {
        setState { copy(recipeId = newRecipeId)}
    }

    private fun setLines(newline: BasketPreviewLine) {
        setEvent(BasketPreviewContract.Event.BuildEntriesLines(newline))
    }

    private fun toogleLine() {
        setState { copy( showLines = !uiState.value.showLines)}
    }

    private  fun buildEntriesLines(bpl :BasketPreviewLine){
       launch { fillBasketEntries(bpl)}
    }

    private fun basketChange(){

           val bpl = basketStore.observeState().value.basketPreview?.find { basketPreviewLine -> basketPreviewLine.id == recipeId }
            if(bpl != null) {
                setEvent(BasketPreviewContract.Event.SetLines(bpl))
            }

    }

    private suspend fun fillBasketEntries(line: BasketPreviewLine) {

        try {
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

                setState { copy(line = BasicUiState.Success(line)) }

        } catch (cause: Throwable) {
            print(cause.toString())
          // todo Throw error
        }
    }
}

