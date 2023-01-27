package com.miam.kmmMiamCore.component.basketTag

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe


public interface BasketTagContract {

    public sealed class Event : UiEvent {
        public data class SetRetailerProductId(val productId: String) : Event()
    }

    public data class State(
        val recipeList: BasicUiState<List<Recipe>>,
    ) : UiState

    public sealed class Effect : UiEffect
}

