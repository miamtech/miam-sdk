package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.miam.kmmMiamCore.component.catalog.CatalogContent
import com.miam.kmmMiamCore.component.catalog.CatalogViewModel
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogModifier.categoryListContainer
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.favoriteTitle
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.filterSearchTitle
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.prefixWordSearchTitle
import com.miam.kmm_miam_sdk.android.ui.components.categoryRecipesPage.CategoryRecipesPage
import com.miam.kmm_miam_sdk.android.ui.components.searchRecipesPage.SearchRecipesPage

@Composable
fun CatalogSuccessView(
    categories: List<Package>,
    content: CatalogContent,
    enableFilters: Boolean,
    enablePreferences: Boolean,
    columns: Int,
    vSpacing: Int,
    hSpacing: Int,
    vmCatalog: CatalogViewModel
) {

    val routeService = RouteServiceInstance.instance
    val filterVM = FilterViewModelInstance.instance

    fun back() = routeService.previous()
    val catId = vmCatalog.currentState.openedCategoryId
    val catTitle = vmCatalog.currentState.openedCategoryTitle
    val catSubtitle = vmCatalog.currentState.subtitle


    Column {
        CatalogHeader(content, enableFilters, enablePreferences, vmCatalog)
        when (content) {
            CatalogContent.CATEGORIES_LIST -> CatalogBodyCategories(categories = categories, vmCatalog = vmCatalog)
            CatalogContent.FILTER_SEARCH -> CatalogBodyFilterSearch(::back, columns, vSpacing, hSpacing)
            CatalogContent.WORD_SEARCH -> CatalogBodyWordSearch(filterVM.currentState.searchString, columns, vSpacing, hSpacing)
            CatalogContent.CATEGORY -> CatalogBodyCategory(catId, catTitle, catSubtitle ?: "", columns, vSpacing, hSpacing)
            CatalogContent.FAVORITE -> CatalogBodyFavorite(::back, columns, vSpacing, hSpacing)
        }
    }
}

@Composable
fun CatalogBodyCategories(categories: List<Package>, vmCatalog: CatalogViewModel) {
    Box(categoryListContainer.fillMaxSize()) {
        if (categories.isNotEmpty()) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                categories.forEach { cat ->
                    CatalogCategory(cat, LocalContext.current) {
                        vmCatalog.goToCategory(
                            it.id,
                            it.attributes?.title ?: "",
                            it.attributes?.settings?.subtitle
                        )
                    }
                }
            }
        } else {
            CatalogEmptyPage("avec vos préférences", false) {}
        }
        Template.CatalogFloatingElementTemplate?.let { it() }
    }
}

@Composable
fun CatalogBodyFilterSearch(back: () -> Unit, columns: Int, vSpacing: Int, hSpacing: Int) {
    CatalogPage(LocalContext.current).apply { bind(filterSearchTitle, back, columns, vSpacing, hSpacing) }.Content()
}

@Composable
fun CatalogBodyWordSearch(searchStr: String?, columns: Int, vSpacing: Int, hSpacing: Int) {
    val title = "$prefixWordSearchTitle \"$searchStr\""
    SearchRecipesPage(LocalContext.current).apply { bind(title, columns, vSpacing, hSpacing) }.Content()
}

@Composable
fun CatalogBodyCategory(openedCategoryId: String, openedCategoryTitle: String, subtitle: String, columns: Int, vSpacing: Int, hSpacing: Int) {
    CategoryRecipesPage(LocalContext.current).apply { bind(openedCategoryId, openedCategoryTitle, subtitle, columns, vSpacing, hSpacing) }.Content()
}

@Composable
fun CatalogBodyFavorite(back: () -> Unit, columns: Int, vSpacing: Int, hSpacing: Int) {
    // TODO use stand alone favorite
    CatalogPage(LocalContext.current).apply { bind(favoriteTitle, back, columns, vSpacing, hSpacing) }.Content()
}