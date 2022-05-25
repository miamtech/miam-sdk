package com.miam.kmm_miam_sdk.android.ui.components.basketPreview



import androidx.compose.animation.ExperimentalAnimationApi
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
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle

import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract

import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel

import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine

import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine.Companion.fromBasketEntry


class BasketPreview(
    private val recipeId: String,
    private val recipeVm: RecipeViewModel,
    val goToDetail: () -> Unit,
    val close: ()-> Unit,
    val goToItemSelector: () -> Unit,
) {

    private val vmBasketPreview :BasketPreviewViewModel = BasketPreviewViewModel(recipeId)

    @ExperimentalCoilApi
    @Composable
    fun content() {

        val state by vmBasketPreview.uiState.collectAsState()


        fun removeRecipeAndClose(){
            vmBasketPreview.setEvent(BasketPreviewContract.Event.RemoveRecipe)
            close()
        }
        Scaffold(
            topBar = {

                    if (Template.basketPreviewHeaderTemplate != null) {
                        Template.basketPreviewHeaderTemplate?.let {
                            it( recipeVm ) { goToDetail() }
                        }
                    } else {
                       Row(
                           modifier =  headerRowModifier,
                           verticalAlignment = headerRowVerticalAlignment
                        )
                        {
                            IconButton(
                                modifier = headerPreviousButton,
                                onClick = { goToDetail() }
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
            },
            content = {

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
                                    { goToDetail() },
                                    { goToItemSelector()},
                                    vmBasketPreview
                                )
                            },
                            onTryAgain = { /*TODO*/ },
                            onCheckAgain = { /*TODO*/ },
                            loadingView = { BasketPreviewLoader() }
                        )
                    }
            },
            bottomBar = {
                    BottomAppBar(backgroundColor = RecipeDetailsColor.footerSectionBackgroundColor) {
                        if(Template.basketPreviewLineFooterTemplate != null){
                            Template.basketPreviewLineFooterTemplate!!(
                                {
                                   removeRecipeAndClose()
                                },
                                { close() }
                            )
                        } else {
                            Row(
                                modifier = RecipeDetailsStyle.footerMainContainer,
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Row(
                                    footerRemoveButton
                                        .weight(1F)
                                        .clickable {
                                            removeRecipeAndClose()
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
                                        modifier = footerContinueButton
                                            .weight(1f)
                                            .clickable { close() },
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
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
 fun BasketPreviewSucessView(
    line : BasketPreviewLine,
    recipeVm: RecipeViewModel,
    goToDetail: () -> Unit,
    goToItemSelector: () -> Unit,
    vmBasketPreview: BasketPreviewViewModel,
) {


    Column() {
        BasketPreviewRecipeLine(
            line = line,
            { count ->
                vmBasketPreview.setEvent(BasketPreviewContract.Event.CountChange(
                line.copy(count = count), recipeVm = recipeVm, )
             )
            },
            goToDetail
        )
            BasketPreviewContent(
                line = line,
                vmBasketPreview= vmBasketPreview,
                goToItemSelector = {goToItemSelector()}
            )
        Spacer(modifier = Modifier.padding(vertical = 32.dp))
    }
}


@Composable
fun BasketPreviewContent(
    line : BasketPreviewLine,
    vmBasketPreview: BasketPreviewViewModel,
    goToItemSelector : () -> Unit

                            ) {
    if(vmBasketPreview.currentState.isReloading){
        BasketPreviewLoader()
    } else {
        Column() {
            if (line.entries?.found?.isNotEmpty() == true) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)) {
                    line.entries!!.found.map { entry ->  fromBasketEntry(entry) }.forEach { bpl ->
                        EntryLine(bpl, vmBasketPreview, goToItemSelector)
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



