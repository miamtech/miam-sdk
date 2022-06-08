package com.miam.kmm_miam_sdk.component.tag

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

open class TagViewModel(private val vmRouter: RouterOutletViewModel) :
    BaseViewModel<TagContract.Event, TagContract.State, TagContract.Effect>() {

    private val basketStore: BasketStore by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception ->
        LogHandler.error("Miam error in recipe view $exception ${exception.stackTraceToString()}")
        //setEvent(RecipeContract.Event.Error)
    }

     fun goToDetail(recipe : Recipe){
        val vmRecipe = RecipeViewModel(vmRouter)
        vmRecipe.setEvent(RecipeContract.Event.OnSetRecipe(recipe))
        vmRouter.goToDetail(vmRecipe,false)
    }

    override fun createInitialState(): TagContract.State =
        TagContract.State(
            recipeList = BasicUiState.Loading,
        )


    override fun handleEvent(event: TagContract.Event) {
        when (event) {
         is TagContract.Event.SetRetailerProductId -> setItemExtId(event.productId)
        }
    }

    private fun setItemExtId(itemExtId: String){
        LogHandler.info("getting belonging recipes for $itemExtId")
        val recipeIds = basketStore.getBasket()?.relationships?.basketEntries?.data?.filter { be ->
            be.selectedItem?.attributes?.extId == itemExtId && be.attributes?.groceriesEntryStatus == "active"
        }?.flatMap { be ->
          be.attributes?.recipeIds?.map { it.toString() }?: listOf()
        }?: listOf()
        LogHandler.info("recipe count  ${recipeIds.size}")

        if (recipeIds.isEmpty()) {

            setState { copy(recipeList = BasicUiState.Empty) }
            return
        }

        var recipes = listOf<Recipe>()
        launch(coroutineHandler) {
            recipes = recipeRepositoryImp.getRecipesByIds(recipeIds)
        }.invokeOnCompletion { error ->
            if (error == null) {
                setState { copy(recipeList = BasicUiState.Success(recipes)) }
            } else {
                setState { copy(recipeList = BasicUiState.Error("Could not fetch recipes $recipeIds")) }
            }
        }
    }

}