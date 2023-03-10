package com.miam.kmmMiamCore.component.recipe

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.GroceriesListAction
import com.miam.kmmMiamCore.base.mvi.GroceriesListEffect
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.base.mvi.UserStore
import com.miam.kmmMiamCore.component.preferences.PreferencesEffect
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

public open class RecipeViewModel(): BaseViewModel<RecipeContract.Event, RecipeContract.State, RecipeContract.Effect>() {

    private val recipeRepositoryImp: RecipeRepositoryImp = MiamDI.recipeRepository
   

    override fun createInitialState(): RecipeContract.State = defaultState()

    private fun defaultState(): RecipeContract.State {
        return RecipeContract.State(
            TODO("Not yet implemented")
        )
    }

    public fun fetchRecipe(recipeId: String) {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: RecipeContract.Event) {
        TODO("Not yet implemented")
    }
}