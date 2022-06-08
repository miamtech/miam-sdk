package com.miam.kmm_miam_sdk.component.tag

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.model.Recipe


interface TagContract {

    sealed class Event : UiEvent {
        data class SetRetailerProductId(val productId: String) : TagContract.Event()
    }

    data class State(
        val recipeList: BasicUiState<List<Recipe>>,
        ) : UiState

    sealed class Effect : UiEffect {
    }
}

