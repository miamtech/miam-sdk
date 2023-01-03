package com.miam.kmm_miam_sdk.android.ui.components.categoryRecipesPage

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.ui.components.catalog.CatalogPage

class CategoryRecipesPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {

    private val routeService = RouteServiceInstance.instance
    private val recipeFilters = FilterViewModelInstance.instance

    var categoryTitle = ""
    var categoryRecipesPageColumns = 1
    var categoryRecipesPageVerticalSpacing = 12
    var categoryRecipesPageHorizontalSpacing = 12

    fun bind(categoryId: String, categoryTitle: String, columns: Int? = null, verticalSpacing: Int? = null, horizontalSpacing: Int? = null) {
        recipeFilters.setCat(categoryId)
        this.categoryTitle = categoryTitle
        columns?.let { categoryRecipesPageColumns = it }
        verticalSpacing?.let { categoryRecipesPageVerticalSpacing = it }
        horizontalSpacing?.let { categoryRecipesPageHorizontalSpacing = it }
    }

    @Composable
    override fun Content() {
        CatalogPage(context = context).apply {
            bind(
                categoryTitle,
                { routeService.previous() },
                columns = categoryRecipesPageColumns,
                verticalSpacing = categoryRecipesPageVerticalSpacing,
                horizontalSpacing = categoryRecipesPageHorizontalSpacing
            )
        }.Content()
    }
}
