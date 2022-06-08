package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.base.executor.ExecutorHelper
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler
import com.miam.kmm_miam_sdk.handler.LogHandler
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
    val id: String,
    val delatQty: Int,
)

data class BasketState(
    val basket :Basket?,
    val basketPreview : List<BasketPreviewLine>?,
    val entriesCount : Int? =0,
    val recipeCount :Int? =0,
    val totalPrice : Double? =0.0
) : State

sealed class  BasketAction : Action {
    object RefreshBasket : BasketAction()
    data class AddBasketEntry(val entry: BasketEntry): BasketAction()
    data class RemoveEntry(val entry: BasketEntry): BasketAction()
    data class UpdateBasketEntries(
        val basketEntries: List<BasketEntry>
    ): BasketAction()
    data class UpdateBasketEntriesDiff(
        val basketEntriesDiff: List<AlterQuantityBasketEntry>
    ): BasketAction()
    data class ReplaceSelectedItem(val basketEntry :BasketEntry, val itemId :Int): BasketAction()
    data class ConfirmBasket(val price: String) : BasketAction()
}

sealed class  BasketEffect : Effect {
    object BasketPreviewChange: BasketEffect()
    object BasketConfirmed :BasketEffect()
}

class BasketStore : Store<BasketState, BasketAction, BasketEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in BasketStore $exception ${exception.stackTraceToString()}")
    }

    override val state = MutableStateFlow(BasketState( null, emptyList()))
    private val sideEffect = MutableSharedFlow<BasketEffect>()
    private val basketRepo : BasketRepositoryImp by inject()
    private val basketEntryRepo :BasketEntryRepositoryImp by inject()
    private val groceriesRepo : GroceriesEntryRepositoryImp by inject()
    private val supplierRepositoryImp: SupplierRepositoryImp by inject()
    private val basketHandler: BasketHandler by inject()
    private val groceriesListStore: GroceriesListStore by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()

    override fun observeState(): StateFlow<BasketState> = state

    override fun observeSideEffect(): Flow<BasketEffect> = sideEffect

    fun getBasket(): Basket? {
        return state.value.basket
    }

    override fun dispatch(action: BasketAction): Job {
        when (action) {
            is BasketAction.RefreshBasket -> {
                val gl = groceriesListStore.getGroceriesList()
                val posId = pointOfSaleStore.getPosId()
                LogHandler.debug("[Miam] RefreshBasket $gl $posId")
                if (gl == null || posId == null) return ExecutorHelper.emptyJob()

                return launch(coroutineHandler) {
                    val basket = basketRepo.getFromListAndPos(gl.id, posId)
                    setBasket(gl, basket)
                }
            }
            is BasketAction.AddBasketEntry -> {
                // basket preview is already updated by the view
                return launch(coroutineHandler) {
                    val newEntry = updateBasketEntryStatus(action.entry, "active")
                    val newBasket = state.value.basket?.updateBasketEntry(newEntry)
                    setBasket(groceriesListStore.getGroceriesList()!!, newBasket!!)
                }
            }
            is BasketAction.RemoveEntry -> {
                // basket preview is already updated by the view
                return launch(coroutineHandler) {
                    val newEntry = updateBasketEntryStatus(action.entry, "deleted")
                    val newBasket = state.value.basket?.updateBasketEntry(newEntry)
                    setBasket(groceriesListStore.getGroceriesList()!!, newBasket!!)
                }
            }
            is BasketAction.ReplaceSelectedItem -> {
                val replacedEntry = action.basketEntry.updateSelectedItem(action.itemId)
                return launch(coroutineHandler) {
                    val newEntry = updateBasketEntry(replacedEntry)
                    val newBasket = state.value.basket?.updateBasketEntry(newEntry)
                    setBasket(groceriesListStore.getGroceriesList()!!, newBasket!!)
                }
            }
            is BasketAction.UpdateBasketEntriesDiff -> {
                val basketEntries = action.basketEntriesDiff.filter { alterQty ->
                    alterQty.delatQty != 0
                }.mapNotNull { alterQty ->
                    alteredEntries(alterQty, state.value.basket?.relationships?.basketEntries?.data ?: emptyList())
                }
                return dispatch(BasketAction.UpdateBasketEntries(basketEntries))
            }
            is BasketAction.UpdateBasketEntries -> {
                return launch(coroutineHandler) {
                    action.basketEntries.map { async { updateBasketEntry(it) } }.awaitAll()
                    // wait for all jobs to complete
                    dispatch(BasketAction.RefreshBasket)
                }
            }
            is BasketAction.ConfirmBasket -> {
                return launch(coroutineHandler) {
                    if(state.value.basket != null) {
                        confirmBasket(state.value.basket!!, action.price)
                        groceriesListStore.dispatch(GroceriesListAction.ResetGroceriesList)
                    }
                }
            }
        }
    }

    fun fastRemoveRecipeFromBpl(recipeId: String) {
        val newState = state.value.copy(basketPreview = state.value.basketPreview?.filter { bpl -> bpl.id != recipeId })
        updateStateIfChanged(newState)
    }

    fun activeEntries(): List<BasketEntry>? {
        return state.value.basket?.relationships?.basketEntries?.data?.filter { e ->
            e.attributes!!.groceriesEntryStatus == "active"
        }
    }

    private fun setBasket(groceriesList: GroceriesList, basket: Basket) {
        val lineEntries = this.groupBasketEntries( groceriesList.attributes?.recipesInfos ?: emptyList()
            , basket.relationships?.basketEntries?.data ?: emptyList())
        val basketPreview = BasketPreviewLine.recipesAndLineEntriesToBasketPreviewLine(groceriesList, lineEntries,)
        val entriesCountAndPrice = this.entriesFoundAndPrice(basketPreview)
        val newState = state.value.copy(
            basket = basket,
            basketPreview = basketPreview,
            recipeCount = groceriesList.attributes?.recipesInfos?.size ?: 0,
            entriesCount = entriesCountAndPrice.first,
            totalPrice = entriesCountAndPrice.second
        )
        updateStateIfChanged(newState)
        launch(coroutineHandler) {
            sideEffect.emit(BasketEffect.BasketPreviewChange)
        }
        basketHandler.basketChange(activeEntries()!!)
    }

    private fun groupBasketEntries(recipesInfos : List<RecipeInfos>, entries : List<BasketEntry>) : List<LineEntries> {
        return recipesInfos.map { ri ->
            val lineEntries = LineEntries()
            entries.filter { entry -> entry.attributes!!.recipeIds?.contains(ri.id) ?: false }
                .forEach { matchingEntry ->
                    val available  = matchingEntry.attributes!!.selectedItemId
                    if(available != null) {
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
       return (state.value.basket?.relationships?.basketEntries?.data?.isEmpty() == true)
    }

    fun recipeInBasket(recipeId: String): Boolean{
       return  state.value.basketPreview?.any { it.isRecipe && it.id == recipeId } == true
    }

    private fun entriesFoundAndPrice(basketPreview: List<BasketPreviewLine>) : Pair<Int, Double> {
        val entriesFound: List<BasketEntry> = basketPreview.map { bpl -> bpl.entries?.found ?: emptyList() }.flatten()
        var totalPrice = 0.0

        entriesFound.forEach { e ->
            val item = e.attributes!!.basketEntriesItems?.find { bei -> bei.itemId != null && bei.itemId == e.attributes.selectedItemId }
            totalPrice += (e.attributes.quantity ?: 0) * (item?.unitPrice ?: 0.0)
        }
        return Pair(entriesFound.size, totalPrice)
    }

    private suspend fun confirmBasket(basket: Basket, price: String){
       val newBasket = basketRepo.updateBasket(basket.copy(attributes = basket.attributes!!.copy(confirmed = true)))
       if(newBasket.attributes!!.token != null) {
           supplierRepositoryImp.notifyConfirmBasket(newBasket.attributes!!.token!!)
           supplierRepositoryImp.notifyPaidBasket(newBasket.attributes!!.token!!, price)
       }
    }

    private fun alteredEntries(aqbe: AlterQuantityBasketEntry, basketEntries: List<BasketEntry> ): BasketEntry ?{
        var basketEntry =  basketEntries.find { it.id == aqbe.id.toString() }

        if (basketEntry != null) {
            val newQty = (basketEntry.attributes!!.quantity ?: 0) + aqbe.delatQty
            basketEntry = basketEntry.updateQuantity(newQty)
        }
        return basketEntry
    }

    private suspend fun updateBasketEntryStatus(basketEntry: BasketEntry, status: String): BasketEntry {
        basketEntry.updateStatus(status)
        return updateBasketEntry(basketEntry)
    }

    private suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry {
        // send update on ge first so if status changed you get resuts in be groceries_entry_status
        val ge = basketEntry.relationships?.groceriesEntry?.data
        if (ge?.needPatch == true) {
            groceriesRepo.updateGrocerieEntry(ge)
        }
        return basketEntryRepo.updateBasketEntry(basketEntry)
    }
}