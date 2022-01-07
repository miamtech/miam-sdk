package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Recipe

import com.miam.kmm_miam_sdk.network.service.IngredientService
import com.miam.kmm_miam_sdk.network.service.RecipeServiceOld
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MiamService (
    private val recipeServiceOld: RecipeServiceOld,
    private val ingredientService: IngredientService
    )
    {

        private val recipeService = RecipeService.create()

        fun getRecipe(id:Int): Flow<Recipe> = flow {
            emit(recipeService.getRecipeById())
        }



            // com.miam.kmm_miam_sdk.miam_core.service.RecipeServiceImpl().getRecipeById()
        //emit(RecipeConverter.DAOtoDTO(recipeServiceOld.get(id),ingredientService ))


}