package com.miam.kmm_miam_sdk.android.ui.components.myMeal

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.recapPage.RecapPageViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine


class MyMeal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr)
{
    private val myMealVm = RecapPageViewModel()


    @Composable
    override fun Content() {

        val state by myMealVm.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.lines,
            successView = { previewLines ->
                requireNotNull(previewLines)
                CurrentRecipeInBasket(previewLines, myMealVm)
            },
            loadingView = {
                if(Template.recipeLoaderTemplate != null){
                    Template.recipeLoaderTemplate?.let { it() }
                } else {
                    MyMealLoading()
                }
            },
            emptyView =  {
                    Box{}
            },
            onTryAgain = { },
            onCheckAgain = {  },
        )
    }
}

@Composable
private fun MyMealLoading(){
    if (Template.myMealLoaderTemplate != null){
        Template.myMealLoaderTemplate?.let { it() }
    } else {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun CurrentRecipeInBasket(
    previewLines: List<BasketPreviewLine>,
    myMealVm: RecapPageViewModel
){
    previewLines.forEach{ previewLine ->
        BasketPreview()
    }

}

