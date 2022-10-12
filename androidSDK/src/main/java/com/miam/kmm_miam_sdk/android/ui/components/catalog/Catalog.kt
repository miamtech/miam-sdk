package com.miam.kmm_miam_sdk.android.ui.components.catalog

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


class Catalog @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val vmCatalog: CatalogViewModel = CatalogViewModel()

    fun bind(
        categoryId: String,
        title: String?
    ) {
        vmCatalog.setEvent(
            CatalogContract.Event.GoToRecipeListFromCategory(
                categoryId,
                title ?: ""
            )
        )
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
                        CatalogSuccessView(categories, state, context, vmCatalog)
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