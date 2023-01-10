package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.catalog.CatalogContent
import com.miam.kmmMiamCore.component.catalog.CatalogViewModel
import com.miam.kmmMiamCore.component.catalog.DialogContent
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.ui.components.common.RoutableDialog
import com.miam.kmm_miam_sdk.android.ui.components.preferences.Preferences
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState


class Catalog @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {

    private val vmCatalog: CatalogViewModel = CatalogViewModel()
    private val routeService = RouteServiceInstance.instance

    private val filter = CatalogFilter(
        { routeService.onCloseDialog() },
        { vmCatalog.onSimpleSearch(CatalogContent.FILTER_SEARCH) }
    )
    private val search = CatalogSearch(
        { routeService.onCloseDialog() },
        { vmCatalog.onSimpleSearch(CatalogContent.WORD_SEARCH) }
    )
    private val preference = Preferences(context).apply { bind({ routeService.onCloseDialog() }, { routeService.onCloseDialog() }) }

    var catalogPageColumns = 1
    var catalogPageVerticalSpacing = 12
    var catalogPageHorizontalSpacing = 12

    fun goToCategory(categoryId: String, categoryTitle: String) {
        vmCatalog.goToCategory(categoryId, categoryTitle)
    }

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
        if (categoryId != null) {
            vmCatalog.goToCategory(categoryId, title ?: "")
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

        ModaleComponent(state.dialogIsOpen, state.dialogContent, routeService, filter, search, preference)

        Box {
            Column {
                ManagementResourceState(
                    resourceState = state.categories,
                    successView = { categories ->
                        requireNotNull(categories)
                        CatalogSuccessView(
                            categories,
                            state.content,
                            enableFilters = state.enableFilters,
                            enablePreferences = state.enablePreferences,
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

    @Composable
    fun ModaleComponent(
        dialogIsOpen: Boolean,
        content: DialogContent,
        routeService: RouteService,
        filter: CatalogFilter,
        search: CatalogSearch,
        preference: Preferences
    ) {
        if (dialogIsOpen) {
            RoutableDialog(onDismissRequest = {
                routeService.previous()
            }) {
                when (content) {
                    DialogContent.FILTER -> filter.Content()
                    DialogContent.SEARCH -> search.Content()
                    DialogContent.PREFERENCES -> preference.Content()
                }
            }
        }
    }
}