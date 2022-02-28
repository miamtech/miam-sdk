package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.annotation.ExperimentalCoilApi
import com.miam.kmm_miam_sdk.android.ui.components.ItemsSelector.ItemsSelector
import com.miam.kmm_miam_sdk.android.ui.components.RecipeHelper
import com.miam.kmm_miam_sdk.android.ui.components.RecipeSponsor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.recipdeDetails
import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContent
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel


@ExperimentalFoundationApi
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun RouterModal(
    vm: BaseViewModel<*, *, *>,
    openDialog: MutableState<Boolean>,
) {

    //val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    val goTo = { destination: RecipeContract.Event -> vm.setEvent(destination as Nothing) }
    var isIngredientChecked by remember { mutableStateOf(MiamMasterView.MiamDisplayMode.INGREDIENT_MODE) }

        if (openDialog.value) {
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
                        openDialog.value = false
                    }
                ) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        if (vm is RecipeViewModel) {
                            recipdeDetails( vm, openDialog)
                        } else if (vm is BottomSheetViewModel) {
                            val state by vm.uiState.collectAsState()
                            when (state.content) {
                               /* BottomSheetContent.RECIPE_HELPER -> RecipeHelper(goTo)
                                BottomSheetContent.RECIPE_SPONSOR -> RecipeSponsor(goTo)*/
                                BottomSheetContent.BASKET_PREVIEW -> BasketPreview(
                                    vm.currentState.recipeId ?: -1, fun(){ openDialog.value = false}
                                ).content(
                                )
                                BottomSheetContent.ITEMS_SELECTOR -> ItemsSelector().Content()
                            }
                        } else {
                            Surface() {}
                        }
                    }
                }
            }
        }

}