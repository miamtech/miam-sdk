package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewContract
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.theme.Typography.bodySmallBold
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewColor.BPPLDescriptionColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.delete
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.swap
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent.MultipleRecipeTag
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.android.ui.components.price.Price
import java.util.*

@ExperimentalCoilApi
@Composable
fun EntryLine(
    entry: BasketPreviewLine,
    vmBasketPreview: BasketPreviewViewModel,
    goToItemSelector: () -> Unit
) {
    val productName =
        entry.title.substring(0, 1).uppercase(Locale.getDefault()) + entry.title.substring(1)
            .lowercase(Locale.getDefault())
    val description = entry.bplDescription[0]
    val sharingCount = entry.inlineTag
    var count by remember { mutableStateOf(entry.count) }
    val rememberedEntry by remember { mutableStateOf(entry) }

    if (rememberedEntry.id != entry.id) {
        // object is reused reset remembered count
        count = entry.count
    }


    fun delete() {
        vmBasketPreview.removeBasketEntry(entry.record as BasketEntry)
    }

    fun replace() {
        vmBasketPreview.setEvent(BasketPreviewContract.Event.OpenItemSelector(entry))
        goToItemSelector()
    }

    fun increaseQty() {
        count++
        vmBasketPreview.updateBasketEntry(entry.record as BasketEntry, count)
    }

    fun decreaseQty() {
        if (count == 1) {
            vmBasketPreview.removeBasketEntry(entry.record as BasketEntry)
        } else {
            count--
            vmBasketPreview.updateBasketEntry(entry.record as BasketEntry, count)
        }
    }

    if (Template.basketPreviewProductLine != null) {
        Template.basketPreviewProductLine?.let {
            it(
                productName,
                description,
                entry.picture,
                count,
                sharingCount.toString(),
                entry.price,
                (entry.record as BasketEntry).relationships!!.items!!.data.size,
                { delete() },
                { replace() },
                { increaseQty() },
                { decreaseQty() })
        }
    } else {

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = rememberImagePainter(entry.picture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Text(
                        text = productName,
                        style = bodyBold
                    )
                    Spacer(modifier = Modifier.padding(vertical = 2.dp))
                    Text(
                        text = description,
                        color = BPPLDescriptionColor,
                        style = bodySmallBold,
                        modifier = Modifier.widthIn(200.dp, 200.dp)
                    )
                    Spacer(modifier = Modifier.padding(vertical = 2.dp))
                    MultipleRecipeTag(sharingCount)
                }
                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = { delete() }
                ) {
                    Image(
                        painter = painterResource(delete),
                        contentDescription = "delete"
                    )
                }
            }

            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                EntryPriceAndActionRow(
                    (entry.record as BasketEntry).relationships!!.items!!.data.size,
                    entry.price.toDouble(),
                    count,
                    ::increaseQty,
                    ::decreaseQty,
                    ::replace
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Divider(Modifier.fillMaxWidth(), color = Color.LightGray)
            }
        }
    }
}


@Composable
fun EntryPriceAndActionRow(
    itemsCount: Int,
    price: Double,
    count: Int,
    increaseQty: () -> Unit,
    decreaseQty: () -> Unit,
    replace: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (itemsCount > 1) {
            Clickable(
                onClick = { replace() },
                children = {
                    Row(Modifier) {
                        Image(
                            painter = painterResource(swap),
                            contentDescription = "swap",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text(
                            text = BasketPreviewText.swap,
                            color = Color(0xff037E92),
                            style = body,
                            modifier = Modifier
                        )
                    }
                }
            )
        } else {
            Surface {}
        }
        Spacer(
            modifier =
            Modifier.weight(1f)
        )
        Box(modifier = Modifier.padding(end = 16.dp)) {
            Price(
                price = price,
                isTotalPrice = true
            )
        }
        Counter(
            count = count,
            increase = { increaseQty() },
            decrease = { decreaseQty() },
            lightMode = true,
            isDisable = false
        )
    }
}

@Preview
@Composable
fun EntryPriceAndActionRowPreview() {
    EntryPriceAndActionRow(2, 14.90, 4, {}, {}, {})
}