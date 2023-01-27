package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject


public open class RecipeCarouselViewModel : BaseViewModel<RecipeCarouselContract.Event, RecipeCarouselContract.State, RecipeCarouselContract.Effect>() {

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()

    override fun createInitialState(): RecipeCarouselContract.State = RecipeCarouselContract.State(suggestions = BasicUiState.Loading)

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in recipe carousel $exception ${exception.stackTraceToString()}")
    }

    override fun handleEvent(event: RecipeCarouselContract.Event) {
        when (event) {
            is RecipeCarouselContract.Event.GetRecipeSuggestionsFromId -> getRecipeSuggestionsFromId(event.productId, event.numberOfResult)
            is RecipeCarouselContract.Event.GetRecipeSuggestionsFromCriteria -> getRecipeSuggestionsFromCriteria(event.criteria, event.numberOfResult)
        }
    }

    private fun getRecipeSuggestionsFromId(productId: String, numberOfResult: Int) {
        setState { copy(suggestions = BasicUiState.Loading) }
        val criteria = SuggestionsCriteria(currentIngredientsIds = listOf(productId))
        getRecipeSuggestionsFromCriteria(criteria, numberOfResult)
    }

    private fun getRecipeSuggestionsFromCriteria(criteria: SuggestionsCriteria, numberOfResult: Int) {
        pointOfSaleStore.observeState().value.idSupplier?.let {
            launch(coroutineHandler) {
                val recipes = recipeRepositoryImp.getRecipeSuggestions(it, criteria, numberOfResult)
                setState { copy(suggestions = if (recipes.isEmpty()) BasicUiState.Empty else BasicUiState.Success(recipes)) }
            }
            return
        }
        setState { copy(suggestions = BasicUiState.Empty) }
    }
}
