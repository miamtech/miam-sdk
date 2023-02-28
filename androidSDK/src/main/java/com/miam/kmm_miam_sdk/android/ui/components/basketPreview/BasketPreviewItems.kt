package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miam.core.sdk.localisation.Localisation
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewContract
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Template

@Composable
fun BasketPreviewItem(
    line: BasketPreviewLine,
    vmBasketPreview: BasketPreviewViewModel,
    goToItemSelector: () -> Unit
) {
    if (vmBasketPreview.currentState.isReloading) {
        BasketPreviewLoader()
    } else {
        Column {
            if (line.entries?.found?.isNotEmpty() == true) {
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    line.entries!!.found.map { entry -> BasketPreviewLine.fromBasketEntry(entry) }
                        .forEach { bpl ->
                            EntryLine(bpl, vmBasketPreview, goToItemSelector, vmBasketPreview.currentState.updatingBasketEntryId)
                        }
                }
            }
            if (line.entries?.notFound?.isNotEmpty() == true) {
                if (Template.basketPreviewNotFoundTemplate != null) {
                    Template.basketPreviewNotFoundTemplate?.let { it(line.entries?.notFound ?: emptyList()) }
                } else {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    ExpendableEntryLine(
                        line.entries!!.notFound,
                        backGroundColor = Color(0xffEDEDED),
                        fontColor = Color(0xff252525),
                        title = Localisation.Basket.unavailableProducts(line.entries!!.notFound.size).localised
                    )
                }
            }
            if (line.entries?.oftenDeleted?.isNotEmpty() == true) {
                if (Template.basketPreviewOftenDeletedTemplate != null) {
                    Template.basketPreviewOftenDeletedTemplate?.let {
                        it(
                            line.entries?.oftenDeleted ?: emptyList(),
                            fun(entry: BasketEntry) {
                                vmBasketPreview.setEvent(
                                    BasketPreviewContract.Event.AddEntry(entry)
                                )
                            }
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    ExpendableEntryLine(
                        line.entries!!.oftenDeleted,
                        backGroundColor = Color(0xffD9EFF2),
                        fontColor = Colors.primary,
                        title = Localisation.Basket.ownedProducts(line.entries!!.oftenDeleted.size).localised,
                        click = fun(entry: BasketEntry) {
                            vmBasketPreview.setEvent(
                                BasketPreviewContract.Event.AddEntry(entry)
                            )
                        }
                    )
                }

            }
            if (line.entries?.removed?.isNotEmpty() == true) {
                if (Template.basketPreviewRemovedTemplate != null) {
                    Template.basketPreviewRemovedTemplate?.let {
                        it(line.entries?.removed ?: emptyList(),
                            fun(entry: BasketEntry) {
                                vmBasketPreview.setEvent(
                                    BasketPreviewContract.Event.AddEntry(entry)
                                )
                            }
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    ExpendableEntryLine(
                        line.entries!!.removed,
                        backGroundColor = Color(0xffBBBBBB),
                        fontColor = Color(0xff252525),
                        title = Localisation.Basket.removedProducts(line.entries!!.removed.size).localised,
                        click = fun(entry: BasketEntry) {
                            vmBasketPreview.setEvent(
                                BasketPreviewContract.Event.AddEntry(entry)
                            )
                        }
                    )
                }
            }
        }
    }
}