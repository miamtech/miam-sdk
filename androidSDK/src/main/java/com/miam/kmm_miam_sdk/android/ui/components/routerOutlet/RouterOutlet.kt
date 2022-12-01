package com.miam.kmm_miam_sdk.android.ui.components.routerOutlet


import RouteService
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorContract
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.component.router.RouterContent
import com.miam.kmmMiamCore.component.router.RouterOutletContract
import com.miam.kmmMiamCore.component.router.RouterOutletViewModel
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.ItemsSelector
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetails
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RouterOutlet: KoinComponent {

    private var vmRouter: RouterOutletViewModel = RouterOutletViewModel()
    private val itemSelectorViewModel: ItemSelectorViewModel by inject()
    private val routeService: RouteService by inject()


    fun getViewModel(): RouterOutletViewModel {
        return vmRouter
    }

    fun goToDetail(vmRecipe: RecipeViewModel, showDetailsFooter: Boolean = true) {
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToDetail(
                vmRecipe, showDetailsFooter
            )
        )
    }

    fun goToPreview(recipeId: String, vmRecipe: RecipeViewModel) {
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToPreview(
                recipeId = recipeId,
                vm = vmRecipe
            )
        )
    }

    fun goToReplaceItem() {
        itemSelectorViewModel.setEvent(
            ItemSelectorContract.Event.SetReturnToBasketPreview(
                returnToPreview = {
                    if (vmRouter.currentState.recipeId != null && vmRouter.currentState.rvm != null) {
                        goToPreview(
                            vmRouter.currentState.recipeId!!,
                            vmRouter.currentState.rvm!!
                        )
                    } else {
                        close()
                    }
                }
            )
        )
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToItemSelector
        )
    }

    private fun close() {
        vmRouter.setEvent(RouterOutletContract.Event.CloseDialogFromPreview)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Content() {

        val state by vmRouter.uiState.collectAsState()

        if (state.isOpen) {

            Dialog(
                onDismissRequest = { routeService.previous() },
                properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = true)
            ) {
                FullScreenContent({ close() }, { goToReplaceItem() }, { goToDetail(it) }, state, vmRouter)
            }

        }
    }
}

@Composable
fun FullScreenContent(
    close: () -> Unit,
    goToReplaceItem: () -> Unit,
    goToDetail: (RecipeViewModel) -> Unit,
    state: RouterOutletContract.State,
    vmRouter: RouterOutletViewModel
) {
    Box {
        when (state.content) {
            RouterContent.RECIPE_DETAIL -> state.rvm?.let {
                RecipeDetails(
                    it,
                    vmRouter,
                    fun() { vmRouter.setEvent(RouterOutletContract.Event.CloseDialog) })
            }
            RouterContent.BASKET_PREVIEW -> state.rvm?.let {
                BasketPreview(
                    recipeId = state.recipeId!!,
                    it,
                    { goToDetail(it) },
                    close,
                    goToReplaceItem
                ).content()
            }
            RouterContent.ITEMS_SELECTOR -> ItemsSelector().Content()
        }
    }
}



