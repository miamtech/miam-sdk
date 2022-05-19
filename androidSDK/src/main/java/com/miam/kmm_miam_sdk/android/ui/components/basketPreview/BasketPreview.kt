package com.miam.kmm_miam_sdk.android.ui.components.basketPreview


import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*

import androidx.compose.material.*


import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewColor.continueButtonTextColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewColor.removeButtonTextColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewImage.previous
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewStyle.footerContinueButton
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewStyle.footerRemoveButton
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewStyle.headerPreviousButton
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewStyle.headerRowModifier
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewStyle.headerRowVerticalAlignment
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewText.addedRecipe
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewText.continueShopping
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewText.removeRecipe
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.ItemsSelector
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle

import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListAction
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract


import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel


import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine


import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine.Companion.fromBasketEntry
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BasketPreview(
    private val routerViewModel: RouterViewModel,
    private val vmBasketPreview :BasketPreviewViewModel,
    private val recipeVm: RecipeViewModel,
    val close: ()-> Unit
) : KoinComponent {

    private val groceriesListStore: GroceriesListStore by inject()

    @ExperimentalCoilApi
    @Composable
    fun content() {

        val state by vmBasketPreview.uiState.collectAsState()

        Scaffold(
            topBar = {
                if (state.showItemSelector) {
                    Surface {}
                } else {
                    if (Template.basketPreviewHeaderTemplate != null) {
                        Template.basketPreviewHeaderTemplate?.let {
                            it( recipeVm ) {
                                routerViewModel.setEvent(
                                    RouterContract.Event.GoToDetailFromPreview(
                                        recipeVm
                                    )
                                )
                            }
                        }
                    } else {
                       Row(
                           modifier =  headerRowModifier,
                           verticalAlignment = headerRowVerticalAlignment
                        )
                        {
                            IconButton(
                                modifier = headerPreviousButton,
                                onClick = { routerViewModel.setEvent(RouterContract.Event.GoToDetailFromPreview(recipeVm)) }
                            ) {
                                Image(
                                    painter = painterResource(previous),
                                    contentDescription = "Previous"
                                )
                            }
                            Text(
                                text = addedRecipe,
                                style = Typography.bodyBold
                            )
                        }
                    }
                }
            },
            content = {
                if (state.showItemSelector) {
                    ItemsSelector().Content()
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        ManagementResourceState(
                            resourceState = state.line,
                            successView = { line ->
                                requireNotNull(line)
                                BasketPreviewSucessView(
                                    line ,
                                    recipeVm,
                                    vmBasketPreview ,
                                    routerViewModel
                                )
                            },
                            onTryAgain = { /*TODO*/ },
                            onCheckAgain = { /*TODO*/ },
                            loadingView = { BasketPreviewLoader() }
                        )
                    }
                }

            },
            bottomBar = {
                if(state.showItemSelector){
                   Surface {}
                }
                else{
                    BottomAppBar(backgroundColor = RecipeDetailsColor.footerSectionBackgroundColor) {
                        if(Template.basketPreviewLineFooterTemplate != null){
                            Template.basketPreviewLineFooterTemplate!!(
                                {
                                    groceriesListStore.dispatch(GroceriesListAction.RemoveRecipe(recipeId = vmBasketPreview.recipeId!! ))
                                    close()
                                },
                                { close() }
                            )
                        } else {
                            Row(
                                modifier = RecipeDetailsStyle.footerMainContainer,
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Row(footerRemoveButton.weight(1F).clickable {
                                    groceriesListStore.dispatch(GroceriesListAction.RemoveRecipe(recipeId = vmBasketPreview.recipeId!! ))
                                    close()
                                },
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment =  Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = removeRecipe,
                                        style = Typography.button,
                                        color = removeButtonTextColor
                                    )
                                }
                                    Row(
                                        modifier = footerContinueButton.weight(1f).clickable { close() },
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = continueShopping,
                                            style = Typography.button,
                                            color = continueButtonTextColor

                                        )
                                    }
                            }
                        }

                    }
                }

            }
        )
    }
}

@Composable
private fun BasketPreviewSucessView(
    line : BasketPreviewLine,
    recipeVm: RecipeViewModel,
    vmBasketPreview : BasketPreviewViewModel,
    routerViewModel: RouterViewModel
) {
    Column() {
        basketPreviewLine(line = line,recipeVm, vmBasketPreview, router = routerViewModel)
        if(vmBasketPreview.currentState.isReloading){
            BasketPreviewLoader()
        } else {

            if (line.entries?.found?.isNotEmpty() == true) {
                Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    line.entries!!.found.map { entry ->  fromBasketEntry(entry) }.forEach { bpl ->
                        EntryLine(bpl,vmBasketPreview)
                    }
                }
            }
            if (line.entries?.notFound?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                expendableEntryLine(
                    line.entries!!.notFound,
                    backGroundColor = Color(0xffEDEDED),
                    fontColor = Color(0xff252525),
                    title = "Article(s) indisponible(s) (${line.entries!!.notFound.size})"
                )
            }
            if (line.entries?.oftenDeleted?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                expendableEntryLine(
                    line.entries!!.oftenDeleted,
                    backGroundColor = Color(0xffD9EFF2),
                    fontColor = primary,
                    title = "Déjà dans vos placards ? (${line.entries!!.oftenDeleted.size})",
                    click = fun (entry : BasketEntry) { vmBasketPreview.setEvent(BasketPreviewContract.Event.AddEntry(entry)) }
                )
            }
            if (line.entries?.removed?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                expendableEntryLine(
                    line.entries!!.removed,
                    backGroundColor = Color(0xffBBBBBB),
                    fontColor = Color(0xff252525),
                    title = "Article(s) retiré(s) du panier (${line.entries!!.removed.size})",
                    click = fun (entry :BasketEntry) { vmBasketPreview.setEvent(BasketPreviewContract.Event.AddEntry(entry)) }
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 32.dp))
    }
}

@Composable
private fun BasketPreviewLoader(){
    if (Template.basketPreviewLoadingTemplate != null){
        (Template.basketPreviewLoadingTemplate!!)()
    } else {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            CircularProgressIndicator()
        }
    }
}

