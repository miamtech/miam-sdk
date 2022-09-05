package com.miam.kmmMiamCore.component.pricing

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Pricing


interface PricingContract {

    sealed class Event : UiEvent {
        data class OnSetRecipe(val idRecipe: String, val guestNumber: Int) : Event()
        data class SetPrice(val pricing: Pricing) : Event()
        data class SetDirectPrice(val price: Double) : Event()
        object OnPriceUpdate : Event()
    }

    data class State(
        val price: BasicUiState<Pricing>,
        val directPrice: Double?,
        val isInCart: Boolean,
        val recipeId: String,
        val guestNumber: Int,
        val integerPart: String,
        val decimalPart: String
    ) : UiState

    sealed class Effect : UiEffect
}