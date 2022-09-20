package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.price.Price
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText


@Composable
fun RecipeDetailFooter(
    recipeId: String,
    guestNumber: Int,
    isInCart: Boolean,
    seeProductMatching: () -> Unit,
    buy: () -> Unit
) {

    val recipeIdState = remember { recipeId }


    Row(
        modifier = RecipeDetailsStyle.footerMainContainer,
        horizontalArrangement = Arrangement.End,
    ) {
        Row(
            Modifier.weight(1F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Price(
                recipeId = recipeIdState,
                guestNumber
            )
        }
        if (isInCart) {

            Row(
                modifier = RecipeDetailsStyle.checkProductButton
                    .weight(2f)
                    .clickable { seeProductMatching() },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = RecipeDetailsText.checkBasketPreview,
                    style = Typography.button,
                    color = RecipeDetailsColor.goToPreviewTextColor

                )
            }
        } else {
            Row(
                modifier = RecipeDetailsStyle.buyRecipeButton
                    .weight(2f)
                    .clickable { buy() },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = RecipeDetailsText.addRecipe,
                    style = Typography.button,
                    color = RecipeDetailsColor.buyButtonTextColor
                )
                Image(
                    painter = painterResource(Image.cart),
                    contentDescription = null,
                    modifier = RecipeDetailsStyle.buyRecipeButtonIcon
                )
            }
        }
    }
}
