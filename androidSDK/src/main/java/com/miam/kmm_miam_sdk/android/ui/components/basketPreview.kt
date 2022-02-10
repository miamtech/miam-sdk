package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine

class BasketPreview(val recipeId :Int) {

   private  val vmBasketPreview = BasketPreviewViewModel(recipeId)


    @Composable
    fun content() {

        val state by vmBasketPreview.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
                            shape = RoundedCornerShape(100.dp)
                        ),
                    onClick = {
                       // TODO
                    }) {
                    Icon(
                        tint = Color(0xff037E92),
                        imageVector = Icons.Default.Cancel,
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
                        LazyColumn {
                            items(recipeLine.entries!!.found) { entry ->
                                EntriesLine(entry)
                            }
                        }
                    }
                    if (recipeLine.entries?.notFound?.isNotEmpty() == true) {
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        ExpendableEntryLine(
                            recipeLine.entries!!.notFound,
                            backGroundColor = Color.DarkGray,
                            fontColor = Color.White,
                            title = "Article(s) indisponible(s) ${recipeLine.entries!!.notFound.size}"
                        )
                    }
                    if (recipeLine.entries?.oftenDeleted?.isNotEmpty() == true) {
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        ExpendableEntryLine(
                            recipeLine.entries!!.oftenDeleted,
                            backGroundColor = Color.DarkGray,
                            fontColor = Color.White,
                            title = "Déjà dans vos placards ? ${recipeLine.entries!!.oftenDeleted.size}"
                        )
                    }
                    if (recipeLine.entries?.removed?.isNotEmpty() == true) {
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        ExpendableEntryLine(
                            recipeLine.entries!!.removed,
                            backGroundColor = Color.DarkGray,
                            fontColor = Color.White,
                            title = "Article(s) retiré(s) du panier ${recipeLine.entries!!.removed.size}"
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
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun basketPreviewLine(line: BasketPreviewLine) {

        val price = Price(recipeId = line.id ?: -1)

        Column() {
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(line.picture),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(8.dp)),
                    )
                    Column(
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = line.title,
                            maxLines = 2,
                            color = Color(0xff037E92),
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h5.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            ), modifier = Modifier
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(horizontal = 30.dp)
                        )
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        Text(
                            text = "${line.description?.get(0) ?: ' '}",
                            color= Color(0xff9F9F9F),
                            style = MaterialTheme.typography.h5.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(horizontal = 30.dp)
                        )
                        Text(
                            text = "${line.price}€ /personne",
                            color= Color(0xff9F9F9F),
                            style = MaterialTheme.typography.h5.copy(fontSize = 14.sp),
                            modifier = Modifier
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(horizontal = 30.dp)
                        )
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        Counter(
                            count = line.count,
                            increase = { /*TODO*/ },
                            decrease = { /*TODO*/ },
                            counterModifier = CounterModifier(
                                iconModifier = Modifier.size(30.dp),
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            price.content()
                        }
                    }

                }
            }

        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ExpendableEntryLine(
        entries: List<BasketEntry>,
        title: String,
        backGroundColor: Color,
        fontColor: Color
    ) {

        var expandedState by remember { mutableStateOf(false) }
        val rotationState by animateFloatAsState(
            targetValue = if (expandedState) 180f else 0f
        )

        Column() {
            Card(
                backgroundColor = backGroundColor,
                onClick = { expandedState = !expandedState }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier
                            .weight(6f),
                        text = title,
                        color = fontColor
                    )
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .rotate(rotationState),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            tint = fontColor,
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop-Down Arrow"
                        )
                    }
                }
            }


            if (expandedState) {
                LazyColumn {
                    items(entries) { entrie ->
                        EntriesLine(entrie)
                    }
                }
            }

        }

    }


    @Composable
    fun EntriesLine(entry: BasketEntry) {
        
        Row(Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.size(30.dp).border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xffD9D9D9)
                    ),
                    shape = RoundedCornerShape(100.dp)
                ),
                onClick = {
                    //TODO
                }) {
                Icon(
                    tint = Color.Gray,
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Drop-Down Arrow"
                )
            }
        }
        Text(
            text = entry.id.toString(),
        )
    }
}