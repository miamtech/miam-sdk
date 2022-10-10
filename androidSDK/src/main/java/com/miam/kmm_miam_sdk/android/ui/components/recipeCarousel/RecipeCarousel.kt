package com.miam.kmm_miam_sdk.android.ui.components.recipeCarousel

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.recipeCarousel.RecipeCarouselContract
import com.miam.kmmMiamCore.component.recipeCarousel.RecipeCarouselViewModel
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class RecipeCarousel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private var vmRecipeCarousel: RecipeCarouselViewModel = RecipeCarouselViewModel()
    private var recipeListSize = 4

    fun bind(
        productId: String = "",
        recipeListSize: Int?
    ) {
        if (recipeListSize != null) {
            vmRecipeCarousel.setEvent(RecipeCarouselContract.Event.GetSuggestionsFromIdAndSize(productId, recipeListSize))
            this.recipeListSize = recipeListSize
            return
        }
        vmRecipeCarousel.setEvent(RecipeCarouselContract.Event.GetSuggestionFromId(productId))
    }

    @Composable
    override fun Content() {
        val state by vmRecipeCarousel.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.suggestions,
            successView = { suggestions ->
                requireNotNull(suggestions)
                RecipeCarouselSuccessView(suggestions, context)
            },
            loadingView = {
                RecipeCarouselLoadingView(recipeListSize)
            },
            emptyView = {
                Box {}
            },
            onTryAgain = { },
            onCheckAgain = { },
        )
    }
}