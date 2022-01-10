package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Recipe

import com.miam.kmm_miam_sdk.network.service.IngredientServiceOld
import com.miam.kmm_miam_sdk.network.service.RecipeServiceOld
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MiamService (
    private val recipeServiceOld: RecipeServiceOld,
    private val ingredientServiceOld: IngredientServiceOld
    )
    {

        private val recipeService = RecipeService.create()
        private val ingredientService = IngredientService.create()

        fun getRecipe(id: Int): Flow<Recipe> = flow {
            val recipe = recipeService.getRecipeById(id)

            println(" the recipe is")
            print(recipe.id)

            val ingredients = ingredientService.getIngredient(recipe.id, "recipes")
            println(" the ingredient is")
            print(ingredients.ingredients.first())

            ingredients.also { recipe.attributes.ingredients = it }

            emit(recipe)

        }



            // com.miam.kmm_miam_sdk.miam_core.service.RecipeServiceImpl().getRecipeById()
        //emit(RecipeConverter.DAOtoDTO(recipeServiceOld.get(id),ingredientService ))


}