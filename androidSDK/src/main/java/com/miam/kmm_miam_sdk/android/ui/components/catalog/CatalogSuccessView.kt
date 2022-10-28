package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.miam.kmmMiamCore.component.catalog.CatalogContent
import com.miam.kmmMiamCore.component.catalog.CatalogContract
import com.miam.kmmMiamCore.component.catalog.CatalogViewModel
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmm_miam_sdk.android.ui.components.preferences.Preferences

@Composable
fun CatalogSuccessView(
    categories: List<Package>,
    state: CatalogContract.State,
    context: Context,
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
    preference.bind { vmCatalog.setEvent(CatalogContract.Event.TogglePreference) }

    Column {
        if (state.filterOpen) {
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
            }
            CatalogContent.RECIPE_LIST -> {
                CatalogPage(vmCatalog.currentState.recipePageVM, context) {
                    vmCatalog.setEvent(
                        CatalogContract.Event.GoToDefault
                    )
                }
            }
        }
    }
}