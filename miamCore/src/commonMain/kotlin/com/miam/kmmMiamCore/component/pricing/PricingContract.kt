package com.miam.kmmMiamCore.component.pricing

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Pricing


public interface PricingContract {

    public sealed class Event: UiEvent

    public data class State(
        val price: BasicUiState<Pricing>,
        val recipeId: String? = null,
        val guestNumber: Int? = null
    ): UiState

    public sealed class Effect: UiEffect
}