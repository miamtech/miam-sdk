package com.miam.kmmMiamCore.component.pricing

import com.miam.kmmMiamCore.base.mvi.*
import com.miam.kmmMiamCore.miam_core.data.repository.PricingRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Pricing
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.math.roundToInt

open class PricingViewModel :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<PricingContract.Event, PricingContract.State, PricingContract.Effect>()  {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in Pricing view $exception ${exception.stackTraceToString()}")
    }

    private val basketStore : BasketStore by inject()

    private val pointOfSaleStore :PointOfSaleStore by inject()
    private val pricingRepository : PricingRepositoryImp by inject()

    override fun createInitialState(): PricingContract.State =
        PricingContract.State(
            price = BasicUiState.Empty,
            directPrice = null,
            recipeId = "",
            guestNumber = -1,
            integerPart = "0",
            decimalPart = "00",
            isInCart= false,
        )

    override  fun handleEvent(event: PricingContract.Event) {
        when (event) {
            is PricingContract.Event.OnPriceUpdate -> getPrice()
            is PricingContract.Event.SetPrice -> setState { copy(price = BasicUiState.Success(event.pricing))}
            is PricingContract.Event.SetDirectPrice -> {
                setState { copy(
                    price = BasicUiState.Success(Pricing(directPrice ?: 0.0,1)),
                    directPrice = event.price) }
                getPrice()
            }
            is PricingContract.Event.OnSetRecipe -> setState  { copy(recipeId = event.idRecipe, guestNumber = event.guestNumber) }
        }
    }

    init {
        launch(coroutineHandler) {
            basketStore.observeSideEffect().collect {
                setEvent(PricingContract.Event.OnPriceUpdate)
            }
        }
    }

    private  fun getPrice() {
        if (uiState.value.directPrice != null ) {
            splitePrice(uiState.value.directPrice!!)
            return
        }
        if( checkIsInCart()){
            extactPricing()
        } else {
            launch(coroutineHandler) { fetchPrice()}
        }
    }

    private fun checkIsInCart(): Boolean {
        if(currentState.recipeId == "" ) return false
        return basketStore.recipeInBasket(currentState.recipeId)
    }

    private fun extactPricing() {
      val recipeBPL = basketStore.observeState().value.basketPreview?.first { it.isRecipe && it.id == currentState.recipeId }
        if(recipeBPL != null){
            setState {
                copy(
                    price = BasicUiState.Success(
                        Pricing(recipeBPL.price.toDouble() ,currentState.guestNumber)
                    )
                )
            }
            splitePrice(recipeBPL.price.toDouble() / currentState.guestNumber)
        }
    }

    private fun splitePrice(price : Double){
        // will it work each time with different region format ?
        val priceCent = (price * 100).roundToInt().toString()
        val intergerPart = if (priceCent.length <= 2) "0" else priceCent.substring(0, priceCent.length - 2)
        val decimalPart = if (priceCent.length <= 2) priceCent.substring(0) else priceCent.substring(priceCent.length - 2)
        setState { copy( integerPart = intergerPart.toInt().toString(),
                         decimalPart = decimalPart) }
    }

    private suspend fun fetchPrice() {
        val posId = pointOfSaleStore.observeState().value.idPointOfSale
        if(uiState.value.recipeId == "" || posId == null ) return
        setState { copy(price = BasicUiState.Loading)}
        try {
            launch(coroutineHandler) {
                val recipePrice = pricingRepository.getRecipePrice(uiState.value.recipeId, posId)
                splitePrice(recipePrice.price / uiState.value.guestNumber)
                setEvent(PricingContract.Event.SetPrice(recipePrice))
            }
        } catch (e: Exception) {
            setState { copy(price =  BasicUiState.Empty ) }
        }
    }
}