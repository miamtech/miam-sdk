package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import com.miam.kmm_miam_sdk.android.ui.components.ItemsSelector.ItemsSelector

import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.recipdeDetails

import com.miam.kmm_miam_sdk.component.router.RouterContent
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RouterModal :KoinComponent {
    private val vmRouter: RouterViewModel by inject()

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Content()  {

        val state by vmRouter.uiState.collectAsState()

        if (state.isOpen) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Dialog(
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                        usePlatformDefaultWidth = false
                    ),
                    onDismissRequest = {
                        vmRouter.setEvent(RouterContract.Event.CloseBottomSheet)
                    }
                ) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        when(state.content){
                          RouterContent.RECIPE_DETAIL  -> state.vm?.let { recipdeDetails(state.vm!!, fun (){ vmRouter.setEvent(RouterContract.Event.CloseBottomSheet)}) }
                          RouterContent.BASKET_PREVIEW -> state.recipeId?.let { BasketPreview(it, fun (){ vmRouter.setEvent(RouterContract.Event.CloseBottomSheet)}).content() }
                          RouterContent.ITEMS_SELECTOR -> ItemsSelector().Content()
                    }
                }
            }
        }

    }

}
}


