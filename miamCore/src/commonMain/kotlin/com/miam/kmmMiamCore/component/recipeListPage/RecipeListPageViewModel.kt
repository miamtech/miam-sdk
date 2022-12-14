package com.miam.kmmMiamCore.component.recipeListPage

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.component.preferences.PreferencesEffect
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Recipe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class RecipeListPageViewModel:
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<RecipeListPageContract.Event, RecipeListPageContract.State, RecipeListPageContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in recipe list view $exception")
    }

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val preference: SingletonPreferencesViewModel by inject()
    private val recipeFilter: SingletonFilterViewModel by inject()

    init {
        listenPreferencesChanges()
    }

    override fun createInitialState(): RecipeListPageContract.State =
        RecipeListPageContract.State(
            recipes = BasicUiState.Loading,
            title = "",
            filter = "",
            currentPage = 1,
            isFetchingNewPage = false,
            noMoreData = false
        )

    override fun handleEvent(event: RecipeListPageContract.Event) {
        when (event) {
            is RecipeListPageContract.Event.LoadPage -> loadPage()
            is RecipeListPageContract.Event.InitPage -> initPage(event.title)
        }
    }

    private fun listenPreferencesChanges() {
        launch(coroutineHandler) {
            preference.observeSideEffect().filter { effect ->
                effect is PreferencesEffect.PreferencesLoaded || effect is PreferencesEffect.PreferencesChanged
            }.collect {
                initPage(currentState.title)
            }
        }
    }

    fun canLoad(): Boolean {
        return !currentState.isFetchingNewPage && !currentState.noMoreData
    }

    private fun loadPage() {
        if (!canLoad()) return
        setState { copy(isFetchingNewPage = true) }
        val currentPage = this.currentState.currentPage
        val newRecipes: MutableList<Recipe> = this.getCurrentRecipes().toMutableList()
        launch(coroutineHandler) {
            val fetchedRecipes = recipeRepositoryImp.getRecipesFromStringFilter(
                currentState.filter + preference.getPreferencesAsQueryString(),
                RecipeRepositoryImp.DEFAULT_INCLUDED,
                RecipeRepositoryImp.DEFAULT_PAGESIZE,
                currentPage
            )
            newRecipes.addAll(fetchedRecipes)
            val uiState = if (newRecipes.isEmpty()) BasicUiState.Empty else BasicUiState.Success(newRecipes)
            setState {
                copy(
                    recipes = uiState,
                    noMoreData = fetchedRecipes.size < RecipeRepositoryImp.DEFAULT_PAGESIZE,
                    currentPage = currentPage + 1,
                    isFetchingNewPage = false
                )
            }
        }.invokeOnCompletion { error ->
            if (error != null) {
                LogHandler.error("category loadPage is in error")
                setState {
                    copy(
                        recipes = BasicUiState.Error("Error while getting recipe list pages"),
                        isFetchingNewPage = false
                    )
                }
            }
        }
    }

    private fun initPage(title: String) {
        setState {
            copy(
                title = title,
                filter = recipeFilter.getSelectedFilterAsQueryString() + preference.getPreferencesAsQueryString(),
                recipes = BasicUiState.Loading,
                noMoreData = false,
                currentPage = 1
            )
        }
        loadPage()
    }

    private fun getCurrentRecipes(): List<Recipe> {
        if (this.currentState.recipes is BasicUiState.Success) {
            val recipes = this.currentState.recipes as BasicUiState.Success
            return recipes.data
        }
        return listOf()
    }
}