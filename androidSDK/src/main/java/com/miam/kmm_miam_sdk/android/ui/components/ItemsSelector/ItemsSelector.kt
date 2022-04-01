package com.miam.kmm_miam_sdk.android.ui.components.ItemsSelector

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft

import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView
import com.miam.kmm_miam_sdk.android.ui.components.price.Price

import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorContract

import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ItemsSelector () :KoinComponent {

    private val vmItemSelector: ItemSelectorViewModel by inject()

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content () {

    val state  = vmItemSelector.uiState.collectAsState()

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 32.dp),
    verticalArrangement =  Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = {
                    vmItemSelector.setEvent(ItemSelectorContract.Event.ReturnToBasketPreview)
                }) {
                Icon(
                    tint = Color(0xff037E92),
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "ChevronLeft"
                )
            }
        }
        Surface(
            border = BorderStroke(1.dp, MiamMasterView.Primary),
            shape = RoundedCornerShape(25),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row( modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
                 horizontalArrangement = Arrangement.SpaceEvenly,
                 verticalAlignment = Alignment.CenterVertically
                ) {
                Image(
                    painter = rememberImagePainter(state.value.selectedItem?.picture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = state.value.selectedItem?.description?.get(0) ?: " ",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        color = MiamMasterView.textColor)
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Price(price= state.value.selectedItem?.price?.toDouble() ?: 0.0,
                            isTotalPrice = true).content()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        
        Text(text = "Remplacer cet article par : ",
             Modifier.fillMaxWidth() ,
             textAlign = TextAlign.Start,
             fontSize = 16.sp,
             color = MiamMasterView.textColor,
             fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

       LazyVerticalGrid (
             cells = GridCells.Adaptive(140.dp),
             contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 0.dp
            ),
            content = {

                val itemsList = state.value.itemList ?: emptyList()

                items(itemsList.size) {
                    index ->
                    Clickable(
                        onClick = { vmItemSelector.choose(index) },
                        children = {
                            Surface(
                                border = BorderStroke(1.dp, MiamMasterView.lightGray),
                                shape = RoundedCornerShape(15),
                                modifier= Modifier.padding(8.dp)
                            ) {
                                Column( verticalArrangement =  Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier =  Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        tint = MiamMasterView.Secondary,
                                        imageVector = Icons.Default.SwapHorizontalCircle,
                                        contentDescription = "swap_horizontal_circle"
                                    )

                                    Image(
                                        painter = rememberImagePainter(itemsList[index].picture),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .height(72.dp)
                                            .width(72.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                    )
                                    Text(text = "${itemsList[index].description?.get(0) ?: ' '}" ,                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center,
                                        color = MiamMasterView.textColor)
                                    Price(price = itemsList[index].price.toDouble(),
                                        isTotalPrice = true).content()
                                }
                            }
                        }
                    )
                }
            }
        )

    }
}

}
  

