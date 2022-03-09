package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel

import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorContract
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry

import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import java.util.*

@ExperimentalCoilApi
@Composable
fun entryLine(entry: BasketPreviewLine,
              vmBasketPreview : BasketPreviewViewModel) {
    val price = Price(price = entry.price.toDouble(), isTotalPrice = true)
    var count by remember { mutableStateOf(entry.count) }

    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(30.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xffD9D9D9)
                    ),
                    shape = CircleShape
                ),
            onClick = {
                vmBasketPreview.setEvent(BasketPreviewContract.Event.RemoveEntry(entry.record as BasketEntry))
            }) {
            Icon(
                tint = Color.Gray,
                imageVector = Icons.Default.Delete,
                contentDescription = "delete"
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Image(
            painter = rememberImagePainter(entry.picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(72.dp)
                .width(72.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

        Column() {
            Text(
                text =  entry.title.substring(0, 1).uppercase(Locale.getDefault()) + entry.title.substring(1)
                    .lowercase(Locale.getDefault()),
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            entry.description?.get(0)?.let {
                Text(
                    text = it,
                    color= MiamMasterView.Grey02,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 14.sp,
                    ),
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Clickable(
                onClick = {
                    vmBasketPreview.setEvent(BasketPreviewContract.Event.OpenItemSelector(entry))
                },
                children = {
                    Text(
                        text ="Changer d'article",
                        color = Color(0xff037E92),
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h5.copy(
                            fontSize = 14.sp,
                            textDecoration = TextDecoration.Underline
                        ),

                        )
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            if(entry.inlineTag != null ){
                Surface(
                    border = BorderStroke(1.dp, Color(0xff676767)),
                    contentColor = Color(0xff676767),
                    shape = CircleShape
                ) {
                    Text(modifier = Modifier.padding(8.dp),text = entry.inlineTag!!)
                }
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween) {
                price.content()

                Counter(
                    count = count,
                    increase = {
                        count++
                        vmBasketPreview.setEvent(BasketPreviewContract.Event.UpdateBasketEntry(entry.record as BasketEntry, 1))},
                    decrease = {
                        if(count == 1 ){
                            vmBasketPreview.setEvent(BasketPreviewContract.Event.RemoveEntry(entry.record as BasketEntry))
                        }else{
                            count --
                            vmBasketPreview.setEvent(BasketPreviewContract.Event.UpdateBasketEntry(entry.record as BasketEntry, -1))
                        }

                    },
                    counterModifier = CounterModifier(
                        iconModifier = Modifier.width(0.dp)),
                    isDisable = false
                )
            }
        }
    }
}