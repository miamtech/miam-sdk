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
    data class RefreshBasket(
        val groceriesList: GroceriesList,
        val idPointOfSale: Int,
        val callback: (()-> Unit)? = null
    ): BasketAction()
    data class SetBasket(
        val basket: Basket,
        val callback: (()-> Unit)? = null
    ): BasketAction()
    data class SetGroceriesList(val groceriesList: GroceriesList) : BasketAction()
    data class SetIdPointOfSale(val pointOfSaleId: Int) : BasketAction()
    data class SetBasketStats(val basketPreview: List<BasketPreviewLine>) :BasketAction()
    data class AddBasketEntry(val entry: BasketEntry): BasketAction()
    data class RemoveEntry(val entry: BasketEntry): BasketAction()
    data class UpdateBasketEntries(
        val basketEntries: List<BasketEntry>,
        val callback: (()-> Unit)? = null
    ): BasketAction()
    data class UpdateBasketEntriesDiff(
        val basketEntriesDiff: List<AlterQuantityBasketEntry>,
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

    init { }

    override fun observeState(): StateFlow<BasketState> = state

    override fun observeSideEffect(): Flow<BasketEffect> = sideEffect

    override fun dispatch(action: BasketAction) {
        // println("Miam basket dispatch : $action")
        when (action) {
            is BasketAction.RefreshBasket -> {
                // println("Miam --> basket refresh")
                launch {
                    val basket = loadBasket(action.groceriesList.id, action.idPointOfSale)
                    dispatch(BasketAction.SetBasket(basket, action.callback)) // will set state here
                }
                // do not wait for completion
            }
            is BasketAction.SetGroceriesList -> {
                // println("Miam --> basket setGroceries")
                val newState =  state.value.copy(groceriesList = action.groceriesList)
                if(shouldUpdateBasket(newState)){
                    dispatch(BasketAction.RefreshBasket(newState.groceriesList!!, newState.idPointOfSale!!))
                }
                updateStateIfChanged(newState)
            }
            is BasketAction.SetIdPointOfSale -> {
                val newState =  state.value.copy(idPointOfSale = action.pointOfSaleId)
                if(shouldUpdateBasket(newState)){
                    dispatch(BasketAction.RefreshBasket(newState.groceriesList!!, newState.idPointOfSale!!))
                }
                updateStateIfChanged(newState)
            }
            is BasketAction.ResetUpdateBasketEntriesQueue -> {
                val newState =  state.value.copy(updateBasketEntrieQueue = mutableListOf())
                updateStateIfChanged(newState)
            }
            is BasketAction.AddBasketEntry -> {
                launch {
                    updateBasketEntryStatus(action.entry, "active")
                    // TODO : update state ???
                }
                // do not wait for completion
            }
            is BasketAction.RemoveEntry -> {
                launch {
                    updateBasketEntryStatus(action.entry, "deleted")
                    // TODO : update state ???
                }
                // do not wait for completion
            }
            is BasketAction.UpdateBasketEntries -> {
                // println("Miam basketStore UpdateBasketEntries " + action.basketEntries)
                launch {
                    action.basketEntries.map { async { updateBasketEntry(it) } }.awaitAll()
                    // wait for all jobs to complete
                    // println("Miam basketStore UpdateBasketEntries will refresh basket")
                    dispatch(
                        // will set state
                        BasketAction.RefreshBasket(state.value.groceriesList!!, state.value.idPointOfSale!!, action.callback)
                    )
                }
            }
            is BasketAction.UpdateBasketEntriesDiff -> {
                // println("Miam basketStore UpdateBasketEntriesDiff " + action.basketEntriesDiff)
                val basketEntries = action.basketEntriesDiff.filter { alterQty ->
                    alterQty.delatQty != 0
                }.mapNotNull { alterQty ->
                    alteredEntries(alterQty, state.value.basket?._relationships?.basketEntries ?: emptyList())
                }
                dispatch(BasketAction.UpdateBasketEntries(basketEntries, action.callback))
            }
            is BasketAction.ReplaceSelectedItem -> {
                // println("Miam ---> ReplaceItem")
                action.basketEntry.updateSelectedItem(action.itemId)
                launch {
                    updateBasketEntry(action.basketEntry)
                    // TODO : update state ???
                }
                // do not wait for job completion
            }
            is BasketAction.SetBasket -> {
                // println("Miam --> basket setBasket")
                val lineEntries = this.groupBasketEntries( state.value.groceriesList?.attributes?.recipesInfos ?: emptyList()
                    , action.basket._relationships?.basketEntries ?: emptyList())
                val basketPreview = BasketPreviewLine.recipesAndLineEntriesToBasketPreviewLine(state.value.groceriesList!!, lineEntries,)
                val newState = state.value.copy(basket = action.basket, basketPreview = basketPreview, recipeCount = state.value.groceriesList?.attributes?.recipesInfos?.size ?: 0 )
                dispatch(BasketAction.SetBasketStats(basketPreview))
                updateStateIfChanged(newState)
                action.callback?.let { it() }
            }
            is BasketAction.SetBasketStats -> {
                val newState =  setBasketStats(action.basketPreview, state.value)
                // println("Basket emit changePreview")
                launch { sideEffect.emit(BasketEffect.BasketPreviewChange) }
                updateStateIfChanged(newState)
            }
            is BasketAction.ConfirmBasket -> {
                if(state.value.basket != null) {
                    // println("Miam confirm basket")
                    confirmBasket(state.value.basket!!, action.price)
                }
                // no state to set
            }
            is BasketAction.Error -> {
                // TODO
            }
        }
    }

    private fun updateStateIfChanged(newState: BasketState) {
        if (newState != state.value) {
            state.value = newState
        }
    }

    private fun shouldUpdateBasket(tmpState: BasketState): Boolean {
        // println("Miam --> sould ${tmpState.groceriesList}  ${tmpState.idPointOfSale}  ")
        return tmpState.groceriesList != null && tmpState.idPointOfSale != null
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
    //    println("Miam is basket empty ? ${state.value.basket?._relationships?.basketEntries?.isEmpty()  ==  true}")
       return (state.value.basket?._relationships?.basketEntries?.isEmpty() == true)
    }

    private fun setBasketStats(basketPreview: List<BasketPreviewLine>, oldState: BasketState) : BasketState {

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
            //    println("Maim --> basket updated")
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
        // println("Miam alteredEntries " + aqbe)
        // println("Miam alteredEntries " + basketEntries)
        var basketEntry =  basketEntries.find { it.id == aqbe.id }

        // println("Miam alteredEntries found $basketEntry")
        if (basketEntry != null) {
            val newQty = (basketEntry.attributes.quantity ?: 0) + aqbe.delatQty
            // println("Miam updatedEntry before items " + basketEntry._relationships?.items)
            // println("Miam updatedEntry before ge " + basketEntry._relationships?.groceriesEntry)
            basketEntry = basketEntry.updateQuantity(newQty)
            // println("Miam updatedEntry after items " + basketEntry._relationships?.items)
            // println("Miam updatedEntry after ge " + basketEntry._relationships?.groceriesEntry)
        }
        // println("Miam updatedEntry new entry " + basketEntry)
        // println("Miam alteredEntries return $basketEntry")
        return basketEntry
    }

    private suspend fun updateBasketEntryStatus(basketEntry: BasketEntry, status: String) {
        basketEntry.updateStatus(status)
        updateBasketEntry(basketEntry)
    }

    private suspend fun updateBasketEntry(basketEntry: BasketEntry) {
        // println("Miam will update basket entry $basketEntry")
        // send update on ge first so if status changed you get resuts in be groceries_entry_status
        val ge = basketEntry._relationships?.groceriesEntry
        if (ge?.needPatch == true) {
            groceriesRepo.updateGrocerieEntry(ge)
        }
        basketEntryRepo.updateBasketEntry(basketEntry)
    }


    private suspend fun loadBasket(idGroceriesList: Int,idPointOfSale :Int ): Basket {
        return basketRepo.getFromListAndPos(idGroceriesList, idPointOfSale)
    }

}