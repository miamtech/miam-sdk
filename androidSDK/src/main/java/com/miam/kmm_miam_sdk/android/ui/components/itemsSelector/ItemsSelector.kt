package com.miam.kmm_miam_sdk.android.ui.components.itemsSelector

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class ItemsSelector {

    private val vmItemSelector: ItemSelectorViewModel by lazy { MiamDI.itemSelectorViewModel }
    private val routeService by lazy { MiamDI.routeService }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Content() {

        val state by vmItemSelector.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.selectedItem,
            successView = { currentItem ->
                requireNotNull(currentItem)
                ItemsSelectorSuccessView(currentItem, state.items, { routeService.previous() }, { item, index -> vmItemSelector.choose(item, index) })
            },
            loadingView = {
                if (Template.itemSelectorLoadingTemplate != null) {
                    Template.itemSelectorLoadingTemplate?.let { it { routeService.previous() } }
                } else {
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            },
            emptyView = {
                if (Template.itemSelectorEmptyTemplate != null) {
                    Template.itemSelectorEmptyTemplate?.let { it { routeService.previous() } }
                } else {
                    Box {}
                }
            }
        )
    }
}
