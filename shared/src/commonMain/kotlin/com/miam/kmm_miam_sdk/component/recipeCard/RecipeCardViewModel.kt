package com.miam.kmm_miam_sdk.component.recipeCard

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.domain.interactors.GetRecipeUseCase
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

import org.koin.core.component.inject

open class RecipeCardViewModel :
    BaseViewModel<RecipeCardContract.Event, RecipeCardContract.State, RecipeCardContract.Effect>() {

    private val getRecipeUseCase: GetRecipeUseCase by inject()

    private var recipeId: Int? = null
    private lateinit var recipe: Recipe

    override fun createInitialState(): RecipeCardContract.State =
        RecipeCardContract.State(
            recipeCard = BasicUiState.Loading,
            headerText = "",
            analyticsEventSent = false,
            isPriceDisplayed= false,
            isInViewport= false,
        )

    override fun handleEvent(event: RecipeCardContract.Event) {
        when (event) {
            is RecipeCardContract.Event.OnGetRecipe -> getRecipe(event.idRecipe)
            RecipeCardContract.Event.Retry -> recipeId?.let { getRecipe(it) }
        }
    }

   fun addRecipe() {
       // TODO push recipe to basket service
       // TODO send Add event
    }

    fun sendShowEvent(eventType: String = "show-recipe-card") {
        if (this.recipe == null ) {
            return
        }
        if (!currentState.analyticsEventSent && currentState.isInViewport) {
            // TODO send event with event service
            setState { copy(analyticsEventSent = true) }
        }
    }

    private fun getRecipe(recipeId: Int) {
        this.recipeId = recipeId
        setState { copy(recipeCard = BasicUiState.Loading) }
        launch(getRecipeUseCase.execute(recipeId), { recipe ->
            setState { copy(recipeCard = BasicUiState.Success(recipe)) }
            this.recipe = recipe
            displayPrice()
        }, {
            setState {  copy(recipeCard = BasicUiState.Error()) }
            setEffect { RecipeCardContract.Effect.HideCard }
        })
    }

    private fun  recipeLoaded(){
        this.initIngredientsString();
        // TODO handle guests count
        //this.recipe.modifiedGuests ||= +this.recipe.guests;
    }

    private fun initIngredientsString() {
        var  ingredientsConcatName = ""
        if (recipe != null) {
            // TODO concat ingredient name + ','
        }
    }

   private fun displayPrice() {
        if (currentState.isPriceDisplayed || !currentState.isInViewport) {
            return
        }
       setState { copy(isPriceDisplayed = true) }
    }
}