package com.miam.kmmMiamCore.component.recipeListPage

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Recipe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class RecipeListPageViewModel :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<RecipeListPageContract.Event, RecipeListPageContract.State, RecipeListPageContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in recipe list view $exception")
    }

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

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
            is RecipeListPageContract.Event.InitPage -> initPage(event.title, event.filter)
        }
    }


    private fun loadPage() {
        if (currentState.noMoreData) return

        val currentPage = this.currentState.currentPage
        val newRecipes: MutableList<Recipe> = this.getCurrentRecipes().toMutableList()
        var noMoreData = true
        launch(coroutineHandler) {
            setState { copy(isFetchingNewPage = true) }
            val fetchedRecipes = recipeRepositoryImp.getRecipesFromStringFilter(
                currentState.filter,
                RecipeRepositoryImp.DEFAULT_INCLUDED,
                RecipeRepositoryImp.DEFAULT_PAGESIZE,
                currentPage
            )
            newRecipes.addAll(fetchedRecipes)
            val uiState =
                if (newRecipes.isEmpty() && fetchedRecipes.isEmpty()) BasicUiState.Empty else BasicUiState.Success(
                    newRecipes
                )
            setState {
                copy(
                    recipes = uiState,
                    noMoreData = noMoreData,
                    currentPage = currentPage + 1,
                    isFetchingNewPage = false
                )
            }
            noMoreData = fetchedRecipes.size < RecipeRepositoryImp.DEFAULT_PAGESIZE
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

    private fun initPage(title: String, filter: String) {
        setState {
            copy(
                title = title,
                filter = filter,
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