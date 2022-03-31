package com.miam.kmm_miam_sdk.component.pricing

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.data.repository.PricingRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Pricing
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.math.roundToInt

open class PricingViewModel :
    BaseViewModel<PricingContract.Event, PricingContract.State, PricingContract.Effect>()  {

    private val basketStore : BasketStore by inject()

    private val pointOfSaleStore :PointOfSaleStore by inject()
    private val pricingRepository : PricingRepositoryImp by inject()

    override fun createInitialState(): PricingContract.State =
        PricingContract.State(
            price = BasicUiState.Empty,
            directPrice = null,
            recipeId = "",
            guestNumber = -1,
            integerPart = 0,
            decimalPart = 0,
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
        launch {
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
        // checkIf recipe is in basket
        // extract price

        launch { fetchPrice()}
    }

    private fun extactPricing() {
        // TODO extract from basket
        val pricing = Pricing(4.15, 1)
       //splitePrice(pricing)
    }

    private fun splitePrice(price : Double){
        // will it work each time with different region format ?
        val priceCent = (price * 100).roundToInt().toString()
        val intergerPart = if (priceCent.length <= 2) "00" else priceCent.substring(0, priceCent.length - 2)
        val decimalPart = if (priceCent.length <= 2) priceCent.substring(0) else priceCent.substring(priceCent.length - 2)
        setState { copy( integerPart = intergerPart.toInt(),
                         decimalPart = decimalPart.toInt()) }
    }

    private suspend fun fetchPrice() {
        val posId = pointOfSaleStore.observeState().value.idPointOfSale
        if(uiState.value.recipeId == "" || posId == null ) return
        setState { copy(price = BasicUiState.Loading)}
        try {
            launch {
                val recipePrice = pricingRepository.getRecipePrice(uiState.value.recipeId, posId)
                splitePrice(recipePrice.price / uiState.value.guestNumber)
                setEvent(PricingContract.Event.SetPrice(recipePrice))
            }
        } catch (e: Exception) {
            setState { copy(price =  BasicUiState.Empty ) }
        }
    }
}
