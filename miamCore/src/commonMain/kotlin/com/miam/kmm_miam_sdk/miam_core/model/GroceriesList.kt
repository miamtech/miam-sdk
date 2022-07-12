package com.miam.kmm_miam_sdk.miam_core.model

import com.miam.kmm_miam_sdk.handler.LogHandler
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("groceries-lists")
data class GroceriesList private constructor(
    override val id: String,
    override var attributes: GroceriesListAttributes? = null,
    override val relationships: GroceriesListRelationships? = null,
    @Transient var recipes : List<Recipe> = listOf()
): Record() {
    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) null else jsonFormat.decodeFromJsonElement<GroceriesListAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<GroceriesListRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    fun hasRecipe(recipeId: String): Boolean {
        if (attributes?.recipesInfos.isNullOrEmpty()) return false
        return  attributes!!.recipesInfos.any { el -> el.id.toString() == recipeId }
    }

    fun guestsForRecipe(recipeId: String): Int {
        if (attributes?.recipesInfos.isNullOrEmpty()) return -1
        val recipeInfo = this.attributes!!.recipesInfos.find { recipeInfos -> recipeInfos.id.toString() == recipeId }
            ?: return -1
        return recipeInfo.guests
    }

    fun missingRecipesIds(existingRecipes: List<Recipe>): List<String> {
        val missingIds = mutableListOf<String>()
        val allIds = this.attributes?.recipesInfos?.map { ri -> ri.id.toString() } ?: emptyList()
        val existingIds = existingRecipes.map { recipe -> recipe.id }
        allIds.forEach { recipeId ->
            if (existingIds.indexOf(recipeId) < 0 ) {
                missingIds.add(recipeId)
            }
        }
        return missingIds
    }

    fun rebuildRecipesRelationships(missingRecipes: List<Recipe>, existingRecipes: List<Recipe>) {
        val newRecipesList = mutableListOf<Recipe>()
        val existingPresentRecipes = existingRecipes.filter { recipe -> this.hasRecipe(recipe.id) }
        newRecipesList.addAll(existingPresentRecipes)
        newRecipesList.addAll(missingRecipes)
        this.recipes = newRecipesList
    }
}


@Serializable
data class GroceriesListAttributes constructor(
    var name: String,
    @SerialName("created-at")
    val createdAt: String,
    @SerialName("updated-at")
    val updatedAt: String,
    @SerialName("recipes-infos")
    val recipesInfos: MutableList<RecipeInfos>,
    @SerialName("user-id")
    val userId: String? = null,
    @SerialName("append-recipes")
    var appendRecipes: Boolean = true,
): Attributes()


@Serializable
data class GroceriesListRelationships constructor(
    @SerialName("groceries-entries") var groceriesEntries: GroceriesEntryRelationshipList? = null,
): Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        groceriesEntries?.buildFromIncluded(includedRecords)
    }
}


