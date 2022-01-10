package com.miam.kmm_miam_sdk.repository

import com.miam.kmm_miam_sdk.network.model.Converter.RecipeConverter
import com.miam.kmm_miam_sdk.network.model.Recipe
import com.miam.kmm_miam_sdk.network.service.IngredientServiceOld
import com.miam.kmm_miam_sdk.network.service.RecipeServiceOld
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val recipeService: RecipeServiceOld,
    private val ingredientServiceOld: IngredientServiceOld,
)  {

     fun getRecipe(id:Int): Flow<Recipe> = flow {
        emit(RecipeConverter.DAOtoDTO(recipeService.get(id),ingredientServiceOld ))
    }

}