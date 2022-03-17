package com.miam.kmm_miam_sdk.component.router

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel

open class RouterViewModel:
    BaseViewModel<RouterContract.Event, RouterContract.State, RouterContract.Effect>() {

    override fun createInitialState(): RouterContract.State =
        RouterContract.State(
            content= RouterContent.BASKET_PREVIEW,
            rvm = null,
            bpvm = null,
            recipeId = null,
            isOpen = false
        )

    override fun handleEvent(event: RouterContract.Event) {
        when (event) {
            is RouterContract.Event.GoToDetail -> {
                setState { copy(rvm = event.vm) }
                navigateTo(RouterContent.RECIPE_DETAIL)
            }
            is RouterContract.Event.GoToPreview -> {
                setState { copy(
                    recipeId =event.recipeId,
                    bpvm= BasketPreviewViewModel(event.recipeId),
                    rvm = event.vm
                )}
                navigateTo( RouterContent.BASKET_PREVIEW)
            }
            is RouterContract.Event.GoToItemSelector -> {
                navigateTo(RouterContent.ITEMS_SELECTOR)
            }
            RouterContract.Event.GoToHelper -> navigateTo (RouterContent.RECIPE_HELPER)
            RouterContract.Event.GoToSponsor -> navigateTo(RouterContent.RECIPE_SPONSOR)
            RouterContract.Event.OpenDialog ->  setState { copy(isOpen = true) }
            RouterContract.Event.CloseDialog ->  setState { copy(isOpen = false) }
        }

    }
    private fun navigateTo( destination : RouterContent) {
        setState { copy(content = destination) }
        if (!uiState.value.isOpen) {
            setEvent(RouterContract.Event.OpenDialog)
        }
    }
}