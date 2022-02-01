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
    private var isInit: Boolean = false
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
            is RecipeCardContract.Event.UpdateGuest -> updateGuest(event.nbGuest)
            RecipeCardContract.Event.OnAddRecipe -> addOrAlterRecipe()
            RecipeCardContract.Event.DecreaseGuest -> removeGuest()
            RecipeCardContract.Event.IncreaseGuest -> addGuest()
            RecipeCardContract.Event.Retry -> recipeId?.let { getRecipe(it) }
        }
    }

    private fun handleGLChange(gl : GroceriesListEffect) {
        when (gl) {
            is GroceriesListEffect.GroceriesListLoaded -> {
                if (isInit) {
                    setState { copy(isInCart = checkIsInCart(), guest = getGuest(recipe))}
                }else {
                    setState { copy(isInCart = checkIsInCart())}
                    }

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

    private fun removeGuest() {
        if (uiState.value.guest == 1) return
        setState { copy(guest = uiState.value.guest - 1) }
        if(checkIsInCart()) addOrAlterRecipe()
    }

    private fun addGuest(){
        if (uiState.value.guest == 100) return
        setState { copy(guest = uiState.value.guest + 1) }
        if(checkIsInCart()) addOrAlterRecipe()
    }

    private fun updateGuest(nbGuest: Int){
        if (uiState.value.guest <= 1 || uiState.value.guest >= 100 ) return
        setState { copy(guest = nbGuest) }
        if(checkIsInCart()) {
            addOrAlterRecipe()
        }
    }

   private fun addOrAlterRecipe() {
       launch(addRecipeUseCase.execute(recipe.copy(attributes = recipe.attributes.copy(numberOfGuests = uiState.value.guest))), {
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
            this.isInit = true
            displayPrice()
        }, {
            setState {  copy(recipeCard = BasicUiState.Error()) }
            setEffect { RecipeCardContract.Effect.HideCard }
        })
    }

    private fun getGuest(recipe: Recipe) :Int{
        if(checkIsInCart()){
            val currentGl =  groceriesListStore.observeState().value.groceriesList
            return (currentGl?.attributes?.recipesInfos?.find { ri ->ri.id == recipeId })?.guests ?: 4
        }
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
       if (currentState.isPriceDisplayed || !currentState.isInViewport) return
       setState { copy(isPriceDisplayed = true) }
    }
}