package com.miam.kmm_miam_sdk.android.ui.components.myMeal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewItem
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewRecipeLine
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle

@Composable
fun ExpendableBasketPreviewLine(
    line: BasketPreviewLine,
    vmBasketPreview: BasketPreviewViewModel,
    goToDetail: () -> Unit,
    goToReplaceItem: () -> Unit,
    removeRecipe: () -> Unit,
    updateGuest: (guestInt: Int) -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 270f else 90f
    )

    Column {
        Clickable(
            onClick = { expandedState = !expandedState },
            children = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    Column(Modifier.weight(1f)) {
                        BasketPreviewRecipeLine(
                            line = line,
                            { guestCount -> vmBasketPreview.updateGuest(updateGuest, guestCount) },
                            goToDetail,
                            false
                        )
                    }
                    if (Template.myMealRecipeExpendableAction != null) {
                        Template.myMealRecipeExpendableAction?.let {
                            it(expandedState, { expandedState = !expandedState }) {
                                removeRecipe()
                            }
                        }
                    } else {
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .background(Colors.ternary.copy(alpha = 0.1f))
                                .padding(8.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                modifier = Modifier.size(30.dp),
                                onClick = { removeRecipe() }
                            ) {
                                Image(
                                    painter = painterResource(BasketPreviewImage.delete),
                                    contentDescription = "delete"
                                )
                            }
                            IconButton(
                                modifier = Modifier
                                    .size(30.dp)
                                    .rotate(rotationState),
                                onClick = {
                                    expandedState = !expandedState
                                }
                            ) {
                                Image(
                                    painter = painterResource(BasketPreviewImage.toggleCaret),
                                    contentDescription = null,
                                    modifier = RecipeDetailsStyle.totalTimeIcon
                                )
                            }
                        }
                    }
                }
            }
        )

        AnimatedVisibility(visible = expandedState) {
            BasketPreviewItem(
                line = line,
                vmBasketPreview = vmBasketPreview,
                goToItemSelector = { goToReplaceItem() }
            )
        }
    }
}