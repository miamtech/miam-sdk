package com.miam.kmm_miam_sdk.component.tag

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState

open class TagViewModel:
    BaseViewModel<TagContract.Event, TagContract.State, TagContract.Effect>() {

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
        //TODO get recipes
    }

}