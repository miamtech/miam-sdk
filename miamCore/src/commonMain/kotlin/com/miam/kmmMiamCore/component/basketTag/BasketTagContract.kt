package com.miam.kmmMiamCore.component.basketTag

import com.miam.kmmMiamCore.base.mvi.*
import com.miam.kmmMiamCore.miam_core.model.Recipe


interface BasketTagContract {

    sealed class Event : UiEvent {
        data class SetRetailerProductId(val productId: String) : Event()
    }

    data class State(
        val recipeList: BasicUiState<List<Recipe>>,
        ) : UiState

    sealed class Effect : UiEffect {
    }
}
