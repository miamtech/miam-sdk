package com.miam.kmm_miam_sdk.android.ui.components.myMeal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewContent
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewImage
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreviewRecipeLine
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewContract
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpendableBasketPreviewLine(
    line : BasketPreviewLine,
    recipeVm: RecipeViewModel,
    vmBasketPreview: BasketPreviewViewModel,
    goToDetail: () -> Unit,
    goToReplaceItem: ()-> Unit,
    removeRecipe : () -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 270f else 90f
    )

    Column() {
        Clickable(
            onClick = { expandedState = !expandedState},
            children = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                      ) {
                    Column( Modifier.weight(1f)) {
                        BasketPreviewRecipeLine(
                            line = line,
                            { count ->
                                vmBasketPreview.setEvent(
                                    BasketPreviewContract.Event.CountChange(
                                        line.copy(count = count), recipeVm = recipeVm )
                                )
                            },
                            goToDetail
                        )
                    }
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
                                    modifier =  RecipeDetailsStyle.totalTimeIcon
                                )
                            }
                    }
                }

            }
        )

        AnimatedVisibility(visible = expandedState ) {
            BasketPreviewContent(
                line = line,
                vmBasketPreview= vmBasketPreview,
                goToItemSelector = { goToReplaceItem() }
            )
        }
    }
}