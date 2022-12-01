package com.miam.kmm_miam_sdk.android.ui.components.catalog

import RouteService
import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.catalog.CatalogContract
import com.miam.kmmMiamCore.component.catalog.CatalogViewModel
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class Catalog @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr), KoinComponent {

    private val vmCatalog: CatalogViewModel = CatalogViewModel()
    private val routeService: RouteService by inject()
    var catalogPageColumns = 1
    var catalogPageVerticalSpacing = 12
    var catalogPageHorizontalSpacing = 12

    fun bind(
        categoryId: String? = null,
        title: String? = null,
        catalogPageColumns: Int? = null,
        catalogPageVerticalSpacing: Int? = null,
        catalogPageHorizontalSpacing: Int? = null,
    ) {
        this.catalogPageColumns = catalogPageColumns ?: this.catalogPageColumns
        this.catalogPageVerticalSpacing = catalogPageVerticalSpacing ?: this.catalogPageVerticalSpacing
        this.catalogPageHorizontalSpacing = catalogPageHorizontalSpacing ?: this.catalogPageHorizontalSpacing
        this.routeService.onClose { vmCatalog.setEvent(CatalogContract.Event.OnCloseModal) }
        if (categoryId != null) {
            vmCatalog.setEvent(
                CatalogContract.Event.GoToRecipeListFromCategory(categoryId, title ?: "")
            )
        }
    }

    @Suppress("Used in component injecting catalog")
    fun enablePreference(enable: Boolean = true) {
        vmCatalog.enablePreferences(enable)
    }

    @Suppress("Used in component injecting catalog")
    fun enableFilters(enable: Boolean = true) {
        vmCatalog.enableFilters(enable)
    }

    @Composable
    override fun Content() {

        val state by vmCatalog.uiState.collectAsState()


        Box {
            Column {
                ManagementResourceState(
                    resourceState = state.categories,
                    successView = { categories ->
                        requireNotNull(categories)
                        CatalogSuccessView(
                            categories,
                            state,
                            context,
                            catalogPageColumns,
                            catalogPageVerticalSpacing,
                            catalogPageHorizontalSpacing,
                            vmCatalog
                        )
                    },
                    loadingView = {
                        CatalogLoadingView()
                    },
                    emptyView = {
                        //TODO
                    },
                    onTryAgain = {},
                    onCheckAgain = {},
                )
            }
        }
    }
}