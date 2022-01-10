package com.miam.kmm_miam_sdk.network.model.Converter

import com.miam.kmm_miam_sdk.network.model.DAO.RecipeDAO

import com.miam.kmm_miam_sdk.network.model.Recipe
import com.miam.kmm_miam_sdk.network.service.IngredientServiceOld


class RecipeConverter {

    companion object {
       suspend fun DAOtoDTO(recipeDAO: RecipeDAO, ingredientServiceOld: IngredientServiceOld) : Recipe {
            val recipe =  Recipe(recipeDAO.attributes)

          // recipe.ingredients = IngredientConverter.DAOstoDTOs(ingredientService.getWhere(Recipe.type, recipeDAO.id))

           return recipe

        }
    }


}