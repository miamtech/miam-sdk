package com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle

@Composable
fun BasketPreviewFooter(removeRecipeAndClose: () -> Unit, close: () -> Unit) {

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
                BasketPreviewStyle.footerRemoveButton
                    .weight(1F)
                    .clickable {
                        removeRecipeAndClose()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = BasketPreviewText.removeRecipe,
                    style = Typography.button,
                    color = BasketPreviewColor.removeButtonTextColor
                )
            }
            Row(
                modifier = BasketPreviewStyle.footerContinueButton
                    .weight(1f)
                    .clickable { close() },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = BasketPreviewText.continueShopping,
                    style = Typography.button,
                    color = BasketPreviewColor.continueButtonTextColor

                )
            }
        }
    }
}

@Preview
@Composable
fun BasketPreviewFooterPreview() {
    BasketPreviewFooter({}) {}
}

