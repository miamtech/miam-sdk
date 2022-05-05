package com.miam.kmm_miam_sdk.component.favoritePage

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState


open class FavoritePageViewModel :
    BaseViewModel< FavoritePageContract.Event, FavoritePageContract.State,  FavoritePageContract.Effect>() {

    override fun createInitialState(): FavoritePageContract.State =
        FavoritePageContract.State(
            favoritesRecipes= BasicUiState.Loading,
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
        //TODO alexis fetch favorite recipe
        if(currentState.noMoreData) return
        if (currentState.currentPage == 1){
            // fetch
            // if(fetchReturn.isEmpty){
            // setState { copy(favoritesRecipes= BasicUiState.Empty, noMoreData = true) }
            // } else {
            // setState { copy(favoritesRecipes= BasicUiState.Success(
            //     fetchResult
            //      ), currentPage = currentState.currentPage + 1 }
            //  }
        }

        // if(fetchReturn.isEmpty || fetchReturn.size < 20){
        // setState { copy(favoritesRecipes= BasicUiState.Success(
        //      concat previews + fetch result
        //      ), noMoreData = true) }
        // }
        // setState { copy(favoritesRecipes= BasicUiState.Success(
        //      concat previews + fetch result
        //      ), currentPage = currentState.currentPage + 1)
    }


    private suspend  fun fetchFavoriteRecipe(){
        //TODO Alexis

    }
 }