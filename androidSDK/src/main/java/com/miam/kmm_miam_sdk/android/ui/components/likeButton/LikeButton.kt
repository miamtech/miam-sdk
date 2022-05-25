package com.miam.kmm_miam_sdk.android.ui.components.likeButton


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.Surface

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonColor.buttonBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonColor.iconColor

import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonStyle.iconModifier

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
            Surface(
                shape = CircleShape,
                elevation = 8.dp
            ) {
                Box(Modifier.background(buttonBackgroundColor).padding(8.dp))
                {
                    Image(
                        painter = painterResource(LikeButtonImage.favorite),
                        contentDescription = "favorite",
                        modifier = iconModifier,
                        colorFilter = ColorFilter.tint(color = iconColor)
                    )
                    if (vmRecipe.currentState.isLiked) {
                        Image(
                            painter = painterResource(LikeButtonImage.favoriteFilled),
                            contentDescription = "favorite",
                            modifier = iconModifier,
                            colorFilter = ColorFilter.tint(color = iconColor)
                        )
                    }
                }
            }
        }
    )
}