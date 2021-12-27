package com.example.kmm_miam_sdk.repository

import com.example.kmm_miam_sdk.network.model.Recipe
import com.example.kmm_miam_sdk.network.service.RecipeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val recipeService: RecipeService,
)  {

     fun getRecipe(id:Int): Flow<Recipe> = flow {
        emit(recipeService.get(id))
    }

}