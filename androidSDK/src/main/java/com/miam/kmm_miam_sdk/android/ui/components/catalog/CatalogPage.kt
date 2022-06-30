package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageColor
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePageStyle
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.recipeListPage.RecipeListPageContract
import com.miam.kmm_miam_sdk.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

@Composable
fun CatalogPage(recipePageVM :RecipeListPageViewModel, context: Context, returnToCategoriesPage: () -> Unit) {

        val state = recipePageVM.uiState.collectAsState()

    ManagementResourceState(
        resourceState = state.value.recipes,
        successView = { favoritesRecipes ->
            requireNotNull(favoritesRecipes)
            CatalogSuccessPage(
                recipePageVM,
                favoritesRecipes,
                context
            ) { returnToCategoriesPage() }
        },
        emptyView = {
           
        },
        loadingView = {
            CatalogLoadingPage()
        },
        onTryAgain = { /**TODO*/ },
        onCheckAgain = { /**TODO*/ },
    )
    }


@Composable
private fun CatalogSuccessPage (recipePageVM :RecipeListPageViewModel, recipes: List<Recipe>, context: Context, returnToCategoriesPage: () -> Unit){
    LazyColumn(
        modifier = FavoritePageStyle.favoriteMainContainer,
    ) {
        item {  Row() {
            Clickable(onClick = { returnToCategoriesPage() }) {
                Image(
                    painter = painterResource(CatalogImage.back),
                    contentDescription = null,
                    Modifier
                        .rotate(180f)
                        .padding(vertical = 8.dp)
                )
            }
            Text(text = recipePageVM.currentState.title,
                color= Colors.black,
                style = Typography.subtitleBold )
        } }
        itemsIndexed(recipes) { index, item ->
            val recipe =  RecipeView(context = context)
            recipe.bind(recipe = item)
            recipe.Content()
            if(index == recipes.lastIndex){
                recipePageVM.setEvent(RecipeListPageContract.Event.LoadPage)
                if(recipePageVM.currentState.isFetchingNewPage){
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

@Composable
private fun CatalogLoadingPage(){
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Armement du batteur...", style = Typography.subtitleBold , modifier = Modifier.padding(8.dp))
        CircularProgressIndicator(color = Colors.primary)
    }
}
