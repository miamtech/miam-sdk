package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.add
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.toggleCaret
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun expendableEntryLine(
    entries: List<BasketEntry>,
    title: String,
    backGroundColor: Color,
    fontColor: Color,
    click: ((bpl: BasketEntry) -> Unit)? = null
) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 90f else 0f
    )

    fun toggle() {
        expandedState = !expandedState
    }

    Column {
        if (Template.basketPreviewExpendHeaderTemplate != null) {
            Template.basketPreviewExpendHeaderTemplate?.let {
                it(expandedState, title) { toggle() }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { toggle() }
            ) {
                Column {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(6f),
                            text = title,
                            fontWeight = if (expandedState) FontWeight.Bold else FontWeight.Normal
                        )
                        IconButton(
                            modifier = Modifier
                                .weight(1f)
                                .rotate(rotationState),
                            onClick = {
                                toggle()
                            }
                        ) {
                            Image(
                                painter = painterResource(toggleCaret),
                                contentDescription = null,
                                modifier = RecipeDetailsStyle.totalTimeIcon
                            )
                        }
                    }
                    if (!expandedState) {
                        Divider()
                    }
                }
            }
        }

        AnimatedVisibility(visible = expandedState) {
            Column {
                entries.forEach { entry ->
                    val productName =
                        entry.relationships?.groceriesEntry?.data?.attributes?.name ?: ""

                    if (Template.basketPreviewExpendRowTemplate != null) {
                        val action = if (click == null) null else ({ click(entry) })
                        Template.basketPreviewExpendRowTemplate?.let { it(action, productName) }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 32.dp)
                                .fillMaxWidth()

                        ) {
                            Text(
                                text = productName,
                                style = body
                            )
                            if (click != null) {
                                Clickable(
                                    onClick = { click(entry) },
                                    children = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Image(
                                                painter = painterResource(add),
                                                contentDescription = null,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.padding(8.dp))
                                            Text(
                                                text = "Ajouter",
                                                style = bodyBold,
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
