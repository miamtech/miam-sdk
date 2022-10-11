package com.miam.kmmMiamCore.component.recipe

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.GroceriesListAction
import com.miam.kmmMiamCore.base.mvi.GroceriesListEffect
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.base.mvi.UserStore
import com.miam.kmmMiamCore.component.router.RouterOutletViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria
import com.miam.kmmMiamCore.services.Analytics
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.math.max
import kotlin.math.min

open class RecipeViewModel(val routerVM: RouterOutletViewModel) :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<RecipeContract.Event, RecipeContract.State, RecipeContract.Effect>() {

    private val MAX_GUESTS = 100
    private val MIN_GUESTS = 1

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in recipe view $exception ${exception.stackTraceToString()}")
        // TODO alex break in ios mutability
        //setEvent(RecipeContract.Event.Error)
    }

    private val groceriesListStore: GroceriesListStore by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()
    private val userStore: UserStore by inject()
    private val analyticsService: Analytics by inject()

    private val guestSubject: MutableSharedFlow<Int> = MutableSharedFlow()

    private val recipe: Recipe?
        get() = this.currentState.recipe

    val recipeId: String?
        get() = recipe?.id

    override fun createInitialState(): RecipeContract.State = defaultState()

    private fun defaultState(): RecipeContract.State {
        return RecipeContract.State(
            recipeState = BasicUiState.Loading,
            recipe = null,
            headerText = "",
            guest = 4,
            isInCart = false,
            analyticsEventSent = false,
            isPriceDisplayed = false,
            isInViewport = false,
            tabState = TabEnum.INGREDIENT,
            activeStep = 0,
            recipeLoaded = false,
            isLiked = false,
            likeIsEnable = true
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
        setState { copy(likeIsEnable = userStore.state.value.likeIsEnable) }
    }

    override fun handleEvent(event: RecipeContract.Event) {
        when (event) {
            is RecipeContract.Event.SetActiveStep -> setActiveSteps(event.stepIndex)
            RecipeContract.Event.OnAddRecipe -> addOrAlterRecipe()
            RecipeContract.Event.ShowIngredient -> setTab(TabEnum.INGREDIENT)
            RecipeContract.Event.ShowSteps -> setTab(TabEnum.STEP)
            RecipeContract.Event.OnToggleLike -> toggleLike()
            RecipeContract.Event.Error -> setState { copy(recipeState = BasicUiState.Empty) }
        }
    }

    fun goToDetail() {
        routerVM.goToDetail(this)
    }

    private fun handleGLChange(gl: GroceriesListEffect) {
        when (gl) {
            is GroceriesListEffect.GroceriesListLoaded -> {
                setState { currentState.refreshFromGl(groceriesListStore) }
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
        guestSubject.debounce(500).collect { boundedGuests ->
            if (currentState.guest != boundedGuests) {
                setState { copy(guest = boundedGuests) }
                if (currentState.isInCart) {
                    addOrAlterRecipe()
                }
            }
        }
    }

    fun increaseGuest() {
        updateGuest(currentState.guest + 1)
    }

    fun decreaseGuest() {
        updateGuest(currentState.guest - 1)
    }

    fun updateGuest(nbGuest: Int) {
        // reduce guest between min and max
        var boundedGuests = max(MIN_GUESTS, nbGuest)
        boundedGuests = min(MAX_GUESTS, boundedGuests)
        launch(coroutineHandler) { guestSubject.emit(boundedGuests) }
    }

    private fun addOrAlterRecipe(): Job {
        val action = GroceriesListAction.AlterRecipeList(recipe!!.id, uiState.value.guest)
        val job = groceriesListStore.dispatch(action)
        setState { copy(isInCart = true) }
        return job
    }

    private fun setTab(newTab: TabEnum) {
        if (uiState.value.tabState == newTab) return
        setState { copy(tabState = newTab) }
    }

    private fun setActiveSteps(newActiveStep: Int) {
        if (uiState.value.activeStep == newActiveStep) return
        setState { copy(activeStep = newActiveStep) }
    }

    fun fetchRecipe(recipeId: String) {
        setState { copy(recipeState = BasicUiState.Loading) }
        launch(coroutineHandler) {
            setRecipe(recipeRepositoryImp.getRecipeById(recipeId))
        }
    }

    fun setRecipeFromSuggestion(criteria: SuggestionsCriteria) {
        LogHandler.info("[Miam][setRecipeFromSuggestion] ${criteria.shelfIngredientsIds?.toString()}")
        setState { copy(recipeState = BasicUiState.Loading) }
        launch(coroutineHandler) {
            pointOfSaleStore.observeState().value.idSupplier?.let { supplierId ->
                setRecipe(recipeRepositoryImp.getRecipeSuggestions(supplierId, criteria))
            }
        }
    }

    fun setRecipe(recipe: Recipe) {
        // TODO : path + multiple sent ?
        analyticsService.sendEvent(
            Analytics.EVENT_RECIPE_SHOW,
            "",
            Analytics.PlausibleProps(recipe_id = recipe.id)
        )
        setState {
            copy(
                recipeState = BasicUiState.Success(recipe),
                recipe = recipe,
                recipeLoaded = true,
                isLiked = recipe.recipeLike?.attributes?.isPast == false
            ).refreshFromGl(groceriesListStore)
        }
        displayPrice()
    }

    fun unsetRecipe() {
        val defaultState = defaultState()
        setState {
            copy(
                recipeState = defaultState.recipeState,
                recipe = defaultState.recipe,
                recipeLoaded = defaultState.recipeLoaded,
                isLiked = defaultState.isLiked,
                isInCart = defaultState.isInCart,
                guest = defaultState.guest
            )
        }
    }

    private fun toggleLike() {
        // TODO : make it loading and manage it on success with invokeOnCompletion
        setState { copy(isLiked = !currentState.isLiked) }
        val currentRecipe = this.recipe
        launch(coroutineHandler) {
            setRecipe(recipeRepositoryImp.toggleLike(currentRecipe!!))
        }
    }

    private fun displayPrice() {
        if (currentState.isPriceDisplayed || !currentState.isInViewport) return
        setState { copy(isPriceDisplayed = true) }
    }

    fun realQuantities(quantity: String, currentGuest: Int, recipeGuest: Int): String {
        return (quantity.toFloat() * currentGuest / recipeGuest).toString()
    }


    fun readableFloatNumber(value: String, unit: String?): String {
        if (isUnitCountless(unit)) {
            return unit.toString()
        }

        val valueToNumber = value.toFloat()
        var unitToDisplay = if (unit != null && unit.isNotEmpty()) unit else ""
        if (valueToNumber < 1) {
            unitToDisplay = singularize(unit.toString())
            return frac(valueToNumber).plus(" ").plus(unitToDisplay)
        } else if (valueToNumber.equals(1)) {
            unitToDisplay = singularize(unitToDisplay)
        } else if (valueToNumber > 1) {
            unitToDisplay = pluralize(unitToDisplay)
        }

        if (isInteger(valueToNumber)) {
            return valueToNumber.toInt().toString().plus(" ").plus(unitToDisplay)
        }

        return valueToNumber.toString().plus(" ").plus(unitToDisplay)
    }

    fun frac(value: Float, maxdenominator: Int = 10): String {
        var num1 = 0f
        var den1 = 1f
        var num2 = 1f
        var den2 = 1f
        while (den1 <= maxdenominator && den2 <= maxdenominator) {
            val mediant = (num1 + num2) / (den1 + den2)
            if (value == mediant) {
                if (den1 + den2 <= maxdenominator) {
                    return render_frac(value, num1 + num2, den1 + den2)
                } else if (den2 > den1) {
                    return render_frac(value, num2, den2)
                } else {
                    return render_frac(value, num1, den1)
                }
            } else if (value > mediant) {
                num1 = num1 + num2
                den1 = den1 + den2
            } else {
                num2 = num1 + num2
                den2 = den1 + den2
            }
        }
        if (den1 > maxdenominator) {
            return render_frac(value, num2, den2)
        } else {
            return render_frac(value, num1, den1)
        }
    }

    fun render_frac(original_value: Float, num: Float, denom: Float): String {
        if (num == 0f) {
            return original_value.toString()

        }
        return num.toInt().toString().plus('/').plus(denom.toInt())
    }

    fun pluralize(unit: String): String {
        if (unit.isEmpty() || unit.length <= 3) {
            return unit
        }

        val unitArray = unit.split(' ').toMutableList()
        unitArray[0] = unitArray[0].replace("/(. * [^s])s*$/", "$1s")
        return unitArray.joinToString(" ")
    }

    fun singularize(unit: String): String {
        if (unit.isEmpty() || unit.length <= 3) {
            return unit
        }
        val unitArray = unit.split(' ').toMutableList()

        unitArray[0] = unitArray[0].replace("/(. * [^s])s*$/", "$1")
        return unitArray.joinToString(" ")
    }

    private fun isUnitCountless(unit: String?): Boolean {
        val arr = arrayOf("un peu", "un petit peu")
        return arr.contains(unit)
    }

    private fun isInteger(N: Float): Boolean {
        // TODO : check return N.toInt().toFloat() == N
        val intPart = N.toInt()
        val decimalPart = N - intPart

        return decimalPart <= 0
    }
}