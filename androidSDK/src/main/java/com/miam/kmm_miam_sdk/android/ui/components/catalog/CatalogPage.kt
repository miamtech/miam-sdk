package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageContract
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageColor
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

@Composable
fun CatalogPage(
    recipePageVM: RecipeListPageViewModel,
    context: Context,
    columns: Int,
    verticalSpacing: Int,
    horizontalSpacing: Int,
    returnToCategoriesPage: () -> Unit
) {

    val state = recipePageVM.uiState.collectAsState()

    ManagementResourceState(
        resourceState = state.value.recipes,
        successView = { recipes ->
            requireNotNull(recipes)
            CatalogSuccessPage(
                recipePageVM,
                recipes,
                columns,
                verticalSpacing,
                horizontalSpacing,
                context
            )
        },
        emptyView = {
            CatalogEmptyPage(recipePageVM, returnToCategoriesPage)
        },
        loadingView = {
            if (Template.CatalogResultPageLoadingTemplate != null) {
                Template.CatalogResultPageLoadingTemplate?.let {
                    it()
                }
            } else {
                CatalogLoadingPage()
            }
        },
        onTryAgain = { /**TODO*/ },
        onCheckAgain = { /**TODO*/ },
    )
}

@Composable
private fun CatalogSuccessPage(
    recipePageVM: RecipeListPageViewModel,
    recipes: List<Recipe>,
    columns: Int,
    verticalSpacing: Int,
    horizontalSpacing: Int,
    context: Context
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = FavoritePageStyle.favoriteMainContainer,
            columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp, Alignment.Top),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp, Alignment.Start)
        ) {
            item(span = { GridItemSpan(columns) }) {
                if (Template.CatalogPageTitleTemplate != null) {
                    Template.CatalogPageTitleTemplate?.let {
                        it(recipePageVM.currentState.title)
                    }
                } else {
                    Row {
                        Text(
                            text = recipePageVM.currentState.title,
                            color = Colors.black,
                            style = Typography.subtitleBold
                        )
                    }
                }
            }
            itemsIndexed(recipes) { index, item ->
                val recipe = RecipeView(context = context)
                recipe.bind(recipe = item)
                recipe.isNotInShelf()
                recipe.Content()
                if (index == recipes.lastIndex) {
                    recipePageVM.setEvent(RecipeListPageContract.Event.LoadPage)
                }
            }
            item(span = { GridItemSpan(columns) }) {
                if (recipePageVM.currentState.isFetchingNewPage) {
                    if (Template.CatalogResultPageLazyLoaderTemplate != null) {
                        Template.CatalogResultPageLazyLoaderTemplate?.let {
                            it()
                        }
                    } else {
                        Row(
                            modifier = FavoritePageStyle.loadMoreContainer,
                            Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = FavoritePageColor.loaderColor,
                                modifier = FavoritePageStyle.loadMoreModifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CatalogLoadingPage() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Chaud devant !!", style = Typography.subtitleBold, modifier = Modifier.padding(8.dp))
        CircularProgressIndicator(color = Colors.primary)
    }
}

@Composable
private fun CatalogEmptyPage(
    recipePageVM: RecipeListPageViewModel,
    returnToCategoriesPage: () -> Unit
) {

    val isFavorit = recipePageVM.currentState.filter.contains("filter[liked]=true&")

    if (isFavorit) {
        if (Template.CatalogFavoritEmptyTemplate != null) {
            Template.CatalogFavoritEmptyTemplate?.let {
                it { returnToCategoriesPage() }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primary)
            ) {
                Column(
                    Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(CatalogImage.empty),
                        contentDescription = null,
                        Modifier
                            .padding(vertical = 16.dp)
                    )
                    Clickable(onClick = { returnToCategoriesPage() }) {
                        Column {
                            Text(
                                text = "Oups, vous n’avez pas encore d’idée repas",
                                color = white,
                                style = Typography.subtitleBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 8.dp)
                            )
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(white)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Parcourir les idées repas",
                                            color = primary,
                                            modifier = Modifier.padding(
                                                horizontal = 16.dp,
                                                vertical = 8.dp
                                            ),
                                        )
                                        Image(
                                            painter = painterResource(CatalogImage.back),
                                            contentDescription = null,
                                            Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        if (Template.CatalogSearchResultEmptyTemplate != null) {
            Template.CatalogSearchResultEmptyTemplate?.let {
                it { returnToCategoriesPage() }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primary)
            ) {

                Column(
                    Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(CatalogImage.empty),
                        contentDescription = null,
                        Modifier
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = "Oups, aucune recette n’a été trouvée pour '${recipePageVM.currentState.title}'",
                        color = white,
                        style = Typography.subtitleBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = "Essayez une nouvelle recherche.",
                        color = white
                    )
                }
            }
        }
    }
}
