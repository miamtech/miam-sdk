package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.black
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.theme.Typography.link
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewText.moreDetail
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
fun BasketPreviewRecipeLine(
    line: BasketPreviewLine,
    guestChange: (count:Int) -> Unit ,
    goToDetail: () -> Unit
)  {

    val price = Price(price = line.price.toDouble(), isTotalPrice = true)
    val recipeName = line.title
    val recipeDescription = line.description[0]
    val pricePerGuest = "${ (round(((line.price.toDouble() * 100).toBigDecimal() / line.count.toBigDecimal()).toDouble()) / 100) }€ /personne"
    var count by remember { mutableStateOf(line.count) }

    fun goToRecipeDetail(){
        goToDetail()
    }

    fun increase() {
        if(line.count != 100) {
            count++
            guestChange(count)
        }
    }

    fun decrease(){
        if(line.count != 0) {
            count--
            guestChange(count)
            }
    }


    if(Template.basketPreviewRecipeLineTemplate != null){
        Template.basketPreviewRecipeLineTemplate?.let {
            it(
                recipeName,
                recipeDescription,
                pricePerGuest,
                count,
                { goToRecipeDetail() },
                { increase() },
                { decrease() }
            )
        }
    } else {

        Column(modifier = Modifier.background(Colors.ternary.copy(alpha = 0.1f))) {
            Divider(Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Clickable(
                    onClick = { goToRecipeDetail() },
                    children = {  Image(
                        painter = rememberImagePainter(line.picture),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    )
                    }
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Clickable(
                        onClick = { goToRecipeDetail() },
                        children = {
                            Text(
                                text = recipeName,
                                maxLines = 2,
                                color = black,
                                overflow = TextOverflow.Ellipsis,
                                style = bodyBold,
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = recipeDescription,
                        color= Colors.grey,
                        style = body
                    )
                    Text(
                        text = pricePerGuest,
                        color= Colors.grey,
                        style = body
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Clickable(
                        onClick = { goToRecipeDetail() },
                        children =  {
                            Text(
                                text = moreDetail,
                                color= Colors.primary,
                                style = link
                            )
                        }
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                price.content()
                Counter(
                    count = count,
                    increase = { increase() },
                    decrease = { decrease() },
                    lightMode = false,
                    isDisable = false
                )
            }
            Divider(Modifier.fillMaxWidth())
        }
        
    }
}