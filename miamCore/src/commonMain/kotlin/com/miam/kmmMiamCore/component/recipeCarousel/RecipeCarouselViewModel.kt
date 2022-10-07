package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject


class RecipeCarouselViewModel :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<RecipeCarouselContract.Event, RecipeCarouselContract.State, RecipeCarouselContract.Effect>() {

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()


    override fun createInitialState(): RecipeCarouselContract.State = RecipeCarouselContract.State(
        suggestions = BasicUiState.Loading,
    )

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in recipe carousel $exception ${exception.stackTraceToString()}")
    }

    override fun handleEvent(event: RecipeCarouselContract.Event) {
        when (event) {
            is RecipeCarouselContract.Event.GetSuggestionsFromIdAndSize -> getSuggestionsCriterias(
                event.productId,
                event.numberOfResult
            )

            is RecipeCarouselContract.Event.GetSuggestionFromId -> getSuggestionsCriterias(
                event.productId, 4
            )
        }
    }

    private fun getSuggestionsCriterias(productId: String, numberOfResult: Int) {
        setState { copy(suggestions = BasicUiState.Loading) }
        val criteria = SuggestionsCriteria(currentIngredientsIds = listOf(productId))
        launch(coroutineHandler) {
            val recipes = pointOfSaleStore.observeState().value.idSupplier?.let { recipeRepositoryImp.getRecipeSuggestions(it, criteria, numberOfResult) } ?: listOf()
            setState {
                copy(
                    suggestions = if (recipes.isEmpty()) BasicUiState.Empty else BasicUiState.Success(recipes),
                )
            }
        }
    }
}
