package com.miam.kmm_miam_sdk.component.tag

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterOutletViewModel
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import org.koin.core.component.inject

open class TagViewModel(private val vmRouter: RouterOutletViewModel) :
    BaseViewModel<TagContract.Event, TagContract.State, TagContract.Effect>() {

    private val groceriesListStore: GroceriesListStore by inject()

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
         is TagContract.Event.SetIngredientId -> setBelongingRecipes(event.ingredientId)
        }
    }

    private  fun setBelongingRecipes(ingredientId: String){
        LogHandler.info("getting belonging recipes for $ingredientId")
        val gl = groceriesListStore.getGroceriesList()
        if (gl == null) {
            // TODO: raise an error ?
            setState { copy(recipeList = BasicUiState.Empty) }
            return
        }

        val recipesWithIngredient = gl.recipes.filter { recipe ->
            recipe.relationships!!.ingredients!!.data.any { ingredient -> ingredient.id == ingredientId }
        }
        val newState = if (recipesWithIngredient.isEmpty()) BasicUiState.Empty else BasicUiState.Success(recipesWithIngredient)
        setState { copy(recipeList = newState) }
    }

}