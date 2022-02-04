package com.miam.kmm_miam_sdk.component.pricing

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.data.repository.PricingRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Pricing
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import java.lang.String


open class PricingViewModel :
    BaseViewModel<PricingContract.Event, PricingContract.State, PricingContract.Effect>()  {

    private val basketStore : BasketStore by inject()

    private val pointOfSaleStore :PointOfSaleStore by inject()
    private val pricingRepository : PricingRepositoryImp by inject()

    override fun createInitialState(): PricingContract.State =
        PricingContract.State(
            price = BasicUiState.Empty,
            recipeId = -1,
            integerPart = 0,
            decimalPart = 0,
            isInCart= false,
        )

    override  fun handleEvent(event: PricingContract.Event) {
        when (event) {
            is PricingContract.Event.OnPriceUpdate -> getPrice()
            is PricingContract.Event.OnSetRecipe -> setState  { copy(recipeId = event.idRecipe) }
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
        // checkIf recipe is in basket
        // extract price

        launch { fetchPrice()}
    }

    private fun extactPricing() {
        val pricing = Pricing(4.15, 1)
        splitePrice(pricing)
    }

    private fun splitePrice(pricing : Pricing){
        val splitedPrice = String.format("%.2f", pricing.price).split('.');
        setState { copy(price= BasicUiState.Success(pricing),
                        integerPart = splitedPrice[0].toInt(),
                        decimalPart = splitedPrice[1].toInt()) }
    }


    private suspend fun fetchPrice() {
        val posId = pointOfSaleStore.observeState().value.idPointOfSale
        if(uiState.value.recipeId == -1 || posId == null ) return

        try {
            launch {
                pricingRepository.getRecipePrice(uiState.value.recipeId, posId)
                    .collect {
                      splitePrice(it)
                    }
            }
        } catch (e: Exception) {
            setState { copy(price =  BasicUiState.Empty ) }
        }
    }
}
