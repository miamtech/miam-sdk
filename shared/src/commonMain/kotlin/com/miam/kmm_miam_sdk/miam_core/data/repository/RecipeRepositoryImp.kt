package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.data.datasource.RecipeDataSource
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImp(
    private val recipeDataSource: MiamAPIDatasource
): RecipeRepository {

    override fun getRecipeById(recipeId: Int): Flow<Recipe> = flow{
        val recipe = recipeDataSource.getRecipeById(recipeId)
        val ingredients = recipeDataSource.getIngredient(recipe.id)
        val steps = recipeDataSource.getStep(recipeId)
        val provider = recipeDataSource.getProvider(recipeId)
        //val sponsors = recipeDataSource.getSponsor(recipeId)
        val status = recipeDataSource.getStatus(recipeId)
        val type = recipeDataSource.getType(recipeId)
        ingredients.also { recipe.attributes.ingredients = it }
        steps.also { recipe.attributes.steps = it }
        provider.also { recipe.attributes.provider = it }
        //sponsors.also { recipe.attributes.sponsor = it }
        status.also { recipe.attributes.status = it }
        type.also { recipe.attributes.type = it }
        emit(recipe)
    }
}