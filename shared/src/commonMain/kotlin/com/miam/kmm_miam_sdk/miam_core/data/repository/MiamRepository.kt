package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.RecipeDataSource
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MiamRepository(
    private val recipeDataSource: RecipeDataSource
): RecipeRepository {

    override fun getRecipeById(recipeId: Int): Flow<Recipe> = flow{
        val recipe = recipeDataSource.getRecipeById(recipeId)
        val ingredients = recipeDataSource.getIngredient(recipe.id)
        ingredients.also { recipe.attributes.ingredients = it }
        emit(recipe)
    }
}