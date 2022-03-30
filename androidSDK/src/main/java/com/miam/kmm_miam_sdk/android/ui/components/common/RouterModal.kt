package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.dialog.DialogStyle.dialogContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.recipdeDetails
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel

import com.miam.kmm_miam_sdk.component.router.RouterContent
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RouterModal :KoinComponent {

    private var vmRouter: RouterViewModel = RouterViewModel()


    fun goToDetail(vmRecipe :RecipeViewModel){
        vmRouter.setEvent(
            RouterContract.Event.GoToDetail(
                vmRecipe
            )
        )
    }

    fun goToPreview(recipeId: Int ,vmRecipe :RecipeViewModel) {
        vmRouter.setEvent(
            RouterContract.Event.GoToPreview(
                recipeId = recipeId,
                vm = vmRecipe
            )
        )
    }



    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Content()  {

        val state by vmRouter.uiState.collectAsState()

        if (state.isOpen) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Popup(
                    properties = PopupProperties(
                        clippingEnabled= true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                        usePlatformDefaultWidth = false
                    ),
                    onDismissRequest = {
                        vmRouter.setEvent(RouterContract.Event.CloseDialog)
                    }
                ) {
                    Box(modifier = dialogContainer){
                        when(state.content){
                            RouterContent.RECIPE_DETAIL  -> state.rvm?.let { recipdeDetails(it, vmRouter, fun (){ vmRouter.setEvent(RouterContract.Event.CloseDialog)}) }
                            RouterContent.BASKET_PREVIEW -> state.bpvm?.let { BasketPreview(vmRouter ,it, state.rvm!!, fun (){ vmRouter.setEvent(RouterContract.Event.CloseDialogFromPreview)}).content() }

                        }
                    }

            }
        }

    }

}
}


