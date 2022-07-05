package com.miam.kmm_miam_sdk.handler

import com.miam.kmm_miam_sdk.base.mvi.GroceriesListAction
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListEffect
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GroceriesListHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {
    private val groceriesListStore: GroceriesListStore by inject()

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in GroceriesListHandler $exception ${exception.stackTraceToString()}")
    }

    fun resetGroceriesList(): Job {
        return groceriesListStore.dispatch(GroceriesListAction.ResetGroceriesList)
    }

    fun getRecipeCountChangeFlow(): Flow<GroceriesListEffect.RecipeCountChanged> {
        return groceriesListStore.observeSideEffect()
            .filter { it is GroceriesListEffect.RecipeCountChanged }
            .map { it as GroceriesListEffect.RecipeCountChanged }
    }

    fun onRecipeCountChange(updateRecipeCount : (count: Int) -> Unit) {
        launch(coroutineHandler) {
            groceriesListStore.observeSideEffect()
                .filter { it is GroceriesListEffect.RecipeCountChanged }
                .map { it as GroceriesListEffect.RecipeCountChanged }
                .collect { glEffect -> updateRecipeCount(glEffect.newRecipeCount) }
        }
    }
}