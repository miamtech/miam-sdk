package com.miam.kmm_miam_sdk.domain.interactors

import com.miam.kmm_miam_sdk.domain.interactors.type.UseCaseInOut
import com.miam.kmm_miam_sdk.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Recipe


import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(
    private val miamRepository: RecipeRepositoryImp
): UseCaseInOut<Int, Recipe> {
    override fun execute(param: Int): Flow<Recipe> = miamRepository.getRecipeById(param)
}