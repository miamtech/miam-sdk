package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.android.ui.components.price.Price
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlin.math.round


@ExperimentalCoilApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun basketPreviewLine(line: BasketPreviewLine, vmRecipe: RecipeViewModel, vmBasketPreview: BasketPreviewViewModel, router:RouterViewModel)  {

    val price = Price(price = line.price.toDouble(), isTotalPrice = true)

    var count by remember { mutableStateOf(line.count) }


    Column() {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
        ) {
            Clickable(
                onClick = { router.setEvent(RouterContract.Event.GoToDetailFromPreview(vmRecipe)) },
                children = {  Image(
                    painter = rememberImagePainter(line.picture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(0.dp, 20.dp, 0.dp, 0.dp)),
            )
                }
                    )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Clickable(
                    onClick = { router.setEvent(RouterContract.Event.GoToDetailFromPreview(vmRecipe)) },
                children = { Text(
                    text = line.title,
                    maxLines = 2,
                    color = primary,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    ),
                )}
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = "${line.description?.get(0) ?: ' '}",
                    color= Color.Gray,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = "${  (round(((line.price.toDouble() * 100).toBigDecimal() / line.count.toBigDecimal()).toDouble()) / 100) }€ /personne",
                    color= Color.Gray,
                    style = MaterialTheme.typography.h5.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Counter(
                    count = count,
                    increase = { if(line.count != 100) {
                        count++
                        vmBasketPreview.setEvent(BasketPreviewContract.Event.CountChange(
                            line.copy(count = count), recipeVm = vmRecipe )
                        )
                    }},
                    decrease = { if(line.count != 0) {
                        count--
                        vmBasketPreview.setEvent(BasketPreviewContract.Event.CountChange(
                            line.copy(count = count), recipeVm = vmRecipe )
                        ) }},
                    lightMode = false,
                    isDisable = vmBasketPreview.currentState.isReloading
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