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

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView

import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListAction
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore


import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel

import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine.Companion.fromBasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BasketPreview(recipeId :Int ,val close: ()-> Unit) : KoinComponent {

    private  val vmBasketPreview = BasketPreviewViewModel(recipeId)
    private val groceriesListStore: GroceriesListStore by inject()
    private val itemSelectorViewModel: ItemSelectorViewModel by inject()
    private val bottomSheetViewModel :BottomSheetViewModel by inject()

    @ExperimentalCoilApi
    @Composable
    fun content() {

        val state by vmBasketPreview.uiState.collectAsState()

        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                Row(
                    Modifier.fillMaxWidth(),
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
                            .padding(horizontal = 30.dp)
                    )
                    IconButton(
                        modifier = Modifier
                            .size(36.dp)
                            .border(
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color(0xffD9D9D9)
                                ),
                                shape = CircleShape
                            ),
                        onClick = {
                            close()
                        }) {
                        Icon(
                            tint = Color(0xff037E92),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Drop-Down Arrow"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                ManagementResourceState(
                    resourceState = state.line,
                    successView = { line ->
                        requireNotNull(line)
                            Column() {
                                basketPreviewLine(line = line)

                                if (line.entries?.found?.isNotEmpty() == true) {
                                    Column {
                                        line.entries!!.found.map { entry ->  fromBasketEntry(entry) }.forEach { bpl ->
                                            entryLine(bpl, itemSelectorViewModel, bottomSheetViewModel)
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
                                        click = {}
                                    )
                                }
                                if (line.entries?.removed?.isNotEmpty() == true) {
                                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                                    expendableEntryLine(
                                        line.entries!!.removed,
                                        backGroundColor = Color(0xffBBBBBB),
                                        fontColor = Color(0xff252525),
                                        title = "Article(s) retiré(s) du panier (${line.entries!!.removed.size})",
                                        click = {}
                                    )
                                }
                                Spacer(modifier = Modifier.padding(vertical = 32.dp))
                            }

                },

                onTryAgain = { /*TODO*/ },
                onCheckAgain = { /*TODO*/ },
                )
              }
            },
            bottomBar = { BottomAppBar(backgroundColor = Color.White) {  Row(
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
        )
    }
}
