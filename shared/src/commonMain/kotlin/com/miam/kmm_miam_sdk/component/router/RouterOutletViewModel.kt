package com.miam.kmm_miam_sdk.component.router

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel

open class RouterOutletViewModel:
    BaseViewModel<RouterOutletContract.Event, RouterOutletContract.State, RouterOutletContract.Effect>() {

    fun goToDetail(vmRecipe : RecipeViewModel, showDetailsFooter: Boolean = true){
        setEvent(
            RouterOutletContract.Event.GoToDetail(
                vmRecipe, showDetailsFooter
            )
        )
    }

    override fun createInitialState(): RouterOutletContract.State =
        RouterOutletContract.State(
            content= RouterContent.BASKET_PREVIEW,
            rvm = null,
            bpvm = null,
            recipeId = null,
            isOpen = false,
            showFooter = true
        )

    override fun handleEvent(event: RouterOutletContract.Event) {
        when (event) {
            is RouterOutletContract.Event.GoToDetail -> {
                setState { copy(rvm = event.vm, showFooter = event.withFooter) }
                navigateTo(RouterContent.RECIPE_DETAIL)
            }
            is RouterOutletContract.Event.GoToPreview -> {
                setState { copy(
                    recipeId =event.recipeId,
                    bpvm= BasketPreviewViewModel(event.recipeId),
                    rvm = event.vm
                )}
                navigateTo( RouterContent.BASKET_PREVIEW)
            }
            is RouterOutletContract.Event.GoToItemSelector -> {
                navigateTo(RouterContent.ITEMS_SELECTOR)
            }
            is RouterOutletContract.Event.CloseDialogFromPreview ->  {
                killBasketPreviewViewModel()
                setEvent(RouterOutletContract.Event.CloseDialog)
            }
            is RouterOutletContract.Event.GoToDetailFromPreview -> {
                killBasketPreviewViewModel()
                setEvent(RouterOutletContract.Event.GoToDetail(event.vm))
            }
            RouterOutletContract.Event.GoToHelper -> navigateTo (RouterContent.RECIPE_HELPER)
            RouterOutletContract.Event.GoToSponsor -> navigateTo(RouterContent.RECIPE_SPONSOR)
            RouterOutletContract.Event.OpenDialog ->  setState { copy(isOpen = true) }
            RouterOutletContract.Event.CloseDialog ->  setState { copy(isOpen = false) }
        }
    }

    private fun  killBasketPreviewViewModel(){
        uiState.value.bpvm?.setEvent(BasketPreviewContract.Event.KillJob)
        setState { copy(bpvm = null) }
    }

    private fun navigateTo( destination : RouterContent) {
        setState { copy(content = destination) }
        if (!uiState.value.isOpen) {
            setEvent(RouterOutletContract.Event.OpenDialog)
        }
    }
}