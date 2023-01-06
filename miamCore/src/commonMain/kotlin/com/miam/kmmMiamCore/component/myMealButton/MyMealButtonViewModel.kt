package com.miam.kmmMiamCore.component.myMealButton

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.GroceriesListEffect
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.handler.LogHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class MyMealButtonViewModel : BaseViewModel<MyMealButtonContract.Event, MyMealButtonContract.State, MyMealButtonContract.Effect>(), KoinComponent {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error(" [ERROR][Miam][MyMeal] $exception")
    }

    private val grocerieListStore: GroceriesListStore by inject()

    override fun createInitialState(): MyMealButtonContract.State = MyMealButtonContract.State(recipeCount = BasicUiState.Empty)
    override fun handleEvent(event: MyMealButtonContract.Event) {
        TODO("Not yet implemented")
    }

    init {
        updateRecipeCount(grocerieListStore.getGroceriesList()?.recipes?.size ?: 0)

        launch(coroutineHandler) {
            grocerieListStore.observeSideEffect()
                .filter { it is GroceriesListEffect.RecipeCountChanged }
                .map { it as GroceriesListEffect.RecipeCountChanged }
                .collect { glEffect -> updateRecipeCount(glEffect.newRecipeCount) }
        }
    }

    private fun updateRecipeCount(currentRecipeCount: Int) {
        setState { copy(recipeCount = if (currentRecipeCount == 0) BasicUiState.Empty else BasicUiState.Success(currentRecipeCount)) }
    }
}