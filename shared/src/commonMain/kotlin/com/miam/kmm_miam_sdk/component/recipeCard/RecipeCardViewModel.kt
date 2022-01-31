package com.miam.kmm_miam_sdk.component.recipeCard

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListEffect
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import com.miam.kmm_miam_sdk.domain.interactors.AddRecipeUseCase
import com.miam.kmm_miam_sdk.domain.interactors.GetRecipeUseCase
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import org.koin.core.component.inject

open class RecipeCardViewModel :
    BaseViewModel<RecipeCardContract.Event, RecipeCardContract.State, RecipeCardContract.Effect>() {

    private val getRecipeUseCase: GetRecipeUseCase by inject()
    private val addRecipeUseCase: AddRecipeUseCase by inject()
    private val groceriesListStore :GroceriesListStore by inject()

    private var recipeId: Int? = null
    private lateinit var recipe: Recipe

    override fun createInitialState(): RecipeCardContract.State =
        RecipeCardContract.State(
            recipeCard = BasicUiState.Loading,
            headerText = "",
            guest= 4,
            isInCart= false,
            analyticsEventSent = false,
            isPriceDisplayed= false,
            isInViewport= false,
        )

    init {
        launch {
            groceriesListStore.observeSideEffect().collect{
                handleGLChange(it)
            }
        }
    }

    override fun handleEvent(event: RecipeCardContract.Event) {
        when (event) {
            is RecipeCardContract.Event.OnGetRecipe -> getRecipe(event.idRecipe)
            RecipeCardContract.Event.OnAddRecipe -> addRecipe()
            RecipeCardContract.Event.Retry -> recipeId?.let { getRecipe(it) }
        }
    }

    private fun handleGLChange(gl : GroceriesListEffect) {
        when (gl) {
            is GroceriesListEffect.GroceriesListLoaded -> {
                setState { copy(isInCart = checkIsInCart())}
            }
            is GroceriesListEffect.RecipeAdded -> {
                if(gl.recipeId !==  recipeId) return
                setState { copy(isInCart = true , guest = gl.guests) }
            }
            is GroceriesListEffect.RecipeRemoved -> {
                if(gl.recipeId !==  recipeId) return
                setState { copy(isInCart = false) }
            }
        }
    }

    private fun checkIsInCart() : Boolean {
       val currentGl = groceriesListStore.observeState().value.groceriesList ?: return false
       return  currentGl.attributes.recipesInfos != null &&  currentGl.attributes.recipesInfos.any { ri ->ri.id == recipeId }
    }

   private fun addRecipe() {
       launch(addRecipeUseCase.execute(recipe), {
           setState { copy(isInCart = true) }
       })
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
            setState { copy(
                recipeCard = BasicUiState.Success(recipe),
                guest= getGuest(recipe),
                isInCart = checkIsInCart()
            ) }
            this.recipe = recipe
            displayPrice()
        }, {
            setState {  copy(recipeCard = BasicUiState.Error()) }
            setEffect { RecipeCardContract.Effect.HideCard }
        })
    }

    private fun getGuest(recipe: Recipe) :Int{
        return recipe.attributes.numberOfGuests ?: 4
    }


    private fun  recipeLoaded(){
        this.initIngredientsString();
        // TODO handle guests count
        //this.recipe.modifiedGuests ||= +this.recipe.guests;
    }

    private fun initIngredientsString() {
        var  ingredientsConcatName = ""
        if (recipe != null) {

        }
    }

   private fun displayPrice() {
        if (currentState.isPriceDisplayed || !currentState.isInViewport) {
            return
        }
       setState { copy(isPriceDisplayed = true) }
    }
}