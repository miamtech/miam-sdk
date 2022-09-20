package com.miam.kmmMiamCore.component.pricing

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.miam_core.data.repository.PricingRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Pricing
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.math.roundToInt

open class PricingViewModel :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<PricingContract.Event, PricingContract.State, PricingContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in Pricing view $exception ${exception.stackTraceToString()}")
    }

    private val basketStore: BasketStore by inject()

    private val pointOfSaleStore: PointOfSaleStore by inject()
    private val pricingRepository: PricingRepositoryImp by inject()

    override fun createInitialState(): PricingContract.State =
        PricingContract.State(
            price = BasicUiState.Loading,
            directPrice = null,
            recipeId = "",
            guestNumber = -1,
            integerPart = "0",
            decimalPart = "00",
            isInCart = false,
        )

    override fun handleEvent(event: PricingContract.Event) {
        when (event) {
            is PricingContract.Event.OnPriceUpdate -> getPrice()
            is PricingContract.Event.SetPrice -> setState { copy(price = BasicUiState.Success(event.pricing)) }
            is PricingContract.Event.SetDirectPrice -> {
                setState {
                    copy(
                        price = BasicUiState.Success(Pricing(event.price, 1)),
                        directPrice = event.price
                    )
                }
                getPrice()
            }
            is PricingContract.Event.OnSetRecipe -> {
                setState {
                    copy(
                        recipeId = event.idRecipe,
                        guestNumber = event.guestNumber
                    )
                }
                getPrice()
            }
        }
    }

    init {
        launch(coroutineHandler) {
            basketStore.observeSideEffect().collect {
                getPrice()
            }
        }
    }

    fun getPrice() {
        if (uiState.value.directPrice != null) {
            return
        }
        if (checkIsInCart()) {
            extractPricing()
        } else {
            launch(coroutineHandler) { fetchPrice() }
        }
    }

    private fun checkIsInCart(): Boolean {
        if (currentState.recipeId == "") return false
        return basketStore.recipeInBasket(currentState.recipeId)
    }

    private fun extractPricing() {
        val recipeBPL =
            basketStore.observeState().value.basketPreview?.first { it.isRecipe && it.id == currentState.recipeId }
        if (recipeBPL != null) {
            setState {
                copy(
                    price = BasicUiState.Success(
                        Pricing(recipeBPL.price.toDouble(), currentState.guestNumber)
                    )
                )
            }
        }
    }


    private suspend fun fetchPrice() {
        val posId = pointOfSaleStore.observeState().value.idPointOfSale
        if (uiState.value.recipeId == "" || posId == null) return
        setState { copy(price = BasicUiState.Loading) }
        try {
            launch(coroutineHandler) {
                val recipePrice = pricingRepository.getRecipePrice(uiState.value.recipeId, posId)

                val price =
                    ((recipePrice.price / uiState.value.guestNumber) * 100).roundToInt()
                        .toDouble() / 100
                setEvent(
                    PricingContract.Event.SetPrice(
                        Pricing(
                            price,
                            currentState.guestNumber
                        )
                    )
                )
            }
        } catch (e: Exception) {
            setState { copy(price = BasicUiState.Empty) }
        }
    }
}
