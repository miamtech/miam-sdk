package com.miam.kmm_miam_sdk.android.ui.components.preferences

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.preferences.PreferencesViewModel
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class Preferences @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val preferencesVM = PreferencesViewModel()

    @Composable
    override fun Content() {
        val state by preferencesVM.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.basicState,
            successView = { _ ->
                PreferencesSuccessView(state.ingredients)
            },
            loadingView = {
                PreferencesLoadingView()
            },
            emptyView = {
                Box {}
            },
            onTryAgain = { },
            onCheckAgain = { },
        )
    }
}