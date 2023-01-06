package com.miam.kmm_miam_sdk.android.ui.components.myMealButton

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.myMealButton.MyMealButtonViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class MyMealButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {

    private var onclick: () -> Unit = {
        LogHandler.error("[Miam][Error] You must init onclick on My meal button")
    }

    fun bind(onclick: () -> Unit) {
        this.onclick = onclick
    }

    private val myMealButtonVM: MyMealButtonViewModel = MyMealButtonViewModel()


    @Composable
    override fun Content() {
        val state by myMealButtonVM.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.recipeCount,
            successView = { recipeCount ->
                requireNotNull(recipeCount)
                MyMealButtonSuccessView(recipeCount, onclick)
            },
            loadingView = {
                Box {}
            },
            emptyView = {
                MyMealButtonEmptyView()
            },
            onTryAgain = { },
            onCheckAgain = { },
        )
    }
}