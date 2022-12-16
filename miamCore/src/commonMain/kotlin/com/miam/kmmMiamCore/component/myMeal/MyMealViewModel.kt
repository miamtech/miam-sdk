package com.miam.kmmMiamCore.component.myMeal

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.BasketEffect
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.GroceriesListAction
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.handler.LogHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.inject


open class MyMealViewModel: BaseViewModel<MyMealContract.Event, MyMealContract.State, MyMealContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error(" [ERROR][Miam][MyMeal] $exception")
    }

    private val basketStore: BasketStore by inject()
    private val groceriesListStore: GroceriesListStore by inject()

    override fun createInitialState(): MyMealContract.State =
        MyMealContract.State(
            lines = BasicUiState.Loading,
            bpls = null,
            job = null
        )

    init {
        val bp = basketStore.observeState().value.basketPreview
        if (bp != null) {
            setState {
                copy(
                    lines = if (bp.isEmpty()) BasicUiState.Empty else BasicUiState.Success(
                        bp
                    ), bpls = bp
                )
            }
        }
        val job = launch(coroutineHandler) {
            basketStore.observeSideEffect()
                .filter { basketEffect -> basketEffect == BasketEffect.BasketPreviewChange }
                .collect {
                    val bpls = basketStore.observeState().first {
                        it.basketPreview != null
                    }.basketPreview!!
                    setState {
                        copy(
                            lines = if (bpls.isEmpty()) BasicUiState.Empty else BasicUiState.Success(
                                bpls
                            ), bpls = bpls
                        )
                    }
                }
        }
        setState { copy(job = job) }
    }

    override fun handleEvent(event: MyMealContract.Event) {
        when (event) {
            is MyMealContract.Event.RemoveRecipe -> removeRecipe(event.recipeId)
        }
    }

    private fun removeRecipe(recipeId: String) {
        val newBPL = currentState.bpls?.filter {
            it.id.toString() != recipeId
        } ?: emptyList()
        setState { copy(lines = BasicUiState.Success(newBPL), bpls = newBPL) }
        // TODO handle call back and error
        groceriesListStore.dispatch(GroceriesListAction.RemoveRecipe(recipeId = recipeId))
    }
}