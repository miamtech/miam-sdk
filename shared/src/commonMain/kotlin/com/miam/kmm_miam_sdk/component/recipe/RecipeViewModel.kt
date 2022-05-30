package com.miam.kmm_miam_sdk.component.recipe

import com.miam.kmm_miam_sdk.base.mvi.*

import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.data.repository.RecipeRepositoryImp

import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

import org.koin.core.component.inject

open class RecipeViewModel :
    BaseViewModel<RecipeContract.Event, RecipeContract.State, RecipeContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception ->
                LogHandler.error("Miam error in recipe view $exception ${exception.stackTraceToString()}")
                //setEvent(RecipeContract.Event.Error)
    }

    private val groceriesListStore: GroceriesListStore by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()

    private var recipeId: String? = null
    private var isInit: Boolean = false
    private lateinit var recipe: Recipe
    private val guestSubject : MutableSharedFlow<Int> = MutableSharedFlow()

    override fun createInitialState(): RecipeContract.State = defaultState()

    private fun defaultState(): RecipeContract.State {
        return RecipeContract.State(
            recipeState = BasicUiState.Loading,
            headerText = "",
            guest = 4,
            isInCart = false,
            analyticsEventSent = false,
            isPriceDisplayed = false,
            isInViewport = false,
            tabState = TabEnum.INGREDIENT,
            activeStep = 0,
            recipeLoaded = false,
            isLiked = false
        )
    }

    init {
        launch(coroutineHandler) {
            groceriesListStore.observeSideEffect().collect {
                handleGLChange(it)
            }

        }
        launch(coroutineHandler) {
            listenguestSubjectChanges()
        }
    }

    override fun handleEvent(event: RecipeContract.Event) {
        when (event) {
            is RecipeContract.Event.OnGetRecipe -> getRecipe(event.idRecipe)
            is RecipeContract.Event.OnSetRecipe -> setRecipe(event.recipe)
            is RecipeContract.Event.UpdateGuest -> updateGuest(event.nbGuest)
            is RecipeContract.Event.SetActiveStep -> setActiveSteps(event.stepIndex)
            is RecipeContract.Event.OnSetCriteria -> setRecipeFromSuggestion(event.crieria)
            RecipeContract.Event.OnUnbind -> unBindRecipe()
            RecipeContract.Event.OnAddRecipe -> addOrAlterRecipe()
            RecipeContract.Event.DecreaseGuest -> removeGuest()
            RecipeContract.Event.IncreaseGuest -> addGuest()
            RecipeContract.Event.ShowIngredient -> setTab(TabEnum.INGREDIENT)
            RecipeContract.Event.ShowSteps -> setTab(TabEnum.STEP)
            RecipeContract.Event.OnToggleLike -> toggleLike()
            RecipeContract.Event.Error -> setState { copy(recipeState = BasicUiState.Empty)  }
        }
    }

    private fun handleGLChange(gl: GroceriesListEffect) {
        when (gl) {
            is GroceriesListEffect.GroceriesListLoaded -> {
                if (isInit) {
                    setState { copy(isInCart = checkIsInCart(), guest = getGuest(recipe)) }
                } else {
                    setState { copy(isInCart = checkIsInCart()) }
                }
            }
            is GroceriesListEffect.RecipeAdded -> {
                if (gl.recipeId != recipeId) return
                setState { copy(isInCart = true, guest = gl.guests) }
            }
            is GroceriesListEffect.RecipeRemoved -> {
                if (gl.recipeId != recipeId) return
                setState { copy(isInCart = false) }
            }
        }
    }

    private suspend fun listenguestSubjectChanges() {
        guestSubject.debounce(500).collect{
                addOrAlterRecipe()
            }
        }

    private fun checkIsInCart(): Boolean {
        val currentGl = groceriesListStore.observeState().value.groceriesList ?: return false
        return currentGl.attributes!!.recipesInfos.isNotEmpty() && currentGl.attributes!!.recipesInfos.any { ri -> ri.id.toString() == recipeId }
    }

    private fun removeGuest() {
        if (uiState.value.guest == 1) return
        setState { copy(guest = uiState.value.guest - 1) }
        if (checkIsInCart()) launch(coroutineHandler) {
            guestSubject.emit(uiState.value.guest)
        }
    }

    private fun addGuest() {
        if (uiState.value.guest == 100) return
        setState { copy(guest = uiState.value.guest + 1) }
        if (checkIsInCart()) launch(coroutineHandler) {
            guestSubject.emit(uiState.value.guest)
        }
    }

    private fun updateGuest(nbGuest: Int) {
        if (uiState.value.guest <= 1 || uiState.value.guest >= 100) return
        setState { copy(guest = nbGuest) }
        if (checkIsInCart()) {
            addOrAlterRecipe()
        }
    }

    private fun addOrAlterRecipe() {
        launch(coroutineHandler) {
            groceriesListStore.dispatch(
                GroceriesListAction.AlterRecipeList(
                    recipe.id , uiState.value.guest)
            )
            setState { copy(isInCart = true) }
        }
    }

    private fun setTab(newTab: TabEnum) {
        if (uiState.value.tabState == newTab) return
        setState { copy(tabState = newTab) }
    }

    private fun setActiveSteps(newActiveStep: Int) {
        if (uiState.value.activeStep == newActiveStep) return
        setState { copy(activeStep = newActiveStep) }
    }

    fun sendShowEvent(eventType: String = "show-recipe-card") {
        if (this.recipe == null) {
            return
        }
        if (!currentState.analyticsEventSent && currentState.isInViewport) {
            // TODO send event with event service
            setState { copy(analyticsEventSent = true) }
        }
    }

    private fun getRecipe(recipeId: String) {
        this.recipeId = recipeId
        setState { copy(recipeState = BasicUiState.Loading) }
        launch(coroutineHandler) {
            val recipe = recipeRepositoryImp.getRecipeById(recipeId)
            setRecipe(recipe)
        }
    }

    private fun setRecipeFromSuggestion(criteria: SuggestionsCriteria){
        LogHandler.info("[Miam][setRecipeFromSuggestion] ${criteria.shelfIngredientsIds?.toString()}")
        setState { copy(recipeState = BasicUiState.Loading) }
        launch(coroutineHandler){
            pointOfSaleStore.observeState().value.idSupplier?.let {
                var recipe = recipeRepositoryImp.getRecipeSuggestions(it, criteria)
                recipe = recipeRepositoryImp.addRecipeLike(recipe)
                setRecipe(recipe)
            }
        }
    }

    private fun setRecipe(recipe: Recipe) {
        setState {
            copy(
                recipeState = BasicUiState.Success(recipe),
                guest = getGuest(recipe),
                isInCart = checkIsInCart(),
                recipeLoaded = true,
                isLiked = recipe.recipeLike?.attributes?.isPast == false
            )
        }
        this.recipe = recipe
        this.recipeId = recipe.id
        this.isInit = true
        displayPrice()
    }

    private fun getGuest(recipe: Recipe): Int {
        if (checkIsInCart()) {
            val currentGl = groceriesListStore.observeState().value.groceriesList
            return (currentGl?.attributes?.recipesInfos?.find { ri -> ri.id.toString() == recipeId })?.guests
                ?: 4
        }
        return recipe.attributes!!.numberOfGuests ?: 4
    }

    private fun toggleLike(){
        // TODO : make it loading and manage it on success with invokeOnCompletion
        setState { copy(isLiked =  !currentState.isLiked) }
        val currentRecipe = this.recipe
        launch(coroutineHandler){
            setRecipe(recipeRepositoryImp.toggleLike(currentRecipe))
        }
    }

    private fun displayPrice() {
        if (currentState.isPriceDisplayed || !currentState.isInViewport) return
        setState { copy(isPriceDisplayed = true) }
    }

    private fun unBindRecipe() {
        setState { defaultState() }
        this.isInit = false
    }
}