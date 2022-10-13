package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeLike
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

class RecipeRepositoryImp(
    private val recipeDataSource: MiamAPIDatasource
) : RecipeRepository {

    companion object {
        val DEFAULT_INCLUDED =
            listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type")
        val DEFAULT_PAGESIZE = 20
    }

    override suspend fun getRecipeNumberOfResult(filter: String): Int {
        return recipeDataSource.getRecipeNumberOfResult(filter)
    }

    override suspend fun getRecipeById(recipeId: String): Recipe {
        println("Miam getting recipe $recipeId")
        val recipe = recipeDataSource.getRecipeById(recipeId, DEFAULT_INCLUDED)
        return addRecipeLike(recipe)
    }

    override suspend fun getRecipesByIds(recipeIds: List<String>): List<Recipe> {
        println("Miam getting recipes $recipeIds")
        val recipes = recipeDataSource.getRecipeByIds(recipeIds, DEFAULT_INCLUDED)
        return addRecipeLikes(recipes)
    }

    suspend fun getRecipes(
        filters: Map<String, String>,
        included: List<String>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        val recipes = recipeDataSource.getRecipes(filters, included, pageSize, pageNumber)
        return addRecipeLikes(recipes)
    }

    suspend fun getRecipesFromStringFilter(
        filters: String,
        included: List<String>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        val recipes =
            recipeDataSource.getRecipesFromStringFilter(filters, included, pageSize, pageNumber)
        return addRecipeLikes(recipes)
    }

    override suspend fun getRecipeSuggestion(
        supplierId: Int,
        criteria: SuggestionsCriteria
    ): Recipe {
        // TODO : WARNING what if list is empty !!??
        return getRecipeSuggestions(supplierId, criteria, 1)[0]
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria,
        size: Int
    ): List<Recipe> {
        val recipes = recipeDataSource.getRecipeSuggestions(
            supplierId,
            size,
            criteria,
            listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type")
        )
        return recipes.map { addRecipeLike(it) }
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
        val recipeLikesFiltered =
            recipeLikes.filter { like -> like.attributes!!.recipeId.toString() == recipe.id }
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
            val newLike = recipeDataSource.updateRecipeLike(
                currentLike.copy(
                    attributes = currentAttributes.copy(isPast = !currentAttributes.isPast)
                )
            )
            return recipe.copy(recipeLike = newLike)
        }
        val newLike = RecipeLike.createDefault(recipe.id)
        val createdLike = recipeDataSource.createRecipeLike(newLike)
        return recipe.copy(recipeLike = createdLike)
    }
}