package com.miam.kmmMiamCore.component.router

import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.handler.LogHandler

open class RouterOutletViewModel:
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<RouterOutletContract.Event, RouterOutletContract.State, RouterOutletContract.Effect>() {

    fun goToDetail(vmRecipe : RecipeViewModel, showDetailsFooter: Boolean = true){
        LogHandler.info("Miam RouterOutletViewModel goToDetail $vmRecipe")
        setEvent(
            RouterOutletContract.Event.GoToDetail(
                vmRecipe, showDetailsFooter
            )
        )
    }

    override fun createInitialState(): RouterOutletContract.State {
        return RouterOutletContract.State(
            content = RouterContent.BASKET_PREVIEW,
            rvm = null,
            recipeId = null,
            isOpen = false,
            showFooter = true
        )
    }


    override fun handleEvent(event: RouterOutletContract.Event) {
        when (event) {
            is RouterOutletContract.Event.GoToDetail -> {
                LogHandler.info("Miam RouterOutletViewModel goToDetail event $event")
                setState { copy(rvm = event.vm, showFooter = event.withFooter) }
                LogHandler.info("Miam RouterOutletViewModel will navigate")
                navigateTo(RouterContent.RECIPE_DETAIL)
            }
            is RouterOutletContract.Event.GoToPreview -> {
                LogHandler.info("Miam RouterOutletViewModel GoToPreview event $event")
                setState { copy(
                    recipeId =event.recipeId,
                    rvm = event.vm
                )}
                navigateTo(RouterContent.BASKET_PREVIEW)
            }
            is RouterOutletContract.Event.GoToItemSelector -> {
                navigateTo(RouterContent.ITEMS_SELECTOR)
            }
            is RouterOutletContract.Event.CloseDialogFromPreview ->  {
                setEvent(RouterOutletContract.Event.CloseDialog)
            }
            is RouterOutletContract.Event.GoToDetailFromPreview -> {
                setEvent(RouterOutletContract.Event.GoToDetail(event.vm))
            }
            is RouterOutletContract.Event.SetRouterContent -> {
                setState{
                    copy(
                        content = event.routerContent
                    )
                }
            }
            RouterOutletContract.Event.GoToHelper -> navigateTo(RouterContent.RECIPE_HELPER)
            RouterOutletContract.Event.GoToSponsor -> navigateTo(RouterContent.RECIPE_SPONSOR)
            RouterOutletContract.Event.OpenDialog -> {
                LogHandler.info("Miam RouterOutletViewModel will OpenDialog ${this.currentState}")
                setState { copy(isOpen = true) }
                LogHandler.info("Miam RouterOutletViewModel will OpenDialog ${this.currentState}")
            }
            RouterOutletContract.Event.CloseDialog ->  setState { copy(isOpen = false) }
        }
    }

    private fun navigateTo( destination : RouterContent) {
        setState { copy(content = destination) }
        if (!uiState.value.isOpen) {
            setEvent(RouterOutletContract.Event.OpenDialog)
            LogHandler.info("Miam RouterOutletViewModel dialog should be open ${this.currentState}")
        }
    }
}