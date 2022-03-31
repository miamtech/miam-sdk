package com.miam.kmm_miam_sdk.domain.interactors

import com.miam.kmm_miam_sdk.base.mvi.GroceriesListAction

import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import com.miam.kmm_miam_sdk.domain.interactors.type.UseCaseIn
import com.miam.kmm_miam_sdk.domain.interactors.type.UseCaseInOut

import com.miam.kmm_miam_sdk.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Recipe


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipeUseCase(
    private val miamRepository: RecipeRepositoryImp
): UseCaseInOut<String, Recipe> {
    override suspend fun execute(param: String): Recipe = miamRepository.getRecipeById(param)
}

class AddRecipeUseCase () : UseCaseIn<Recipe>, KoinComponent {
    private val groceriesListStore : GroceriesListStore by inject()
    override suspend fun execute(recipe: Recipe) {
            groceriesListStore.dispatch(
                GroceriesListAction.AlterRecipeList(
                    recipe.id , recipe.attributes!!.numberOfGuests ?: 4)
            )
    }
}

