package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.LikeStore
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeLike
import com.miam.kmmMiamCore.miam_core.model.RecipeRelationshipName
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

public class RecipeRepositoryImp(private val recipeDataSource: MiamAPIDatasource): RecipeRepository {
    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val likeStore: LikeStore by lazy { MiamDI.likeStore }

    public companion object {
        public val DEFAULT_INCLUDED: List<String> =
            listOf("ingredients", "recipe-steps", "recipe-provider", "recipe-status", "recipe-type", "sponsors")
        public const val DEFAULT_PAGESIZE: Int = 20
        public const val FIRST_PAGE: Int = 1
    }

    override suspend fun getRecipeNumberOfResult(filters: Map<String, String>): Int {
        return recipeDataSource.getRecipeNumberOfResult(filters)
    }

    override suspend fun getRecipeById(recipeId: String): Recipe {
        println("Miam getting recipe $recipeId")
        val recipe = recipeDataSource.getRecipeById(recipeId, RecipeRelationshipName.allRelationships())
        return addRecipeLike(recipe)
    }

    override suspend fun getRecipesByIds(recipeIds: List<String>): List<Recipe> {
        println("Miam getting recipes $recipeIds")
        val recipes = recipeDataSource.getRecipeByIds(recipeIds, RecipeRelationshipName.allRelationships())
        return addRecipeLikes(recipes)
    }

    public suspend fun getRecipes(
        filters: Map<String, String>,
        included: Array<RecipeRelationshipName>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        val recipes = recipeDataSource.getRecipes(filters, included, pageSize, pageNumber)
        return addRecipeLikes(recipes)
    }

    public suspend fun getRecipesFromStringFilter(
        filters: String,
        included: Array<RecipeRelationshipName>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        val recipes = recipeDataSource.getRecipesFromStringFilter(filters, included, pageSize, pageNumber)
        return addRecipeLikes(recipes)
    }

    override suspend fun getRecipeSuggestion(
        supplierId: String,
        criteria: SuggestionsCriteria
    ): Recipe {
        // TODO : WARNING what if list is empty !!??
        return getRecipeSuggestions(supplierId, criteria, 1)[0]
    }

    override suspend fun getRecipeSuggestions(
        supplierId: String,
        criteria: SuggestionsCriteria,
        size: Int
    ): List<Recipe> {
        val recipes = recipeDataSource.getRecipeSuggestions(
            supplierId,
            size,
            criteria,
            RecipeRelationshipName.allRelationships()
        )
        return recipes.map { addRecipeLike(it) }
    }

    private suspend fun addRecipeLikes(recipeList: List<Recipe>): List<Recipe> {
        val recipeLikes = likeStore.fetchAndGetRecipeLikes(recipeList.map { recipe -> recipe.id })
        return recipeList.map { recipe ->
            findAndAddLike(recipe, recipeLikes)
        }
    }

    private suspend fun addRecipeLike(recipe: Recipe): Recipe {
        val recipeLikes = likeStore.fetchAndGetRecipeLikes(listOf(recipe.id))
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
}