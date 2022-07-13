package com.miam.kmm_miam_sdk.component.basketTag

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterOutletViewModel
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class BasketTagViewModel(private val vmRouter: RouterOutletViewModel) :
    BaseViewModel<BasketTagContract.Event, BasketTagContract.State, BasketTagContract.Effect>() {

    private val basketStore: BasketStore by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception ->
        LogHandler.error("Miam error in Tag view $exception ${exception.stackTraceToString()}")
    }

     open fun goToDetail(recipe : Recipe){
         val vmRecipe = RecipeViewModel(vmRouter)
         LogHandler.info("goToDetail vmRecipe : $vmRecipe")
         vmRecipe.setEvent(RecipeContract.Event.OnSetRecipe(recipe))
         LogHandler.info("goToDetail setEvent :")
         vmRouter.goToDetail(vmRecipe,false)
    }

    override fun createInitialState(): BasketTagContract.State =
        BasketTagContract.State(
            recipeList = BasicUiState.Loading,
        )


    override fun handleEvent(event: BasketTagContract.Event) {
        when (event) {
         is BasketTagContract.Event.SetRetailerProductId -> setItemExtId(event.productId)
        }
    }

    private fun setItemExtId(itemExtId: String){
        LogHandler.info("getting belonging recipes for $itemExtId")
        LogHandler.info("getting belonging recipes : ${basketStore.getBasket()?.relationships?.basketEntries?.data} ")
        val recipeIds = basketStore.getBasket()?.relationships?.basketEntries?.data?.filter { be ->
            be.selectedItem?.attributes?.extId == itemExtId && be.attributes?.groceriesEntryStatus == "active"
        }?.flatMap { be ->
          be.attributes?.recipeIds?.map { it.toString() }?: listOf()
        }?: listOf()

        if (recipeIds.isEmpty()) {
            setState { copy(recipeList = BasicUiState.Empty) }
            return
        }

        launch(coroutineHandler) {
            val recipes = recipeRepositoryImp.getRecipesByIds(recipeIds)
            setState { copy(recipeList = BasicUiState.Success(recipes)) }
            LogHandler.info("getting belonging recipes : Success ")
        }.invokeOnCompletion { error ->
            if (error != null) {
                setState { copy(recipeList = BasicUiState.Error("Could not fetch recipes $recipeIds")) }
            }
        }
    }

}