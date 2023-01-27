package com.miam.kmmMiamCore.component.router

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.services.Analytics
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceAction
import org.koin.core.component.inject

public open class RouterOutletViewModel: BaseViewModel<RouterOutletContract.Event, RouterOutletContract.State, RouterOutletContract.Effect>() {

    private val analyticsService: Analytics by inject()
    private val routeService: RouteService by inject()

    public fun goToDetail(vmRecipe: RecipeViewModel, showDetailsFooter: Boolean = true) {
        setEvent(
            RouterOutletContract.Event.GoToDetail(
                vmRecipe, showDetailsFooter
            )
        )
    }

    override fun createInitialState(): RouterOutletContract.State {
        return RouterOutletContract.State(
            content = RouterContent.EMPTY,
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
                // TODO : path
                analyticsService.sendEvent(Analytics.EVENT_PAGEVIEW, "/detail", Analytics.PlausibleProps(recipe_id = event.vm.recipeId))
                analyticsService.sendEvent(Analytics.EVENT_RECIPE_DISPLAY, "", Analytics.PlausibleProps(recipe_id = event.vm.recipeId))
                setState { copy(rvm = event.vm, showFooter = event.withFooter) }
                LogHandler.info("Miam RouterOutletViewModel will navigate")
                routeService.dispatch(RouteServiceAction.SetDialogRoute("Detail", { navigateTo(RouterContent.RECIPE_DETAIL) }, ::onClose))
                navigateTo(RouterContent.RECIPE_DETAIL)
            }
            is RouterOutletContract.Event.GoToPreview -> {
                // TODO : path
                analyticsService.sendEvent(Analytics.EVENT_PAGEVIEW, "/basket-preview", Analytics.PlausibleProps(recipe_id = event.recipeId))
                setState { copy(recipeId = event.recipeId, rvm = event.vm) }
                routeService.dispatch(RouteServiceAction.SetDialogRoute("Preview", { navigateTo(RouterContent.BASKET_PREVIEW) }, ::onClose))
                navigateTo(RouterContent.BASKET_PREVIEW)
            }
            is RouterOutletContract.Event.GoToItemSelector -> {
                // TODO : path
                analyticsService.sendEvent(Analytics.EVENT_PAGEVIEW, "/replace-item", Analytics.PlausibleProps(recipe_id = currentState.recipeId))
                routeService.dispatch(RouteServiceAction.SetDialogRoute("Item Selector", { navigateTo(RouterContent.ITEMS_SELECTOR) }, ::onClose))
                navigateTo(RouterContent.ITEMS_SELECTOR)
            }
            is RouterOutletContract.Event.Previous -> routeService.previous()
            RouterOutletContract.Event.GoToHelper -> navigateTo(RouterContent.RECIPE_HELPER)
            RouterOutletContract.Event.GoToSponsor -> navigateTo(RouterContent.RECIPE_SPONSOR)
            RouterOutletContract.Event.OpenDialog -> {
                setState { copy(isOpen = true) }
            }
        }
    }

    private fun onClose() {
        setState { copy(isOpen = false) }
    }

    private fun navigateTo(destination: RouterContent) {
        setState { copy(content = destination) }
        if (!uiState.value.isOpen) {
            setEvent(RouterOutletContract.Event.OpenDialog)
            LogHandler.info("Miam RouterOutletViewModel dialog should be open ${this.currentState}")
        }
    }
}