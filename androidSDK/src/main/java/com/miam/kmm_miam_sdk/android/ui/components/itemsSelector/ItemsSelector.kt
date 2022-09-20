package com.miam.kmm_miam_sdk.android.ui.components.itemsSelector

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorContract
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.android.theme.Dimension.lPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sSpacerHeight
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.theme.Typography.bodySmallBold
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorColor.previousIconColor
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorColor.swapIconColor
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorImage.previous
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorImage.swap
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.itemColumnContainer
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.itemsBorder
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.itemsImage
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.itemsWidth
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.mainContainer
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.mainContainerAlignment
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.mainContainerArrangement
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.previousButton
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.previousButtonContainer
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.pricePosition
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.selectedItemContainer
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.selectedItemContainerAlignment
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.selectedItemContainerArrangement
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.selectedItemContainerBorder
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.selectedItemImage
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.selectedItemInfosContainer
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.swapIcon
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle.swapIconContainer
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorText.replaceBy
import com.miam.kmm_miam_sdk.android.ui.components.price.Price
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ItemsSelector : KoinComponent {

    private val vmItemSelector: ItemSelectorViewModel by inject()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Content() {

        val state = vmItemSelector.uiState.collectAsState()
        Scaffold(
            topBar = {
                Row(modifier = previousButtonContainer) {
                    IconButton(
                        modifier = previousButton,
                        onClick = {
                            vmItemSelector.setEvent(ItemSelectorContract.Event.ReturnToBasketPreview)
                        }
                    ) {
                        Image(
                            colorFilter = ColorFilter.tint(previousIconColor),
                            painter = painterResource(previous),
                            contentDescription = "Previous"
                        )
                    }
                }
            },
            content =
            {
                Column(
                    modifier = mainContainer,
                    verticalArrangement = mainContainerArrangement,
                    horizontalAlignment = mainContainerAlignment,
                ) {
                    if (Template.currentProductTemplate != null) {
                        Template.currentProductTemplate?.let {
                            it(state.value.selectedItem!!)
                        }
                    } else {
                        Surface(modifier = selectedItemContainerBorder) {
                            Row(
                                modifier = selectedItemContainer,
                                horizontalArrangement = selectedItemContainerArrangement,
                                verticalAlignment = selectedItemContainerAlignment
                            ) {
                                Image(
                                    painter = rememberImagePainter(state.value.selectedItem?.picture),
                                    contentDescription = "product image",
                                    contentScale = ContentScale.Crop,
                                    modifier = selectedItemImage,
                                )
                                Column(
                                    modifier = selectedItemInfosContainer,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = state.value.selectedItem?.bplDescription?.get(0)
                                            ?: " ",
                                        textAlign = TextAlign.Center,
                                        style = bodySmallBold
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = pricePosition
                                    ) {
                                        Price(
                                            price = state.value.selectedItem?.price?.toDouble()
                                                ?: 0.0,
                                            isTotalPrice = true
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = sSpacerHeight))

                    if (Template.productOptionListTemplate != null) {
                        Template.productOptionListTemplate?.let {
                            it(
                                state.value.itemList ?: emptyList()
                            ) { index ->
                                vmItemSelector.choose(index)
                                vmItemSelector.setEvent(ItemSelectorContract.Event.ReturnToBasketPreview)
                            }
                        }
                    } else {
                        Text(
                            text = replaceBy,
                            textAlign = TextAlign.Start,
                            style = bodyBold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(vertical = sSpacerHeight))


                        LazyVerticalGrid(
                            cells = GridCells.Adaptive(itemsWidth.dp),
                            contentPadding = PaddingValues(
                                start = mPadding,
                                top = lPadding,
                                end = mPadding,
                                bottom = 0.dp
                            ),
                            content = {
                                val itemsList = state.value.itemList ?: emptyList()
                                items(itemsList.size) { index ->
                                    Clickable(
                                        onClick = {
                                            vmItemSelector.choose(index)
                                            vmItemSelector.setEvent(ItemSelectorContract.Event.ReturnToBasketPreview)
                                        },
                                        children = {
                                            Surface(modifier = itemsBorder) {
                                                Column(
                                                    modifier = itemColumnContainer,
                                                    verticalArrangement = Arrangement.Center,
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Box(modifier = swapIconContainer) {
                                                        Image(
                                                            painter = rememberImagePainter(swap),
                                                            modifier = swapIcon.align(Alignment.Center),
                                                            colorFilter = ColorFilter.tint(
                                                                swapIconColor
                                                            ),
                                                            contentDescription = "swap"
                                                        )
                                                    }
                                                    Image(
                                                        painter = rememberImagePainter(itemsList[index].picture),
                                                        contentDescription = "product image",
                                                        contentScale = ContentScale.Crop,
                                                        modifier = itemsImage,
                                                    )
                                                    Text(
                                                        text = itemsList[index].bplDescription[0],
                                                        textAlign = TextAlign.Center,
                                                        style = bodySmallBold
                                                    )
                                                    Price(
                                                        price = itemsList[index].price.toDouble(),
                                                        isTotalPrice = true
                                                    )
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
}
  

