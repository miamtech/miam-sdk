package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketEntryRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.data.repository.GroceriesEntryRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.data.repository.SupplierRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class AlterQuantityBasketEntry(
    val id: Int,
    val delatQty: Int,
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
    data class AddBasketEntry(val entry: BasketEntry): BasketAction()
    data class RemoveEntry(val entry: BasketEntry): BasketAction()
    data class UpdateBasketEntries(
        val basketEntries: List<AlterQuantityBasketEntry>,
        val withDebounce: Boolean = true,
        val callback: (()-> Unit)? = null
    ): BasketAction()
    data class ReplaceSelectedItem(val basketEntry :BasketEntry, val itemId :Int): BasketAction()
    data class ConfirmBasket(val price: String) : BasketAction()
    object ResetUpdateBasketEntriesQueue : BasketAction()
    data class Error(val error: Exception) : BasketAction()
}

sealed class  BasketEffect : Effect {
    data class Error(val error: Exception) :  BasketEffect()
    data class PointOfSaleChanged(val idPointOfSale: Int) :  BasketEffect()
    object BasketPreviewChange: BasketEffect()
    object BasketConfirmed :BasketEffect()
}

class BasketStore : Store<BasketState, BasketAction, BasketEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(BasketState(null, null, null, emptyList()))
    private val sideEffect = MutableSharedFlow<BasketEffect>()
    private val basketRepo : BasketRepositoryImp by inject()
    private val basketEntryRepo :BasketEntryRepositoryImp by inject()
    private val groceriesRepo : GroceriesEntryRepositoryImp by inject()
    private val supplierRepositoryImp: SupplierRepositoryImp by inject()
    private val entriesSubject : MutableSharedFlow< MutableList<AlterQuantityBasketEntry>> = MutableSharedFlow()

    init {
        launch {
            listenEntriesChanges()
        }
    }

   private suspend fun listenEntriesChanges() {
         entriesSubject.debounce(1000).collect{ mutableList ->
            // println("Miam debounce $mutableList")
            sendBasketEntriesChanges(mutableList)
        }
    }

    private suspend fun sendBasketEntriesChanges(mutableList: MutableList<AlterQuantityBasketEntry>, callback: (()-> Unit)? = null) {
        dispatch(BasketAction.ResetUpdateBasketEntriesQueue)
        // println("Miam sendBasketEntriesChanges will run blocking")
        runBlocking {
            mutableList.filter {
                it.delatQty != 0
            }.mapNotNull {
                alteredEntries(it,
                    state.value.basket?._relationships?.basketEntries ?: emptyList()
                )
            }.map {
                updateBasketEntry(it)
            }
        }
        /*dispatch(BasketAction.RefreshBasket(
            state.value.groceriesList!!,
            state.value.idPointOfSale!!)
        )*/
        // println("Miam sendBasketEntriesChanges done run blocking")
        if (callback != null) {
            callback()
        }
    }

    override fun observeState(): StateFlow<BasketState> = state

    override fun observeSideEffect(): Flow<BasketEffect> = sideEffect

    override fun dispatch(action: BasketAction) {
        val oldState = state.value

        val newState = when (action) {
            is BasketAction.RefreshBasket -> {
                // println("Miam --> basket refresh")
                launch {
                        loadBasket(action.groceriesList.id, action.idPointOfSale)
                }
                oldState
            }
            is BasketAction.SetGroceriesList -> {
                // println("Miam --> basket setGroceries")
                val tmp =  oldState.copy(groceriesList = action.groceriesList)
                shouldUpdateBasket(tmp)
                tmp
            }
            is BasketAction.SetIdPointOfSale -> {
                val tmp = oldState.copy(idPointOfSale = action.pointOfSaleId)
                shouldUpdateBasket(tmp)
                tmp
            }
            is BasketAction.ResetUpdateBasketEntriesQueue -> {
                oldState.copy(updateBasketEntrieQueue = mutableListOf())
            }
            is BasketAction.AddBasketEntry -> {
                launch { updateBasketEntryStatus(action.entry, "active") }
                oldState
            }
            is BasketAction.RemoveEntry -> {
                launch { updateBasketEntryStatus(action.entry, "deleted") }
                oldState
            }
            is BasketAction.UpdateBasketEntries -> {
                // println("Miam UpdateBasketEntries " + action.basketEntries)
                // add all alter quantities in a queue updateBasketEntrieQueue
                // the queue may contains alter quantities if it is still not processed due to debounce
                action.basketEntries.forEach {
                    val idx =  oldState.updateBasketEntrieQueue.indexOfFirst { aqbe -> aqbe.id == it.id }
                    if(idx != -1) { // already have an alter quantity, sum them
                        // println("Miam UpdateBasketEntries changing quantity")
                        oldState.updateBasketEntrieQueue[idx] =  it.copy(delatQty = it.delatQty + oldState.updateBasketEntrieQueue[idx].delatQty)
                    } else {
                        // println("Miam UpdateBasketEntries adding element")
                        oldState.updateBasketEntrieQueue.add(it)
                    }
                }
                // println("Miam UpdateBasketEntries queue ${oldState.updateBasketEntrieQueue}")

                launch {
                    if (action.withDebounce) {
                        // println("Miam sendBasketEntriesChanges with debounc")
                        entriesSubject.emit(oldState.updateBasketEntrieQueue)
                    } else {
                        // println("Miam sendBasketEntriesChanges directly")
                        sendBasketEntriesChanges(oldState.updateBasketEntrieQueue, action.callback)
                    }
                }
                oldState
            }
            is BasketAction.ReplaceSelectedItem -> {
                // println("Miam ---> ReplaceItem")
                launch {
                    basketEntryRepo.updateBasketEntry(
                        action.basketEntry.updateSelectedItem(action.itemId)
                    ).collect {
                        // println("Miam ----> change item ")
                    }
                }
                oldState
            }
            is BasketAction.SetBasket -> {
                // println("Miam --> basket setBasket")
                val lineEntries = this.groupBasketEntries( state.value.groceriesList?.attributes?.recipesInfos ?: emptyList()
                    , action.basket._relationships?.basketEntries ?: emptyList())
                val basketPreview = BasketPreviewLine.recipesAndLineEntriesToBasketPreviewLine(state.value.groceriesList!!, lineEntries,)
                val newState = oldState.copy(basket = action.basket, basketPreview = basketPreview, recipeCount = state.value.groceriesList?.attributes?.recipesInfos?.size ?: 0 )
                dispatch(BasketAction.SetBasketState(basketPreview))
                newState
            }
            is BasketAction.SetBasketState -> {
              val newState =  setBasketStates(action.basketPreview, oldState)
                println("Basket emit changePreview")
              launch { sideEffect.emit(BasketEffect.BasketPreviewChange)}
              newState
            }
            is BasketAction.ConfirmBasket -> {
                if(oldState.basket != null) {
                    println("Miam confirm basket")
                    confirmBasket(oldState.basket, action.price)
                }
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
        // println("Miam --> sould ${tmpState.groceriesList}  ${tmpState.idPointOfSale}  ")
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

    fun basketIsEmpty( ): Boolean {
       println("Miam is basket empty ? ${state.value.basket?._relationships?.basketEntries?.isEmpty()  ==  true}")
       return (state.value.basket?._relationships?.basketEntries?.isEmpty() == true)
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

    private fun confirmBasket(basket: Basket, price: String){
       launch {
           basketRepo.updateBasket(basket.copy(attributes = basket.attributes.copy(confirmed = true))).collect {
               basket ->
               println("Maim --> basket updated")
               if(basket.attributes.token != null) {
                   supplierRepositoryImp.notifyConfirmBasket(basket.attributes.token!!).collect {
                       supplierRepositoryImp.notifyPaidBasket(basket.attributes.token, price).collect{
                           sideEffect.emit(BasketEffect.BasketConfirmed)
                       }
                   }
               }
           }
       }
    }

    private fun alteredEntries(aqbe: AlterQuantityBasketEntry, basketEntries: List<BasketEntry> ): BasketEntry ?{
        // println("Miam updatedEntry " + aqbe)
        // println("Miam updatedEntry " + basketEntries)
        var basketEntry =  basketEntries.find { it.id == aqbe.id }

        if (basketEntry != null) {
            val newQty = (basketEntry.attributes.quantity ?: 0) + aqbe.delatQty

            // println("Miam updatedEntry before items " + basketEntry._relationships?.items)
            // println("Miam updatedEntry before ge " + basketEntry._relationships?.groceriesEntry)
            basketEntry = basketEntry.updateQuantity(newQty)
            // println("Miam updatedEntry after items " + basketEntry._relationships?.items)
            // println("Miam updatedEntry after ge " + basketEntry._relationships?.groceriesEntry)
        }
        // println("Miam updatedEntry new entry " + basketEntry)
        return basketEntry
    }

    private suspend fun updateBasketEntryStatus(basketEntry: BasketEntry, status: String) {
        basketEntry.updateStatus(status)
        basketEntryRepo.updateBasketEntry(basketEntry)
        updateBasketEntry(basketEntry)
    }

    private suspend fun updateBasketEntry(basketEntry: BasketEntry) {
        // println("Miam will update basket entry $basketEntry")
        launch {
            basketEntryRepo.updateBasketEntry(basketEntry).first()
            val ge = basketEntry._relationships?.groceriesEntry
            if (ge?.needPatch == true) {
                groceriesRepo.updateGrocerieEntry(ge)
            }
        }
    }


    private suspend fun loadBasket(idGroceriesList: Int,idPointOfSale :Int ) {
        try {
            withContext(Dispatchers.Default){
                basketRepo.getFromListAndPos(idGroceriesList,idPointOfSale)
                    .collect {
                        dispatch(BasketAction.SetBasket(it))
                    }
            }
        } catch (e: Exception) {
            dispatch(BasketAction.Error(e))
        }
    }

}