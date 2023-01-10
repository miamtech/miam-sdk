package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import android.util.AttributeSet
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
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageContract
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.templatesDescriptors.CatalogPageTitleTemplateDescriptor
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.backToCategories
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.noFavoriteYet
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.noResultFor
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.recipesListLoading
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.tryAnOtherSearch
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageColor
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

open class CatalogPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {

    private val recipePageVM = RecipeListPageViewModel()
    private var catalogPageTitle = ""
    private var catalogPageSubtitle = ""
    private var catalogPageColumns = 1
    private var catalogPageVerticalSpacing = 12
    private var catalogPageHorizontalSpacing = 12
    private var catalogPageBack: () -> Unit = { LogHandler.error("[Miam][Error] you must bind back function") }

    fun bind(
        title: String,
        back: () -> Unit,
        columns: Int? = null,
        verticalSpacing: Int? = null,
        horizontalSpacing: Int? = null,
        subtitle: String? = null,
    ) {
        catalogPageTitle = title
        catalogPageBack = back

        columns?.let { catalogPageColumns = it }
        verticalSpacing?.let { catalogPageVerticalSpacing = it }
        horizontalSpacing?.let { catalogPageHorizontalSpacing = it }
        subtitle?.let { catalogPageSubtitle = it }
        recipePageVM.setEvent(RecipeListPageContract.Event.InitPage(title))
    }

    @Composable
    override fun Content() {
        val state = recipePageVM.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.value.recipes,
            successView = { recipes ->
                requireNotNull(recipes)
                CatalogSuccessPage(
                    context,
                    catalogPageTitle,
                    catalogPageSubtitle,
                    recipes,
                    catalogPageColumns,
                    catalogPageVerticalSpacing,
                    catalogPageHorizontalSpacing,
                    recipePageVM.currentState.isFetchingNewPage,
                ) {
                    recipePageVM.setEvent(RecipeListPageContract.Event.LoadPage)
                }
            },
            emptyView = {
                CatalogEmptyPage(
                    recipePageVM.currentState.title,
                    recipePageVM.currentState.filter.contains("filter[liked]=true&"),
                    catalogPageBack
                )
            },
            loadingView = {
                if (Template.CatalogResultPageLoadingTemplate != null) {
                    Template.CatalogResultPageLoadingTemplate?.let { it() }
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
        context: Context,
        title: String,
        subtitle: String,
        recipes: List<Recipe>,
        columns: Int,
        verticalSpacing: Int,
        horizontalSpacing: Int,
        isFetching: Boolean,
        loadPage: () -> Unit
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = FavoritePageStyle.favoriteMainContainer,
                columns = GridCells.Fixed(columns),
                verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp, Alignment.Top),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp, Alignment.Start)
            ) {
                item(span = { GridItemSpan(columns) }) {
                    HeaderTitle(title = title, subtitle)
                }
                itemsIndexed(recipes) { index, item ->
                    val recipe = RecipeView(context = context)
                    recipe.bind(recipe = item)
                    recipe.isNotInShelf()
                    recipe.Content()
                    if (index == recipes.lastIndex) {
                        loadPage()
                    }
                }
                item(span = { GridItemSpan(columns) }) { FooterLoader(isFetching) }
            }
        }
    }

    @Composable
    private fun HeaderTitle(title: String, subtitle: String) {
        val templateToUse = specificTemplate() ?: Template.CatalogPageTitleTemplate
        if (templateToUse != null) {
            templateToUse(CatalogPageTitleTemplateDescriptor(title, subtitle))
        } else {
            Row { Text(text = title, color = Colors.black, style = Typography.subtitleBold) }
        }
    }

    protected open fun specificTemplate(): @Composable() ((CatalogPageTitleTemplateDescriptor) -> Unit)? {
        return null
    }

    @Composable
    private fun FooterLoader(isFetching: Boolean) {
        if (isFetching) {
            if (Template.CatalogResultPageLazyLoaderTemplate != null) {
                Template.CatalogResultPageLazyLoaderTemplate?.let { it() }
            } else {
                Row(modifier = FavoritePageStyle.loadMoreContainer, Arrangement.Center) {
                    CircularProgressIndicator(color = FavoritePageColor.loaderColor, modifier = FavoritePageStyle.loadMoreModifier)
                }
            }
        }
    }

    @Composable
    private fun CatalogLoadingPage() {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(recipesListLoading, style = Typography.subtitleBold, modifier = Modifier.padding(8.dp))
            CircularProgressIndicator(color = Colors.primary)
        }
    }
}

@Composable
fun FavoriteEmptyPage(returnToCategoriesPage: () -> Unit) {
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
            Image(painter = painterResource(CatalogImage.empty), contentDescription = null, Modifier.padding(vertical = 16.dp))
            Clickable(onClick = returnToCategoriesPage) {
                Column {
                    Text(
                        text = noFavoriteYet,
                        color = white,
                        style = Typography.subtitleBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp)
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(50))
                                .background(white)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = backToCategories,
                                    color = primary,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
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

@Composable
fun SearchResultEmptyPage(pageTitle: String) {
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
            Image(painter = painterResource(CatalogImage.empty), contentDescription = null, Modifier.padding(vertical = 16.dp))
            Text(
                text = noResultFor + pageTitle,
                color = white,
                style = Typography.subtitleBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(text = tryAnOtherSearch, color = white)
        }
    }
}


@Composable
fun CatalogEmptyPage(
    title: String,
    isFavorite: Boolean,
    returnToCategoriesPage: () -> Unit
) {
    if (isFavorite) {
        if (Template.CatalogFavoritEmptyTemplate != null) {
            Template.CatalogFavoritEmptyTemplate?.let { it(returnToCategoriesPage) }
        } else {
            FavoriteEmptyPage(returnToCategoriesPage)
        }
    } else {
        if (Template.CatalogSearchResultEmptyTemplate != null) {
            Template.CatalogSearchResultEmptyTemplate?.let { it(returnToCategoriesPage, title) }
        } else {
            SearchResultEmptyPage(pageTitle = title)
        }
    }
}

