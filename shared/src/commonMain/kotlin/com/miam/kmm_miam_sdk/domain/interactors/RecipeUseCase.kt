package com.miam.kmm_miam_sdk.domain.interactors

import com.miam.kmm_miam_sdk.domain.interactors.type.UseCaseInOut

import com.miam.kmm_miam_sdk.network.model.Recipe
import com.miam.kmm_miam_sdk.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(
    private val repository: Repository
): UseCaseInOut<Int, Recipe> {
    override fun execute(param: Int): Flow<Recipe> = repository.getRecipe(param)
}