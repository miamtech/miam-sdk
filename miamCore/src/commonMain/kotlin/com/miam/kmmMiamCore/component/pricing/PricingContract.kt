package com.miam.kmmMiamCore.component.pricing

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Pricing


interface PricingContract {

    sealed class Event: UiEvent

    data class State(
        val price: BasicUiState<Pricing>,
        val recipeId: String? = null,
        val guestNumber: Int? = null
    ): UiState

    sealed class Effect: UiEffect
}