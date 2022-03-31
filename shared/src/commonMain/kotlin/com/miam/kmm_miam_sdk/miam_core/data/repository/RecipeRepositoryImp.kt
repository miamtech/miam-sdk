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

    override suspend fun getRecipeById(recipeId: String): Recipe {
        println("Miam getting recipe $recipeId")
        return recipeDataSource.getRecipeById(recipeId, listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type"))
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria
    ): Recipe {
        val recipes = recipeDataSource.getRecipeSuggestions(supplierId, criteria, listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type"))
        return recipes[0]
    }
}