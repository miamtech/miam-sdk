package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Close

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.miam.kmm_miam_sdk.android.ui.components.ItemsSelector.ItemsSelector

import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView

import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListAction
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract


import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel


import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry


import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine.Companion.fromBasketEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BasketPreview(val vmBasketPreview :BasketPreviewViewModel, val recipeVm: RecipeViewModel, val close: ()-> Unit) : KoinComponent {

    private val groceriesListStore: GroceriesListStore by inject()
    private val routerViewModel :RouterViewModel by inject()

    @ExperimentalCoilApi
    @Composable
    fun content() {

        val state by vmBasketPreview.uiState.collectAsState()

        Scaffold(
            content = {
                if (state.showItemSelector) {
                    ItemsSelector().Content()
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Text(
                                text = "1 repas ajouté à votre panier :",
                                style = MaterialTheme.typography.h5.copy(
                                    color = Color(0xff037E92),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                                    .padding(horizontal = 16.dp)
                            )
                            FloatingActionButton(modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp)
                                .alpha(0.5f),
                                backgroundColor = Color.Gray,
                                onClick = { close() })
                            {
                                Icon(
                                    tint = Color.White,
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "close"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        ManagementResourceState(
                            resourceState = state.line,
                            successView = { line ->
                                requireNotNull(line)
                                Column() {
                                    basketPreviewLine(line = line,recipeVm, vmBasketPreview, router = routerViewModel)
                                    if(vmBasketPreview.currentState.isReloading){
                                        Column(Modifier.padding(32.dp).fillMaxWidth(),
                                             horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center) {
                                            CircularProgressIndicator()
                                            Text(text = "Chargement du repas", color= MiamMasterView.Grey02,
                                                style = MaterialTheme.typography.h5.copy(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold
                                                ),)
                                        }
                                    } else {


                                    if (line.entries?.found?.isNotEmpty() == true) {
                                        Column {
                                            line.entries!!.found.map { entry ->  fromBasketEntry(entry) }.forEach { bpl ->
                                               entryLine(bpl,vmBasketPreview)
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
                                            fontColor = MiamMasterView.Primary,
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

                            },

                            onTryAgain = { /*TODO*/ },
                            onCheckAgain = { /*TODO*/ },
                            loadingView = { Column(
                                Modifier.fillMaxSize(),
                                horizontalAlignment =  Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center

                            ){
                                CircularProgressIndicator()
                            } }
                        )
                    }
                }

            },
            bottomBar = {
                if(state.showItemSelector){
                   Surface() {
                   }
                }
                else{

                BottomAppBar(backgroundColor = Color.White) {  Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    border = BorderStroke(1.dp, MiamMasterView.Primary),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MiamMasterView.Primary),
                    onClick = {
                        groceriesListStore.dispatch(GroceriesListAction.RemoveRecipe(recipeId = vmBasketPreview.recipeId!! ))
                        close()
                    }) {
                    Text(text = "Retirer le repas")
                }

                Button(
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White, backgroundColor = MiamMasterView.Primary),
                    onClick = {close() }) {
                    Text(text = "Continuer mes courses")
                }
                }
               }
                }

            }
        )
    }
}

