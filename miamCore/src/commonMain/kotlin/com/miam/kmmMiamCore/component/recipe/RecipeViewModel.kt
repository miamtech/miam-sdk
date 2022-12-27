package com.miam.kmmMiamCore.component.recipe

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.math.max
import kotlin.math.min

open class RecipeViewModel(val routerVM: RouterOutletViewModel): BaseViewModel<RecipeContract.Event, RecipeContract.State, RecipeContract.Effect>() {

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
            likeIsEnable = true
        )
    }

    init {
        launch(coroutineHandler) {
            groceriesListStore.observeSideEffect().collect {
                handleGLChange(it)
            }
        }
        CoroutineScope(Dispatchers.Default).launch(coroutineHandler) {
            listenGuestSubjectChanges()
        }
        setState { copy(likeIsEnable = userStore.state.value.likeIsEnable) }
    }

    override fun handleEvent(event: RecipeContract.Event) {
        when (event) {
            is RecipeContract.Event.SetActiveStep -> setActiveSteps(event.stepIndex)
            RecipeContract.Event.OnAddRecipe -> addOrAlterRecipe()
            RecipeContract.Event.ShowIngredient -> setTab(TabEnum.INGREDIENT)
            RecipeContract.Event.ShowSteps -> setTab(TabEnum.STEP)
            RecipeContract.Event.Error -> setState { copy(recipeState = BasicUiState.Empty) }
        }
    }

    fun goToDetail() {
        routerVM.goToDetail(this)
    }

    private fun handleGLChange(gl: GroceriesListEffect) {
        when (gl) {
            is GroceriesListEffect.GroceriesListLoaded -> setState { currentState.refreshFromGl(groceriesListStore) }
            is GroceriesListEffect.RecipeAdded -> if (gl.recipeId == recipeId) setState { copy(isInCart = true, guest = gl.guests) }
            is GroceriesListEffect.RecipeRemoved -> if (gl.recipeId == recipeId) setState { copy(isInCart = false) }
            is GroceriesListEffect.RecipeCountChanged -> {}
        }
    }

    private suspend fun listenGuestSubjectChanges() {
        guestSubject.debounce(500).collect { boundedGuests ->
            if (currentState.guest != boundedGuests) {
                setState { copy(guest = boundedGuests) }
                if (currentState.isInCart) {
                    addOrAlterRecipe()
                }
            }
        }
    }

    fun updateGuest(nbGuest: Int) {
        // reduce guest between min and max
        var boundedGuests = max(MIN_GUESTS, nbGuest)
        boundedGuests = min(MAX_GUESTS, boundedGuests)
        launch(coroutineHandler) { guestSubject.emit(boundedGuests) }
    }

    private fun addOrAlterRecipe(): Job {
        val action = GroceriesListAction.AlterRecipeList(recipe!!.id, uiState.value.guest)
        setState { copy(guestUpdating = true) }
        val job = groceriesListStore.dispatch(action)
        job.invokeOnCompletion { setState { copy(guestUpdating = false) } }
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
                setRecipe(recipeRepositoryImp.getRecipeSuggestion(supplierId, criteria))
            }
        }
    }

    fun setRecipe(recipe: Recipe) {
        // TODO : path + on view displayed ?
        if (!currentState.show_event_sent) {
            analyticsService.sendEvent(
                Analytics.EVENT_RECIPE_SHOW,
                "",
                Analytics.PlausibleProps(recipe_id = recipe.id)
            )
            setState { copy(show_event_sent = true) }
        }

        setState {
            copy(
                recipeState = BasicUiState.Success(recipe),
                recipe = recipe,
                recipeLoaded = true,
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
                isInCart = defaultState.isInCart,
                guest = defaultState.guest
            )
        }
    }

    private fun displayPrice() {
        if (currentState.isPriceDisplayed || !currentState.isInViewport) return
        setState { copy(isPriceDisplayed = true) }
    }
}