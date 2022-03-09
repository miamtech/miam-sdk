package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.data.datasource.RecipeDataSource
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImp(
    private val recipeDataSource: MiamAPIDatasource
): RecipeRepository {

    override fun getRecipeById(recipeId: Int): Flow<Recipe> = flow{
        val recipe = recipeDataSource.getRecipeById(recipeId)
        emit(fillRecipe(recipe))
    }

    override  fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria
    ) = flow {
        val recipes = recipeDataSource.getRecipeSuggestions(supplierId, criteria)
        val recipe = recipes[0]
        emit(fillRecipe(recipe))
    }

    private suspend fun fillRecipe(recipe :Recipe ): Recipe {
        val ingredients = recipeDataSource.getIngredient(recipe.id)
        val steps = recipeDataSource.getStep(recipe.id)
        val provider = recipeDataSource.getProvider(recipe.id)
        //val sponsors = recipeDataSource.getSponsor(recipeId)
        val status = recipeDataSource.getStatus(recipe.id)
        val type = recipeDataSource.getType(recipe.id)
        ingredients.also { recipe.attributes.ingredients = it }
        steps.also { recipe.attributes.steps = it }
        provider.also { recipe.attributes.provider = it }
        //sponsors.also { recipe.attributes.sponsor = it }
        status.also { recipe.attributes.status = it }
        type.also { recipe.attributes.type = it }
        return recipe
    }
}