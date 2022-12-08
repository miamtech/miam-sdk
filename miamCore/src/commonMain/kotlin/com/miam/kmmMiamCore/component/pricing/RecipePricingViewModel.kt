package com.miam.kmmMiamCore.component.pricing

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.helpers.dualLet
import com.miam.kmmMiamCore.miam_core.data.repository.PricingRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmmMiamCore.miam_core.model.Pricing
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class RecipePricingViewModel: BaseViewModel<PricingContract.Event, PricingContract.State, PricingContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in Pricing view $exception ${exception.stackTraceToString()}")
    }

    private val basketStore: BasketStore by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()
    private val pricingRepository: PricingRepositoryImp by inject()

    private val viewModelScope = CoroutineScope(coroutineContext)

    override fun createInitialState(): PricingContract.State = PricingContract.State(price = BasicUiState.Loading)

    override fun handleEvent(event: PricingContract.Event) {
        TODO("Not yet implemented")
    }


    fun setRecipe(recipeId: String, guestNumber: Int) {
        setState { copy(recipeId = recipeId, guestNumber = guestNumber) }
        getPrice(recipeId, guestNumber)
    }

    fun listenBasketChanges() {
        viewModelScope.launch(coroutineHandler) {
            launch(coroutineHandler) {
                basketStore.observeSideEffect().collect {
                    dualLet(currentState.recipeId, currentState.guestNumber) { recipeId, guestNumber ->
                        // when a basket update happen only need to update price if recipe is in cart
                        currentBPL(recipeId)?.let {
                            getPrice(recipeId, guestNumber)
                        }
                    }
                }
            }
        }
    }

    fun stopListenBasketChanges() {
        viewModelScope.cancel()
    }

    private fun getPrice(recipeId: String, guestNumber: Int) {
        currentBPL(recipeId)?.let { bpl ->
            // recipe is in basket
            return setState { copy(price = BasicUiState.Success(Pricing(price = bpl.price.toDouble(), serves = guestNumber))) }
        }
        pointOfSaleStore.observeState().value.idPointOfSale?.let { posId ->
            launch(coroutineHandler) {
                setState { copy(price = BasicUiState.Loading) }
                val recipePrice = pricingRepository.getRecipePrice(recipeId, posId, guestNumber)
                setState { copy(price = BasicUiState.Success(Pricing(price = recipePrice.price, serves = guestNumber))) }
            }
            return
        }
        setState { copy(price = BasicUiState.Error("Could not retrieve price for $recipeId")) }
    }

    private fun currentBPL(recipeId: String): BasketPreviewLine? {
        return basketStore.observeState().value.basketPreview?.firstOrNull { it.isRecipe && it.id == recipeId }
    }
}
