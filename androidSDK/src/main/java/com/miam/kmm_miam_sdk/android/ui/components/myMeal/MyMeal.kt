package com.miam.kmm_miam_sdk.android.ui.components.myMeal

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Template

import com.miam.kmm_miam_sdk.android.ui.components.dialog.Dialog
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel
import com.miam.kmm_miam_sdk.component.myMeal.MyMealContract
import com.miam.kmm_miam_sdk.component.myMeal.MyMealViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine


class MyMeal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr)
{
    private val myMealVm = MyMealViewModel()
    private val modal = Dialog()

    @Composable
    override fun Content() {

        val state by myMealVm.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.lines,
            successView = { previewLines ->
                requireNotNull(previewLines)
                Column {
                    modal.Content()
                    CurrentRecipeInBasket(
                        previewLines,
                        myMealVm,
                        { vmRecipe -> modal.goToDetail(vmRecipe,false) },
                        { modal.goToReplaceItem() }
                    )
                }
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CurrentRecipeInBasket(
    previewLines: List<BasketPreviewLine>,
    myMealVm: MyMealViewModel,
    goToDetail: (vmRecipe: RecipeViewModel) -> Unit,
    goToReplaceItem: () -> Unit
){

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    previewLines.forEach {  previewLine ->
             val recipeVM = RecipeViewModel()
             val vmBasketPreview  = BasketPreviewViewModel(previewLine.id ?: "")
             recipeVM.setEvent(
                 RecipeContract.Event.OnGetRecipe( previewLine.id ?: "")
             )


        ExpendableBasketPreviewLine(
            line = previewLine,
            recipeVm = recipeVM,
            vmBasketPreview = vmBasketPreview,
            goToDetail = { goToDetail(recipeVM)  },
            removeRecipe = {
                myMealVm.setEvent(MyMealContract.Event.RemoveRecipe(previewLine.id.toString()))
            },
            goToReplaceItem = {
                goToReplaceItem()
             }
            )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

