package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.data.datasource.RecipeDataSource
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.RecipeLike
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImp(
    private val recipeDataSource: MiamAPIDatasource
): RecipeRepository {

    companion object {
        val DEFAULT_INCLUDED = listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type")
        val DEFAULT_PAGESIZE = 20
    }

    override suspend fun getRecipeById(recipeId: String): Recipe {
        println("Miam getting recipe $recipeId")
        return recipeDataSource.getRecipeById(recipeId, DEFAULT_INCLUDED)
    }

    suspend fun getRecipes(filters: Map<String, String>, included: List<String>, pageSize: Int, pageNumber: Int): List<Recipe> {
        return recipeDataSource.getRecipes(filters, included, pageSize, pageNumber)
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria
    ): Recipe {
        val recipes = recipeDataSource.getRecipeSuggestions(supplierId, criteria, listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type"))
        return recipes[0]
    }

    suspend fun addRecipeLikes(recipeList: List<Recipe>): List<Recipe> {
        val recipeLikes = recipeDataSource.getRecipeLikes(recipeList.map { recipe -> recipe.id })
        return recipeList.map { recipe ->
            findAndAddLike(recipe, recipeLikes)
        }
    }

    suspend fun addRecipeLike(recipe: Recipe): Recipe {
        val recipeLikes = recipeDataSource.getRecipeLikes(listOf(recipe.id))
        return findAndAddLike(recipe, recipeLikes)
    }

    private fun findAndAddLike(recipe: Recipe, recipeLikes: List<RecipeLike>): Recipe {
        var recipeToReturn = recipe
        val recipeLikesFiltered = recipeLikes.filter { like -> like.attributes!!.recipeId.toString() == recipe.id }
        if (recipeLikesFiltered.size == 1) {
            val recipeLike = recipeLikesFiltered[0]
            recipeToReturn = recipe.copy(recipeLike = recipeLike)
        }
        return recipeToReturn
    }

    suspend fun toggleLike(recipe: Recipe): Recipe {
        if (recipe.recipeLike != null) {
            val currentLike = recipe.recipeLike
            val currentAttributes = currentLike.attributes!!
            val newLike = recipeDataSource.updateRecipeLike(currentLike.copy(attributes = currentAttributes.copy(isPast = !currentAttributes.isPast)))
            return recipe.copy(recipeLike = newLike)
        }
        val newLike = RecipeLike.createDefault(recipe.id)
        val createdLike = recipeDataSource.createRecipeLike(newLike)
        return recipe.copy(recipeLike = createdLike)
    }
}