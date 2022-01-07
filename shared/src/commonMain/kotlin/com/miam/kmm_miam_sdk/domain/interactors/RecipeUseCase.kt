package com.miam.kmm_miam_sdk.domain.interactors

import com.miam.kmm_miam_sdk.domain.interactors.type.UseCaseInOut
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.service.MiamService


import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(
    private val miamService: MiamService
): UseCaseInOut<Int, Recipe> {
    override fun execute(param: Int): Flow<Recipe> = miamService.getRecipe(param)
}