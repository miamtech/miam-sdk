package com.miam.kmm_miam_sdk.android.ui.components.searchRecipesPage

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.templatesDescriptors.CatalogPageTitleTemplateDescriptor
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.CatalogPage

class SearchRecipesPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): CatalogPage(context, attrs, defStyleAttr) {

    private val routeService = RouteServiceInstance.instance

    fun bind(
        title: String,
        columns: Int? = null,
        verticalSpacing: Int? = null,
        horizontalSpacing: Int? = null,
    ) {
        super.bind(
            title = title,
            back = { routeService.previous() },
            columns = columns,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing,
            subtitle = null
        )
    }

    override fun specificTemplate(): @Composable() ((CatalogPageTitleTemplateDescriptor) -> Unit)? {
        return Template.CatalogSearchTitleTemplate
    }
}
