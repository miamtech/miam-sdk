package com.miam.kmm_miam_sdk.android.ui.components.likeButton


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.recipeLike.LikeButtonViewModel
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonColor.buttonBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonColor.iconColor
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButtonStyle.iconModifier
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class LikeButton {

    private val likeButtonViewModel = LikeButtonViewModel()

    fun bind(recipeId: String) {
        likeButtonViewModel.setRecipe(recipeId)
    }

    @Composable
    fun Content() {

        val state by likeButtonViewModel.uiState.collectAsState()

        LaunchedEffect(Unit) { likeButtonViewModel.listenRecipeLikeChanges() }
        DisposableEffect(Unit) { onDispose { likeButtonViewModel.stopListenRecipeLikeChanges() } }

        ManagementResourceState(
            resourceState = state.isLiked,
            successView = { isLiked ->
                requireNotNull(isLiked)
                RecipeLikeSuccessButton({ likeButtonViewModel.toggleLike() }, isLiked)
            },
            loadingView = {
                //TODO
            },
            emptyView = {
                //TODO
            },
            onTryAgain = {},
            onCheckAgain = {},
        )
    }

    @Composable
    fun RecipeLikeSuccessButton(toggleLike: () -> Unit, isLiked: Boolean) {
        Clickable(
            onClick = toggleLike
        ) {
            Surface(
                shape = CircleShape
            ) {
                Box(
                    Modifier
                        .background(buttonBackgroundColor)
                        .padding(8.dp)
                )
                {
                    Image(
                        painter = painterResource(LikeButtonImage.favorite),
                        contentDescription = "favorite",
                        modifier = iconModifier,
                        colorFilter = ColorFilter.tint(color = iconColor)
                    )
                    if (isLiked) {
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
    }
}