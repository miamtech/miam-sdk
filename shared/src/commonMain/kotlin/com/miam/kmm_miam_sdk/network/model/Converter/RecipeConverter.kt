package com.miam.kmm_miam_sdk.network.model.Converter

import com.miam.kmm_miam_sdk.network.model.DAO.RecipeDAO

import com.miam.kmm_miam_sdk.network.model.Recipe
import com.miam.kmm_miam_sdk.network.service.IngredientService


class RecipeConverter {

    companion object {
       suspend fun DAOtoDTO(recipeDAO: RecipeDAO , ingredientService: IngredientService) : Recipe {
            val recipe =  Recipe(recipeDAO.attributes)
           return recipe
        }
    }
}