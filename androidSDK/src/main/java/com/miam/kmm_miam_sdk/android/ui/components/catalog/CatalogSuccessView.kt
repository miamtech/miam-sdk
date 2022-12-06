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
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogModifier.categoryListContainer
import com.miam.kmm_miam_sdk.android.ui.components.preferences.Preferences

@Composable
fun CatalogSuccessView(
    categories: List<Package>,
    state: CatalogContract.State,
    context: Context,
    columns: Int,
    verticalSpacing: Int,
    horizontalSpacing: Int,
    vmCatalog: CatalogViewModel
) {
    val filter = CatalogFilter(vmCatalog.currentState.catalogFilterVM,
        { vmCatalog.setEvent(CatalogContract.Event.ToggleFilter) },
        { vmCatalog.setEvent(CatalogContract.Event.GoToRecipeList) }
    )
    val search = CatalogSearch(
        vmCatalog.currentState.catalogFilterVM,
        { vmCatalog.setEvent(CatalogContract.Event.ToggleSearch) },
        { vmCatalog.setEvent(CatalogContract.Event.GoToRecipeList) }
    )

    val preference = Preferences(context)
    preference.bind(
        { vmCatalog.setEvent(CatalogContract.Event.TogglePreference) },
        { vmCatalog.setEvent(CatalogContract.Event.GoToRecipeList) }
    )

    Column {
        if (state.filterOpen && state.enableFilters) {
            filter.Content()
        }
        if (state.searchOpen) {
            search.Content()
        }
        if (state.preferenceOpen && state.enablePreferences) {
            preference.Content()
        }

        CatalogHeader(state, vmCatalog)
        when (state.content) {
            CatalogContent.DEFAULT -> {
                Box(categoryListContainer.fillMaxSize()) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        categories.forEach { cat ->
                            CatalogCategory(cat, context) {
                                vmCatalog.setEvent(
                                    CatalogContract.Event.GoToRecipeListFromCategory(
                                        it.id,
                                        it.attributes?.title ?: ""
                                    )
                                )
                            }
                        }
                    }
                    Template.CatalogFloatingElementTemplate?.let {
                        it()
                    }
                }

            }
            CatalogContent.RECIPE_LIST -> {
                CatalogPage(vmCatalog.currentState.recipePageVM, context, columns, verticalSpacing, horizontalSpacing) {
                    vmCatalog.setEvent(
                        CatalogContract.Event.GoToDefault
                    )
                }
            }
        }
    }
}