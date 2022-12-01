package com.miam.kmm_miam_sdk.android.ui.components.preferences

import RouteService
import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Preferences @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr), KoinComponent {

    private var close: () -> Unit = {
        throw IllegalArgumentException("[Miam][Error] you must bind close function")
    }

    private var onApply: () -> Unit = {
        throw IllegalArgumentException("[Miam][Error] you must bind close function")
    }

    fun bind(close: () -> Unit, onApply: () -> Unit) {
        this.close = close
        this.onApply = onApply
    }

    private val preferencesVM: SingletonPreferencesViewModel by inject()
    private val routeService: RouteService by inject()

    private fun resetAndClose() {
        preferencesVM.resetPreferences()
        close()
    }

    private fun applyAndClose() {
        preferencesVM.applyPreferences()
        onApply()
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val state by preferencesVM.uiState.collectAsState()
        Dialog(
            onDismissRequest = { routeService.previous() },
            properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = true)
        ) {
            ManagementResourceState(
                resourceState = state.basicState,
                successView = { _ ->
                    PreferencesSuccessView(
                        context,
                        state.guests,
                        state.recipesFound,
                        state.ingredients,
                        state.diets,
                        state.equipments,
                        { preferencesVM.togglePreference(it) },
                        { resetAndClose() },
                        { applyAndClose() },
                        { preferencesVM.changeGlobaleGuest(it) },
                        { preferencesVM.addIngredientPreference(it) }
                    )
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
}