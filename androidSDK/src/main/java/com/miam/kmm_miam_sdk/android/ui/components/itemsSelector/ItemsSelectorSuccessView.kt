package com.miam.kmm_miam_sdk.android.ui.components.itemsSelector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Dimension
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorColor
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorImage
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorText
import com.miam.kmm_miam_sdk.android.ui.components.price.SimplePrice


@Composable
fun ItemsSelectorSuccessView(

    itemSelected: BasketPreviewLine,
    items: List<BasketPreviewLine>,
    previous: () -> Unit,
    choose: (item: BasketPreviewLine, index: Int) -> Unit
) {
    Scaffold(
        topBar = {
            if (Template.productSelectorHeaderTemplate != null) {
                Template.productSelectorHeaderTemplate?.let { it { previous() } }
            } else {
                Row(modifier = ItemsSelectorStyle.previousButtonContainer) {
                    IconButton(
                        modifier = ItemsSelectorStyle.previousButton,
                        onClick = previous
                    ) {
                        Image(
                            colorFilter = ColorFilter.tint(ItemsSelectorColor.previousIconColor),
                            painter = painterResource(ItemsSelectorImage.previous),
                            contentDescription = "Previous"
                        )
                    }
                }
            }
        },
        content =
        { padding ->
            Column(
                modifier = ItemsSelectorStyle.mainContainer.padding(padding),
                verticalArrangement = ItemsSelectorStyle.mainContainerArrangement,
                horizontalAlignment = ItemsSelectorStyle.mainContainerAlignment,
            ) {
                if (Template.currentProductTemplate != null) {
                    Template.currentProductTemplate?.let {
                        it(itemSelected)
                    }
                } else {
                    Column {
                        Surface(modifier = ItemsSelectorStyle.selectedItemContainerBorder) {
                            Row(
                                modifier = ItemsSelectorStyle.selectedItemContainer,
                                horizontalArrangement = ItemsSelectorStyle.selectedItemContainerArrangement,
                                verticalAlignment = ItemsSelectorStyle.selectedItemContainerAlignment
                            ) {
                                Image(
                                    painter = rememberImagePainter(itemSelected.picture),
                                    contentDescription = "product image",
                                    contentScale = ContentScale.Crop,
                                    modifier = ItemsSelectorStyle.selectedItemImage,
                                )
                                Column(
                                    modifier = ItemsSelectorStyle.selectedItemInfosContainer,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = itemSelected.bplDescription.get(0),
                                        textAlign = TextAlign.Center,
                                        style = Typography.bodySmallBold
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = ItemsSelectorStyle.pricePosition
                                    ) {
                                        SimplePrice(price = itemSelected.price.toDouble())
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = Dimension.sSpacerHeight))
                    }
                }


                if (Template.productOptionListTemplate != null) {
                    Template.productOptionListTemplate?.let {
                        it(
                            items
                        ) { index ->
                            choose(itemSelected, index)
                            previous()
                        }
                    }
                } else {
                    Text(
                        text = ItemsSelectorText.replaceBy,
                        textAlign = TextAlign.Start,
                        style = Typography.bodyBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(vertical = Dimension.sSpacerHeight))


                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = ItemsSelectorStyle.itemsWidth.dp),
                        contentPadding = PaddingValues(
                            start = Dimension.mPadding,
                            top = Dimension.lPadding,
                            end = Dimension.mPadding,
                            bottom = 0.dp
                        ),
                        content = {

                            items(items.size) { index ->
                                Clickable(
                                    onClick = {
                                        choose(itemSelected, index)
                                        previous()
                                    },
                                    children = {
                                        Surface(modifier = ItemsSelectorStyle.itemsBorder) {
                                            Column(
                                                modifier = ItemsSelectorStyle.itemColumnContainer,
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Box(modifier = ItemsSelectorStyle.swapIconContainer) {
                                                    Image(
                                                        painter = rememberImagePainter(ItemsSelectorImage.swap),
                                                        modifier = ItemsSelectorStyle.swapIcon.align(Alignment.Center),
                                                        colorFilter = ColorFilter.tint(
                                                            ItemsSelectorColor.swapIconColor
                                                        ),
                                                        contentDescription = "swap"
                                                    )
                                                }
                                                Image(
                                                    painter = rememberImagePainter(items[index].picture),
                                                    contentDescription = "product image",
                                                    contentScale = ContentScale.Crop,
                                                    modifier = ItemsSelectorStyle.itemsImage,
                                                )
                                                Text(
                                                    text = items[index].bplDescription[0],
                                                    textAlign = TextAlign.Center,
                                                    style = Typography.bodySmallBold
                                                )
                                                SimplePrice(price = items[index].price.toDouble())
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
    )
}

