package com.miam.kmm_miam_sdk.component.recipeCard

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.domain.interactors.GetRecipeUseCase
import com.miam.kmm_miam_sdk.network.model.Recipe
import org.koin.core.component.inject

class RecipeCardViewModel :
    BaseViewModel<RecipeCardContract.Event, RecipeCardContract.State, RecipeCardContract.Effect>() {

    private val getRecipeUseCase: GetRecipeUseCase by inject()

    private var recipeId: Int? = null
    private lateinit var recipe: Recipe

    override fun createInitialState(): RecipeCardContract.State =
        RecipeCardContract.State(
            recipeCard = BasicUiState.Idle
        )

    override fun handleEvent(event: RecipeCardContract.Event) {
        when (event) {
            is RecipeCardContract.Event.OnGetRecipe -> getRecipe(event.idRecipe)

            RecipeCardContract.Event.Retry -> recipeId?.let { getRecipe(it) }
        }
    }

    private fun getRecipe(recipeId: Int) {
        this.recipeId = recipeId
        setState { copy(recipeCard = BasicUiState.Loading) }
        launch(getRecipeUseCase.execute(recipeId), { recipe ->
            setState { copy(recipeCard = BasicUiState.Success(recipe)) }
            println("miam ${recipe}", )
            this.recipe = recipe
        }, {
            setState {  copy(recipeCard = BasicUiState.Error()) }
        })
    }

}