package com.miam.kmm_miam_sdk.component.tag

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterOutletViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

open class TagViewModel(val vmRouter: RouterOutletViewModel) :
    BaseViewModel<TagContract.Event, TagContract.State, TagContract.Effect>() {

     fun goToDetail(recipe : Recipe){
        val vmRecipe = RecipeViewModel(vmRouter)
        vmRecipe.setEvent(RecipeContract.Event.OnSetRecipe(recipe))
        vmRouter.goToDetail(vmRecipe,false)
    }

    override fun createInitialState(): TagContract.State =
        TagContract.State(
            recipeList = BasicUiState.Loading,
        )


    override fun handleEvent(event: TagContract.Event) {
        when (event) {
         is TagContract.Event.SetIngredientId -> getRecipes(event.ingredientId)
        }
    }

    private  fun getRecipes(ingredientId:  String){
        //TODO ALEX get recipes
    }

}