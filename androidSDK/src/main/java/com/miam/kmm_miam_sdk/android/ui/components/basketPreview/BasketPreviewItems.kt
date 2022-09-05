package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewContract
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Colors

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
                        .padding(horizontal = 16.dp)
                ) {
                    line.entries!!.found.map { entry -> BasketPreviewLine.fromBasketEntry(entry) }
                        .forEach { bpl ->
                            EntryLine(bpl, vmBasketPreview, goToItemSelector)
                        }
                }
            }
            if (line.entries?.notFound?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                expendableEntryLine(
                    line.entries!!.notFound,
                    backGroundColor = Color(0xffEDEDED),
                    fontColor = Color(0xff252525),
                    title = "Article(s) indisponible(s) (${line.entries!!.notFound.size})"
                )
            }
            if (line.entries?.oftenDeleted?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                expendableEntryLine(
                    line.entries!!.oftenDeleted,
                    backGroundColor = Color(0xffD9EFF2),
                    fontColor = Colors.primary,
                    title = "Déjà dans vos placards ? (${line.entries!!.oftenDeleted.size})",
                    click = fun(entry: BasketEntry) {
                        vmBasketPreview.setEvent(
                            BasketPreviewContract.Event.AddEntry(entry)
                        )
                    }
                )
            }
            if (line.entries?.removed?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                expendableEntryLine(
                    line.entries!!.removed,
                    backGroundColor = Color(0xffBBBBBB),
                    fontColor = Color(0xff252525),
                    title = "Article(s) retiré(s) du panier (${line.entries!!.removed.size})",
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