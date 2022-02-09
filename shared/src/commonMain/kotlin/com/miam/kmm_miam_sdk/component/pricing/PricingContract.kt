package com.miam.kmm_miam_sdk.component.pricing

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.Pricing


interface PricingContract {

    sealed class Event : UiEvent {
        data class OnSetRecipe(val idRecipe: Int): Event()
        object OnPriceUpdate: Event()
    }

    data class State(
        val price: BasicUiState<Pricing>,
        val isInCart : Boolean,
        val recipeId: Int,
        val integerPart : Int,
        val decimalPart: Int
    ) : UiState

    sealed class Effect : UiEffect {
    }
}