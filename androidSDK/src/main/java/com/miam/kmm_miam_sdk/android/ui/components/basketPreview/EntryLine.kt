package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.swap
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent.MultipleRecipeTag
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.android.ui.components.price.SimplePrice
import java.util.*

@ExperimentalCoilApi
@Composable
fun EntryLine(
    entry: BasketPreviewLine,
    vmBasketPreview: BasketPreviewViewModel,
    goToItemSelector: () -> Unit,
    updatingBasketEntryId: String?
) {
    val productName = entry.title.substring(0, 1).uppercase(Locale.getDefault()) + entry.title.substring(1).lowercase(Locale.getDefault())
    val description = entry.bplDescription[0]
    val sharingCount = entry.inlineTag

    fun delete() {
        vmBasketPreview.removeBasketEntry(entry.record as BasketEntry)
    }

    fun replace() {
        vmBasketPreview.setEvent(BasketPreviewContract.Event.OpenItemSelector(entry))
        goToItemSelector()
    }

    fun onQuantityChanged(newQuantity: Int) {
        if (newQuantity == 0) {
            vmBasketPreview.removeBasketEntry(entry.record as BasketEntry)
            return
        }
        vmBasketPreview.updateBasketEntry(entry.record as BasketEntry, newQuantity)
    }

    if (Template.basketPreviewProductLine != null) {
        Template.basketPreviewProductLine?.let {
            it(
                productName,
                description,
                entry.picture,
                entry.count,
                sharingCount.toString(),
                entry.price,
                (entry.record as BasketEntry).itemsCountOrZero,
                updatingBasketEntryId,
                { delete() },
                { replace() },
                { newQuantity -> onQuantityChanged(newQuantity) }

            )
        }
    } else {
        Row(Modifier.padding(horizontal = 16.dp)) {
            BasketPreviewProductLine(
                entry.id,
                productName,
                description,
                entry.picture,
                entry.count,
                sharingCount.toString(),
                entry.price,
                (entry.record as BasketEntry).itemsCountOrZero,
                updatingBasketEntryId,
                { delete() },
                { replace() }
            ) { newQuantity -> onQuantityChanged(newQuantity) }
        }
    }
}

@Composable
fun BasketPreviewProductLine(
    entryId: String?,
    productName: String,
    description: String,
    picture: String,
    quantity: Int,
    sharingCount: String,
    price: String,
    itemsCount: Int,
    updatingBasketEntryId: String?,
    delete: () -> Unit,
    replace: () -> Unit,
    onQuantityChanged: (Int) -> Unit
) {
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberImagePainter(picture),
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
                    painter = painterResource(BasketPreviewImage.delete),
                    contentDescription = "delete"
                )
            }
        }

        Column(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            EntryPriceAndActionRow(
                entryId,
                itemsCount,
                price.toDouble(),
                quantity,
                updatingBasketEntryId,
                { onQuantityChanged(it) },
                { replace() },
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Divider(Modifier.fillMaxWidth(), color = Color.LightGray)
        }
    }
}


@Composable
fun EntryPriceAndActionRow(
    entryId: String?,
    itemsCount: Int,
    price: Double,
    currentEntryCount: Int,
    updatingBasketEntryId: String?,
    onCounterChanged: (counterValue: Int) -> Unit,
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
            SimplePrice(price = price)
        }
        Counter(
            initialCount = currentEntryCount,
            onCounterChanged = onCounterChanged,
            lightMode = true,
            isDisable = updatingBasketEntryId != null,
            isLoading = updatingBasketEntryId == entryId,
            minValue = 0,
            maxValue = 99,
            key = entryId
        )
    }
}

@Preview
@Composable
fun EntryPriceAndActionRowPreview() {
    EntryPriceAndActionRow("test", 2, 14.90, 4, null, {}, {})
}