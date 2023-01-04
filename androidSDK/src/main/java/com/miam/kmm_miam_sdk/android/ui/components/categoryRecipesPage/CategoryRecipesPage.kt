package com.miam.kmm_miam_sdk.android.ui.components.categoryRecipesPage

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.CatalogPage

class CategoryRecipesPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): CatalogPage(context, attrs, defStyleAttr) {

    private val routeService = RouteServiceInstance.instance
    private val recipeFilters = FilterViewModelInstance.instance

    fun bind(categoryId: String, categoryTitle: String, columns: Int? = null, verticalSpacing: Int? = null, horizontalSpacing: Int? = null) {
        recipeFilters.setCat(categoryId)
        super.bind(
            title = categoryTitle,
            back = { routeService.previous() },
            columns = columns,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing
        )
    }

    override fun specificTemplate(): @Composable() ((String) -> Unit)? {
        return Template.CatalogCategoryTitleTemplate
    }
}
