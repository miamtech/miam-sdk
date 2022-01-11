package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Ingredients
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

interface RecipeDataSource {

    suspend fun getIngredient(entityId: Int): Ingredients
    suspend fun getRecipeById(id: Int): Recipe
}