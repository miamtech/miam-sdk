package com.miam.kmm_miam_sdk.android.ui.components.favoritePage

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.miam.kmmMiamCore.component.favoritePage.FavoritePageContract
import com.miam.kmmMiamCore.component.favoritePage.FavoritePageViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageColor.loaderColor
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.emptyStateMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.favoriteMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.goToDetailButton
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.loadMoreContainer
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.loadMoreModifier
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.loadingStateLoaderModifier
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.loadingStateMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle.noFavoriteText
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class FavoritePage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private var vmFavoritePage: FavoritePageViewModel = FavoritePageViewModel()
    private fun goVisitCatalog() {}

    @Composable
    override fun Content() {
        val state by vmFavoritePage.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.favoritesRecipes,
            successView = { favoritesRecipes ->
                requireNotNull(favoritesRecipes)
                FavoriteList(
                    vmFavoritePage,
                    favoritesRecipes,
                    context
                )
            },
            emptyView = {
                if (Template.emptyFavoritePage != null) {
                    Template.emptyFavoritePage?.let {
                        it(::goVisitCatalog)
                    }
                } else {
                    FavoriteEmpty(::goVisitCatalog)
                }
            },
            loadingView = {
                if (Template.loadingFavoritePage != null) {
                    Template.loadingFavoritePage?.let { it() }
                } else {
                    FavoriteLoading()
                }
            },
            onTryAgain = { /**TODO*/ },
            onCheckAgain = { /**TODO*/ },
        )
    }


    @Composable
    private fun FavoriteList(
        vmFavoritePage: FavoritePageViewModel,
        favoritesRecipes: List<Recipe>,
        context: Context
    ) {
        LazyColumn(
            modifier = favoriteMainContainer,
//            contentPadding = spaceBetweenRecipe as PaddingValues
        ) {
            itemsIndexed(favoritesRecipes) { index, item ->
                val recipe = RecipeView(context = context)
                recipe.bind(recipe = item)
                recipe.Content()
                if (index == favoritesRecipes.lastIndex) {
                    vmFavoritePage.setEvent(FavoritePageContract.Event.LoadPage)
                    if (vmFavoritePage.currentState.isFetchingNewPage) {
                        Row(
                            modifier = loadMoreContainer,
                            Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = loaderColor,
                                modifier = loadMoreModifier
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun FavoriteLoading() {
        Column(
            modifier = loadingStateMainContainer,
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = loaderColor,
                modifier = loadingStateLoaderModifier
            )
        }
    }

    @Composable
    private fun FavoriteEmpty(visitCatalog: () -> Unit) {
        Column(
            modifier = emptyStateMainContainer,
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(FavoritePageImage.favorite),
                contentDescription = "favorite",
                modifier = FavoritePageStyle.favoriteIconModifier,
                colorFilter = ColorFilter.tint(color = FavoritePageColor.favoriteIconColor)
            )
            Text(
                FavoritePageText.noFavoriteYet,
                modifier = noFavoriteText,
                textAlign = TextAlign.Center,
                style = Typography.subtitle
            )
            Button(
                onClick = { visitCatalog() },
                modifier = goToDetailButton
            ) {
                Text(
                    FavoritePageText.goToCatalog,
                    style = Typography.button
                )
            }
        }
    }
}