package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.base.mvi.GroceriesListAction
import com.miam.kmmMiamCore.base.mvi.GroceriesListEffect
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public object GroceriesListHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {
    private val groceriesListStore: GroceriesListStore by inject()

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in GroceriesListHandler $exception ${exception.stackTraceToString()}")
    }

    public fun resetGroceriesList(): Job {
        return groceriesListStore.dispatch(GroceriesListAction.ResetGroceriesList)
    }

    public fun getRecipeCountChangeFlow(): Flow<GroceriesListEffect.RecipeCountChanged> {
        return groceriesListStore.observeSideEffect()
            .filter { it is GroceriesListEffect.RecipeCountChanged }
            .map { it as GroceriesListEffect.RecipeCountChanged }
    }

    public fun onRecipeCountChange(updateRecipeCount: (count: Int) -> Unit) {
        updateRecipeCount(groceriesListStore.getGroceriesList()?.recipes?.size ?: 0)
        launch(coroutineHandler) {
            groceriesListStore.observeSideEffect()
                .filter { it is GroceriesListEffect.RecipeCountChanged }
                .map { it as GroceriesListEffect.RecipeCountChanged }
                .collect { glEffect -> updateRecipeCount(glEffect.newRecipeCount) }
        }
    }
}