package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.miam.kmm_miam_sdk.component.catalog.CatalogContent
import com.miam.kmm_miam_sdk.component.catalog.CatalogContract
import com.miam.kmm_miam_sdk.component.catalog.CatalogViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Package

@Composable
fun CatalogSuccessView(categories: List<Package>, state:  CatalogContract.State, context: Context,vmCatalog: CatalogViewModel ) {

    val filter = CatalogFilter(state.filter)

    Column(Modifier.verticalScroll(rememberScrollState())) {
        if(state.filterOpen){
            filter.Content()
        }

        CatalogHeader(state,vmCatalog)
        when(state.content) {
            CatalogContent.DEFAULT -> {
                Column() {
                    categories.forEach {
                        CatalogCategory(it, context)
                    }
                }
            }
            CatalogContent.RECIPE_LIST -> {

            }
        }
    }
}