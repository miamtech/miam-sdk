package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class AlterQuantityBasketEntry(
    val id: Int,
    val qty: Int,
)

data class BasketState(
    val groceriesList : GroceriesList?,
    val idPointOfSale : Int?,
    val basket :Basket?,
    val basketPreview : List<BasketPreviewLine>?,
    val entriesCount : Int? =0,
    val recipeCount :Int? =0,
    val totalPrice : Double? =0.0,
    val updateBasketEntrieQueue: MutableList<AlterQuantityBasketEntry> = mutableListOf()
) : State

sealed class  BasketAction : Action {
    data class RefreshBasket(val groceriesList: GroceriesList, val idPointOfSale: Int): BasketAction()
    data class SetBasket(val basket: Basket): BasketAction()
    data class SetGroceriesList(val groceriesList: GroceriesList) : BasketAction()
    data class SetIdPointOfSale(val pointOfSaleId: Int) : BasketAction()
    data class SetBasketState(val basketPreview: List<BasketPreviewLine>) :BasketAction()
    data class UpdateBasketEntries( val basketEntries: List<AlterQuantityBasketEntry>) : BasketAction()
    object ConfirmBasket : BasketAction()
    data class Error(val error: Exception) : BasketAction()
}

sealed class  BasketEffect : Effect {
    data class Error(val error: Exception) :  BasketEffect()
    data class PointOfSaleChanged(val idPointOfSale: Int) :  BasketEffect()
    object BasketPreviewChange: BasketEffect()
}

class BasketStore : Store<BasketState, BasketAction, BasketEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(BasketState(null, null, null, emptyList()))
    private val sideEffect = MutableSharedFlow<BasketEffect>()
    private val basketRepo : BasketRepositoryImp by inject()
    private val entriesSubject : MutableSharedFlow<List<BasketEntry>> = MutableSharedFlow()

    suspend fun listenEntriesChanges() {
         entriesSubject.debounce(1000).collect{


        }
    }

    override fun observeState(): StateFlow<BasketState> = state

    override fun observeSideEffect(): Flow<BasketEffect> = sideEffect

    override fun dispatch(action: BasketAction) {
        val oldState = state.value

        val newState = when (action) {
            is BasketAction.RefreshBasket -> {
                launch {
                        loadBasket(action.groceriesList.id, action.idPointOfSale)
                }
                oldState
            }
            is BasketAction.SetGroceriesList -> {
                val tmp =  oldState.copy(groceriesList = action.groceriesList)
                shouldUpdateBasket(tmp)
                tmp
            }
            is BasketAction.SetIdPointOfSale -> {
                val tmp = oldState.copy(idPointOfSale = action.pointOfSaleId)
                shouldUpdateBasket(tmp)
                tmp
            }
            is BasketAction.UpdateBasketEntries -> {
                action.basketEntries.forEach {
                    val idx =  oldState.updateBasketEntrieQueue.indexOfFirst { aqbe -> aqbe.id == it.id }
                    if(idx != -1){ oldState.updateBasketEntrieQueue[idx] = it } else { oldState.updateBasketEntrieQueue.add(it) }
                }
         /*       launch {
                    entriesSubject.emit(oldState.updateBasketEntrieQueue.filter {
                        it.qty  != 0
                    }.map{
                        updatedEntry(it, oldState?.basket?._relationships?.basketEntries ?: emptyList())
                    }
                   )
                }*/
                oldState
            }
            is BasketAction.SetBasket -> {
                val lineEntries = this.groupBasketEntries( state.value.groceriesList?.attributes?.recipesInfos ?: emptyList()
                    , action.basket._relationships?.basketEntries ?: emptyList())
                val basketPreview = BasketPreviewLine.recipesAndLineEntriesToBasketPreviewLine(state.value.groceriesList!!, lineEntries,)
                val newState = oldState.copy(basket = action.basket, basketPreview = basketPreview, recipeCount = state.value.groceriesList?.attributes?.recipesInfos?.size ?: 0 )
                dispatch(BasketAction.SetBasketState(basketPreview))
                newState
            }
            is BasketAction.SetBasketState -> {
              val newState =  setBasketStates(action.basketPreview, oldState)
              launch { sideEffect.emit(BasketEffect.BasketPreviewChange)}
              newState
            }
            is BasketAction.ConfirmBasket -> {
                confirmBasket()
                oldState
            }
            is BasketAction.Error -> {
                oldState
            }
        }
        if (newState != oldState) {
            state.value = newState
        }
    }

    private fun shouldUpdateBasket(tmpState: BasketState){
        if( tmpState.groceriesList  == null  || tmpState.idPointOfSale == null ) return
        dispatch(BasketAction.RefreshBasket(tmpState.groceriesList,tmpState.idPointOfSale))
    }

    private fun groupBasketEntries(recipesInfos : List<RecipeInfos>, entries : List<BasketEntry>) : List<LineEntries> {
            return recipesInfos.map { ri ->
               var lineEntries = LineEntries()
                entries.filter { entry -> entry.attributes.recipeIds?.contains(ri.id) ?: false }
                    .forEach { matchingEntry ->
                        val available  = matchingEntry.attributes.selectedItemId
                        if(available != null){
                            when(matchingEntry.attributes.groceriesEntryStatus){
                                 "often_deleted" -> lineEntries.oftenDeleted.add(matchingEntry)
                                    "deleted" -> lineEntries.removed.add(matchingEntry)
                                 else -> lineEntries.found.add(matchingEntry)
                            }
                        } else {
                            lineEntries.notFound.add(matchingEntry)
                        }
                     }
                lineEntries
            }
    }

    private fun setBasketStates(basketPreview: List<BasketPreviewLine>, oldState: BasketState) : BasketState {

        val entriesFound: List<BasketEntry> = basketPreview.map { bpl -> bpl.entries?.found ?: emptyList() }.flatten()
        var totalPrice = 0.0

        entriesFound.forEach { e ->
            val item = e.attributes.basketEntriesItems?.find { bei ->  bei.itemId != null && bei.itemId == e.attributes.selectedItemId }
            totalPrice +=  (e.attributes.quantity ?: 0) * (item?.unitPrice ?: 0.0)
        }

       return oldState.copy( entriesCount = entriesFound.size, totalPrice = totalPrice)
    }

    private fun confirmBasket(){
        //TODO confimr basket
    }

    private fun updatedEntry(aqbe: AlterQuantityBasketEntry, basketEntries: List<BasketEntry> ): BasketEntry ?{
        val basketEntrie =  basketEntries.find { it.id == aqbe.id }

        if (basketEntrie != null) {
            val newQty = (basketEntrie.attributes.quantity ?: 0) - aqbe.qty

           return basketEntrie.copy(
                attributes = basketEntrie.attributes.copy(
                    quantity =  newQty ,

                    // TODO
                    //status = if(newQty > 0) "active" else "deleted"
                )

            )
        }

        return basketEntrie
    }

    private suspend fun loadBasket(idGroceriesList: Int,idPointOfSale :Int ) {
       /* try {
            launch {
                basketRepo.getFromListAndPos(idGroceriesList,idPointOfSale)
                    .collect {
                        dispatch(BasketAction.SetBasket(it))
                    }
            }
        } catch (e: Exception) {
            dispatch(BasketAction.Error(e))
        }*/
    }

}