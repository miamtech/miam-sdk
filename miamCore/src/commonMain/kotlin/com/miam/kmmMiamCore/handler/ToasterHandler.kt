package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.base.mvi.State
import kotlinx.coroutines.flow.MutableStateFlow


public data class ToasterState(
    val onSuccess: (message: String) -> Unit = {},
    val onAddRecipeText: String = "",
    val onLikeRecipeText: String = "",
) : State


public object ToasterHandler {

    public val state: MutableStateFlow<ToasterState> = MutableStateFlow(ToasterState())

    public fun setOnSuccess(successToasterCallBack: (message: String) -> Unit) {
        state.value = state.value.copy(onSuccess = successToasterCallBack)
    }

    public fun setOnAddRecipeText(message: String) {
        state.value = state.value.copy(onAddRecipeText = message)
    }

    public fun setOnLikeRecipeText(message: String) {
        state.value = state.value.copy(onLikeRecipeText = message)
    }

    public fun onAddRecipe() {
        if (state.value.onAddRecipeText == "") return
        onSuccess(state.value.onAddRecipeText)
    }

    public fun onLikeRecipe() {
        if (state.value.onLikeRecipeText == "") return
        onSuccess(state.value.onLikeRecipeText)
    }

    public fun onSuccess(message: String) {
        state.value.onSuccess(message)
    }
}