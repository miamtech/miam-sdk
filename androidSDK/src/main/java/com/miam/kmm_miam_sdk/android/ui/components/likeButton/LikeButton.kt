package com.miam.kmm_miam_sdk.android.ui.components.likeButton


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.miam.kmm_miam_sdk.android.theme.Dimension
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonColor.buttonBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonColor.iconColor
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonStyle.iconContainerModifer
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonStyle.iconModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel



@Composable
fun LikeButton(
    vmRecipe : RecipeViewModel
) {
    Clickable(
        onClick = { vmRecipe.setEvent(
            RecipeContract.Event.OnToggleLike
        ) },
        children = {
            Box(modifier = iconContainerModifer.background(buttonBackgroundColor)){
                Image(
                    painter = painterResource(LikeButtonImage.favorite),
                    contentDescription = "favorite",
                    modifier = iconModifier,
                    colorFilter = ColorFilter.tint(color = iconColor)
                )
                if( vmRecipe.currentState.isLiked){
                    Image(
                        painter = painterResource(LikeButtonImage.favoriteFilled),
                        contentDescription = "favorite",
                        modifier = iconModifier,
                        colorFilter = ColorFilter.tint(color = iconColor)
                    )
                }
            }
        }
    )
}