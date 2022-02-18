package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView

import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel

class BasketPreview(val recipeId :Int) {

   private  val vmBasketPreview = BasketPreviewViewModel(recipeId)

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
                                // TODO
                            }) {
                            Icon(
                                tint = Color(0xff037E92),
                                imageVector = Icons.Default.Close,
                                contentDescription = "Drop-Down Arrow"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    if (state.lines.isEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        if (recipeId != -1) {
                            val recipeLine =
                                state.lines.first { line -> line.id == recipeId && line.isRecipe }
                            basketPreviewLine(line = recipeLine)

                            if (recipeLine.entries?.found?.isNotEmpty() == true) {
                                Column {
                                    recipeLine.entries!!.found.forEach { entry ->
                                        entriesLine(entry)
                                    }
                                }
                            }
                            if (recipeLine.entries?.notFound?.isNotEmpty() == true) {
                                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                                expendableEntryLine(
                                    recipeLine.entries!!.notFound,
                                    backGroundColor = Color(0xffEDEDED),
                                    fontColor = Color(0xff252525),
                                    title = "Article(s) indisponible(s) (${recipeLine.entries!!.notFound.size})"
                                )
                            }
                            if (recipeLine.entries?.oftenDeleted?.isNotEmpty() == true) {
                                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                                expendableEntryLine(
                                    recipeLine.entries!!.oftenDeleted,
                                    backGroundColor = Color(0xffD9EFF2),
                                    fontColor = MiamMasterView.Primary,
                                    title = "Déjà dans vos placards ? (${recipeLine.entries!!.oftenDeleted.size})",
                                    click = {}
                                )
                            }
                            if (recipeLine.entries?.removed?.isNotEmpty() == true) {
                                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                                expendableEntryLine(
                                    recipeLine.entries!!.removed,
                                    backGroundColor = Color(0xffBBBBBB),
                                    fontColor = Color(0xff252525),
                                    title = "Article(s) retiré(s) du panier (${recipeLine.entries!!.removed.size})",
                                    click = {}
                                )
                            }
                        } else {
                            LazyColumn {
                                items(state.lines) { line ->
                                    basketPreviewLine(line)
                                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                                }
                            }

                        }

                    }
                    Spacer(modifier = Modifier.padding(vertical = 32.dp))
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
                    onClick = { /*TODO*/ }) {
                    Text(text = "Retirer le repas")
                }

                Button(
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White, backgroundColor = MiamMasterView.Primary),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Continuer mes courses")
                }
            }
            }
            }
        )
    }
}