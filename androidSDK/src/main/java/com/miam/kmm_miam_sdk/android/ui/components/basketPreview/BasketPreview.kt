package com.miam.kmm_miam_sdk.android.ui.components.basketPreview


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewContract
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Dimension
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewColor.continueButtonTextColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewColor.removeButtonTextColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage.toggleCaret
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.footerContinueButton
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.footerRemoveButton
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.headerPreviousButton
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.headerRowModifier
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.headerRowVerticalAlignment
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText.addedRecipe
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText.continueShopping
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText.removeRecipe
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState


class BasketPreview(
    private val recipeId: String,
    private val recipeVm: RecipeViewModel,
    val goToDetail: () -> Unit,
    val close: () -> Unit,
    val goToItemSelector: () -> Unit,
) {

    private val vmBasketPreview: BasketPreviewViewModel = BasketPreviewViewModel(recipeId)

    @ExperimentalCoilApi
    @Composable
    fun content() {

        val state by vmBasketPreview.uiState.collectAsState()


        fun removeRecipeAndClose() {
            vmBasketPreview.setEvent(BasketPreviewContract.Event.RemoveRecipe(state.bpl?.id ?: ""))
            close()
        }
        Scaffold(
            topBar = {

                if (Template.basketPreviewHeaderTemplate != null) {
                    Template.basketPreviewHeaderTemplate?.let {
                        it(recipeVm) { goToDetail() }
                    }
                } else {
                    Row(
                        modifier = headerRowModifier,
                        verticalAlignment = headerRowVerticalAlignment
                    )
                    {
                        IconButton(
                            modifier = headerPreviousButton,
                            onClick = { goToDetail() }
                        ) {
                            Image(
                                painter = painterResource(toggleCaret),
                                contentDescription = "Previous",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(end = Dimension.mPadding)
                                    .rotate(180f)
                            )
                        }
                        Text(
                            text = addedRecipe,
                            style = Typography.bodyBold
                        )
                    }
                }
            },
            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ManagementResourceState(
                        resourceState = state.line,
                        successView = { line ->
                            requireNotNull(line)
                            BasketPreviewSucessView(
                                line,
                                recipeVm,
                                { goToDetail() },
                                { goToItemSelector() },
                                vmBasketPreview
                            )
                        },
                        onTryAgain = { /*TODO*/ },
                        onCheckAgain = { /*TODO*/ },
                        loadingView = { BasketPreviewLoader() }
                    )
                }
            },
            bottomBar = {
                BottomAppBar(backgroundColor = RecipeDetailsColor.footerSectionBackgroundColor) {
                    if (Template.basketPreviewLineFooterTemplate != null) {
                        Template.basketPreviewLineFooterTemplate!!(
                            {
                                removeRecipeAndClose()
                            },
                            { close() }
                        )
                    } else {
                        Row(
                            modifier = RecipeDetailsStyle.footerMainContainer,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Row(
                                footerRemoveButton
                                    .weight(1F)
                                    .clickable {
                                        removeRecipeAndClose()
                                    },
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = removeRecipe,
                                    style = Typography.button,
                                    color = removeButtonTextColor
                                )
                            }
                            Row(
                                modifier = footerContinueButton
                                    .weight(1f)
                                    .clickable { close() },
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = continueShopping,
                                    style = Typography.button,
                                    color = continueButtonTextColor

                                )
                            }
                        }
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BasketPreviewSucessView(
    line: BasketPreviewLine,
    recipeVm: RecipeViewModel,
    goToDetail: () -> Unit,
    goToItemSelector: () -> Unit,
    vmBasketPreview: BasketPreviewViewModel,
) {
    Column {
        BasketPreviewRecipeLine(
            line = line,
            { guestCount -> vmBasketPreview.updateGuest(recipeVm, guestCount) },
            goToDetail
        )
        BasketPreviewItem(
            line = line,
            vmBasketPreview = vmBasketPreview,
            goToItemSelector = { goToItemSelector() }
        )
        Spacer(modifier = Modifier.padding(vertical = 32.dp))
    }
}



