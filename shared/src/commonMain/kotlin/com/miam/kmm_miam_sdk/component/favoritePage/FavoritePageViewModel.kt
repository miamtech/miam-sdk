package com.miam.kmm_miam_sdk.component.favoritePage

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject


open class FavoritePageViewModel :
    BaseViewModel<FavoritePageContract.Event, FavoritePageContract.State, FavoritePageContract.Effect>() {

    companion object {
        val FILTERS = mapOf("liked" to "true", "active" to "true,false")
    }

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception ->
        println("Miam error in favorite view $exception")
    }

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    init {
        loadPage()
    }

    override fun createInitialState(): FavoritePageContract.State =
        FavoritePageContract.State(
            favoritesRecipes = BasicUiState.Loading,
            currentPage = 1,
            isFetchingNewPage = false,
            noMoreData = false
        )

    override fun handleEvent(event: FavoritePageContract.Event) {
        when (event) {
            is FavoritePageContract.Event.LoadPage -> loadPage()
        }
    }


    private fun loadPage(){
        if(currentState.noMoreData) return

        val currentPage = this.currentState.currentPage
        val newRecipes: MutableList<Recipe> = this.getCurrentRecipes().toMutableList()
        var noMoreData = true
        launch(coroutineHandler) {
            setState { copy(isFetchingNewPage = true) }
            val fetchedRecipes = recipeRepositoryImp.getRecipes(FILTERS, RecipeRepositoryImp.DEFAULT_INCLUDED, RecipeRepositoryImp.DEFAULT_PAGESIZE, currentPage)
            newRecipes.addAll(fetchedRecipes)
            noMoreData = fetchedRecipes.size < RecipeRepositoryImp.DEFAULT_PAGESIZE
        }.invokeOnCompletion { error ->
            if (error == null) {
                setState {
                    copy(
                        favoritesRecipes = BasicUiState.Success(newRecipes),
                        noMoreData = noMoreData,
                        currentPage = currentPage + 1,
                        isFetchingNewPage = false
                    )
                }
            } else {
                LogHandler.error("Favorite loadPage is in error")
                setState {copy(favoritesRecipes = BasicUiState.Error("Error while getting favorite pages"), isFetchingNewPage = false)}
            }
        }
    }

    private fun getCurrentRecipes(): List<Recipe> {
        if (this.currentState.favoritesRecipes is BasicUiState.Success) {
            val favoritesRecipes = this.currentState.favoritesRecipes as BasicUiState.Success
            return favoritesRecipes.data
        }
        return listOf()
    }
 }
