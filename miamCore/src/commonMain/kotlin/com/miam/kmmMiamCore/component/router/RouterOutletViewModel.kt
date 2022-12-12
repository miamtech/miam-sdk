package com.miam.kmmMiamCore.component.router

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.services.Analytics
import com.miam.kmmMiamCore.services.DialogRoute
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceAction
import org.koin.core.component.inject

open class RouterOutletViewModel: BaseViewModel<RouterOutletContract.Event, RouterOutletContract.State, RouterOutletContract.Effect>() {

    private val analyticsService: Analytics by inject()
    private val routeService: RouteService by inject()

    fun goToDetail(vmRecipe: RecipeViewModel, showDetailsFooter: Boolean = true) {
        LogHandler.info("Miam RouterOutletViewModel goToDetail $vmRecipe")
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
                navigateToDetail()
            }
            is RouterOutletContract.Event.GoToPreview -> {
                LogHandler.info("Miam RouterOutletViewModel GoToPreview event $event")
                // TODO : path
                analyticsService.sendEvent(Analytics.EVENT_PAGEVIEW, "/basket-preview", Analytics.PlausibleProps(recipe_id = event.recipeId))
                setState { copy(recipeId = event.recipeId, rvm = event.vm) }
                navigateToPreview()
            }
            is RouterOutletContract.Event.GoToItemSelector -> {
                // TODO : path
                analyticsService.sendEvent(Analytics.EVENT_PAGEVIEW, "/replace-item", Analytics.PlausibleProps(recipe_id = currentState.recipeId))
                navigateToItemSelector()
            }
            is RouterOutletContract.Event.CloseDialogFromPreview -> {
                setEvent(RouterOutletContract.Event.CloseDialog)
            }
            is RouterOutletContract.Event.GoToDetailFromPreview -> {
                setEvent(RouterOutletContract.Event.GoToDetail(event.vm))
            }
            is RouterOutletContract.Event.SetRouterContent -> {
                setState {
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
            RouterOutletContract.Event.CloseDialog -> onClose()
        }
    }

    private fun onClose() {
        setState { copy(isOpen = false) }
    }

    private fun navigateToDetail() {
        navigateTo(RouterContent.RECIPE_DETAIL)
        routeService.dispatch(
            RouteServiceAction.SetRoute(
                DialogRoute("Detail", ::navigateToDetail, ::onClose)
            )
        )
    }

    private fun navigateToPreview() {
        navigateTo(RouterContent.BASKET_PREVIEW)
        routeService.dispatch(
            RouteServiceAction.SetRoute(
                DialogRoute("Preview", ::navigateToPreview, ::onClose)
            )
        )
    }

    private fun navigateToItemSelector() {
        navigateTo(RouterContent.ITEMS_SELECTOR)
        routeService.dispatch(
            RouteServiceAction.SetRoute(
                DialogRoute("Item Selector", ::navigateToItemSelector, ::onClose)
            )
        )
    }
    
    private fun navigateTo(destination: RouterContent) {
        setState { copy(content = destination) }
        if (!uiState.value.isOpen) {
            setEvent(RouterOutletContract.Event.OpenDialog)
            LogHandler.info("Miam RouterOutletViewModel dialog should be open ${this.currentState}")
        }
    }
}