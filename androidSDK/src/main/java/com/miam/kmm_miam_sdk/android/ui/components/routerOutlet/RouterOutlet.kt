package com.miam.kmm_miam_sdk.android.ui.components.routerOutlet


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorContract
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.component.router.RouterContent
import com.miam.kmmMiamCore.component.router.RouterOutletContract
import com.miam.kmmMiamCore.component.router.RouterOutletViewModel
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.toggleCaret
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.ItemsSelector
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetails
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
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
        vmRouter.setEvent(RouterOutletContract.Event.GoToDetail(vmRecipe, showDetailsFooter))
    }

    fun goToPreview(recipeId: String, vmRecipe: RecipeViewModel) {
        vmRouter.setEvent(RouterOutletContract.Event.GoToPreview(recipeId = recipeId, vm = vmRecipe))
    }

    fun goToReplaceItem() {
        itemSelectorViewModel.setEvent(
            ItemSelectorContract.Event.SetReturnToBasketPreview(
                returnToPreview = {
                    if (vmRouter.currentState.recipeId != null && vmRouter.currentState.rvm != null) {
                        goToPreview(vmRouter.currentState.recipeId!!, vmRouter.currentState.rvm!!)
                    } else {
                        routeService.onCloseDialog()
                    }
                }
            )
        )
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToItemSelector
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Content() {
        val state by vmRouter.uiState.collectAsState()

        if (state.isOpen) {
            Dialog(
                onDismissRequest = { routeService.previous() },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                FullScreenContent(
                    { routeService.onCloseDialog() },
                    { routeService.previous() },
                    { goToReplaceItem() },
                    { goToDetail(it) },
                    state,
                    vmRouter
                )
            }
        }
    }
}

@Composable
fun EmptyView(close: () -> Unit) {
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = RecipeDetailsStyle.headerMainContainer,
            ) {
                Clickable(
                    onClick = { close() },
                ) {
                    Image(
                        painter = painterResource(toggleCaret),
                        contentDescription = null,
                        modifier = RecipeDetailsStyle.headerCloseIconModifier.rotate(180f)
                    )
                }
            }
        },
        content = {
            Text(text = "Une erreur s'est produite, veuillez réessayer", textAlign = TextAlign.Center)
        }
    )
}

@Composable
fun FullScreenContent(
    close: () -> Unit,
    previous: () -> Unit,
    goToReplaceItem: () -> Unit,
    goToDetail: (RecipeViewModel) -> Unit,
    state: RouterOutletContract.State,
    vmRouter: RouterOutletViewModel
) {
    Box {
        when (state.content) {
            RouterContent.RECIPE_DETAIL -> state.rvm?.let {
                RecipeDetails(it, vmRouter, close, previous)
            }
            RouterContent.BASKET_PREVIEW -> state.rvm?.let { rvm ->
                BasketPreview(
                    recipeId = state.recipeId!!,
                    recipeVm = rvm,
                    goToDetail = { goToDetail(rvm) },
                    previous = previous,
                    close = close,
                    goToItemSelector = goToReplaceItem
                ).content()
            }
            RouterContent.ITEMS_SELECTOR -> ItemsSelector().Content()
            RouterContent.EMPTY -> EmptyView(close)
            else -> {
                EmptyView(close)
            }
        }
    }
}
