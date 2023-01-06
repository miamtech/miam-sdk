package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.miam.kmmMiamCore.component.catalog.CatalogContent
import com.miam.kmmMiamCore.component.catalog.CatalogContract
import com.miam.kmmMiamCore.component.catalog.CatalogViewModel
import com.miam.kmmMiamCore.component.catalog.DialogContent
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogModifier.categoryListContainer
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.prefixWordSearchTitle
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.filterSearchTitle
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.favoriteTitle
import com.miam.kmm_miam_sdk.android.ui.components.categoryRecipesPage.CategoryRecipesPage
import com.miam.kmm_miam_sdk.android.ui.components.common.RoutableDialog
import com.miam.kmm_miam_sdk.android.ui.components.preferences.Preferences

@Composable
fun CatalogSuccessView(
    categories: List<Package>,
    state: CatalogContract.State,
    context: Context,
    columns: Int,
    verticalSpacing: Int,
    horizontalSpacing: Int,
    vmCatalog: CatalogViewModel,
) {

    val routeService = RouteServiceInstance.instance

    val filter = CatalogFilter(
        { routeService.onCloseDialog() },
        { vmCatalog.onSimpleSearch(CatalogContent.FILTER_SEARCH) }
    )
    val search = CatalogSearch(
        { routeService.onCloseDialog() },
        { vmCatalog.onSimpleSearch(CatalogContent.WORD_SEARCH) }
    )

    val preference = Preferences(context).apply { bind({ routeService.onCloseDialog() }, { routeService.onCloseDialog() }) }
    val filterVM = FilterViewModelInstance.instance

    Column {
        if (state.dialogIsOpen) {
            RoutableDialog(onDismissRequest = {
                routeService.previous()
            }) {
                when (state.dialogContent) {
                    DialogContent.FILTER -> filter.Content()
                    DialogContent.SEARCH -> search.Content()
                    DialogContent.PREFERENCES -> preference.Content()
                }
            }
        }

        CatalogHeader(state, vmCatalog)
        when (state.content) {
            CatalogContent.CATEGORIES_LIST -> {
                Box(categoryListContainer.fillMaxSize()) {
                    Categories(categories = categories, context = context, vmCatalog = vmCatalog)
                    Template.CatalogFloatingElementTemplate?.let { it() }
                }
            }
            CatalogContent.FILTER_SEARCH -> {
                CatalogPage(context).apply {
                    bind(filterSearchTitle, { routeService.previous() }, columns, verticalSpacing, horizontalSpacing)
                }.Content()
            }
            CatalogContent.WORD_SEARCH -> {
                val title = "$prefixWordSearchTitle \"${filterVM.currentState.searchString}\""
                CatalogPage(context).apply {
                    bind(title, { routeService.previous() }, columns, verticalSpacing, horizontalSpacing)
                }.Content()
            }
            CatalogContent.CATEGORY -> {
                CategoryRecipesPage(context).apply {
                    bind(
                        categoryId = vmCatalog.currentState.openedCategoryId,
                        categoryTitle = vmCatalog.currentState.openedCategoryTitle,
                        columns,
                        verticalSpacing,
                        horizontalSpacing
                    )
                }.Content()
            }
            CatalogContent.FAVORITE -> {
                // TODO use stand alone favorite
                CatalogPage(context).apply {
                    bind(favoriteTitle, { routeService.previous() }, columns, verticalSpacing, horizontalSpacing)
                }.Content()
            }
        }
    }
}

@Composable
fun Categories(categories: List<Package>, context: Context, vmCatalog: CatalogViewModel) {
    if (categories.isNotEmpty()) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            categories.forEach { cat ->
                CatalogCategory(cat, context) {
                    vmCatalog.goToCategory(it.id, it.attributes?.title ?: "")
                }
            }
        }
    } else {
        CatalogEmptyPage("avec vos préférences", false) {}
    }
}