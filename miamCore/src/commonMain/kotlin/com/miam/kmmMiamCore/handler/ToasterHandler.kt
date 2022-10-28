package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.base.mvi.State
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent


data class ToasterState(
    val onSuccess: (message: String) -> Unit = {},
    val onAddRecipeText: String = "",
    val onLikeRecipeText: String = "",
) : State


object ToasterHandler : KoinComponent {

    val state = MutableStateFlow(ToasterState())

    fun setOnSuccess(successToasterCallBack: (message: String) -> Unit) {
        state.value = state.value.copy(onSuccess = successToasterCallBack)
    }

    fun setOnAddRecipeText(message: String) {
        state.value = state.value.copy(onAddRecipeText = message)
    }

    fun setOnLikeRecipeText(message: String) {
        state.value = state.value.copy(onLikeRecipeText = message)
    }

    fun onAddRecipe() {
        if (state.value.onAddRecipeText == "") return
        onSuccess(state.value.onAddRecipeText)
    }

    fun onLikeRecipe() {
        if (state.value.onLikeRecipeText == "") return
        onSuccess(state.value.onLikeRecipeText)
    }

    fun onSuccess(message: String) {
        state.value.onSuccess(message)
    }
}