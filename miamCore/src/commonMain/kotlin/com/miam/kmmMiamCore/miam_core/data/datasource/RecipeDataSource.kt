package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeLike
import com.miam.kmmMiamCore.miam_core.model.RecipeRelationshipName
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

public enum class RecipeFilter(public val filterName: String) {
    PACKAGES("packages"),
    DIFFICULTY("difficulty"),
    COST("computed_cost"),
    TOTAL_TIME("total-time"),
    SEARCH("search"),
    LIKED("liked"),
    ACTIVE("active"),
    INCLUDE_TAGS("include-tags"),
    EXCLUDE_TAGS("exclude-tags")
}

public interface RecipeDataSource {
    public suspend fun getRecipeById(id: String, included: Array<RecipeRelationshipName> = arrayOf()): Recipe
    public suspend fun getRecipeByIds(
        recipesIds: List<String>,
        included: Array<RecipeRelationshipName> = arrayOf(),
        pageSize: Int = 20
    ): List<Recipe>

    public suspend fun getRecipes(
        filters: Map<String, String>,
        included: Array<RecipeRelationshipName>,
        pageSize: Int = 20,
        pageNumber: Int = 1
    ): List<Recipe>

    public suspend fun getRecipesFromStringFilter(
        filters: String,
        included: Array<RecipeRelationshipName>,
        pageSize: Int = 20,
        pageNumber: Int = 1
    ): List<Recipe>

    public suspend fun getRecipeSuggestions(
        supplierId: String,
        size: Int? = 1,
        criteria: SuggestionsCriteria,
        included: Array<RecipeRelationshipName> = arrayOf()
    ): List<Recipe>

    public suspend fun getRecipeLikes(recipesIds: List<String>, pageSize: Int = 20): List<RecipeLike>
    public suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike
    public suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike
    public suspend fun getRecipeNumberOfResult(filters: Map<String, String>): Int
}