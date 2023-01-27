package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("groceries-lists")
public data class GroceriesList internal constructor(
    override val id: String,
    override var attributes: GroceriesListAttributes? = null,
    override val relationships: GroceriesListRelationships? = null,
    @Transient var recipes: List<Recipe> = listOf()
) : Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) null else jsonFormat.decodeFromJsonElement<GroceriesListAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<GroceriesListRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    public fun hasRecipe(recipeId: String): Boolean {
        if (attributes?.recipesInfos.isNullOrEmpty()) return false
        return attributes!!.recipesInfos.any { el -> el.id.toString() == recipeId }
    }

    public fun guestsForRecipe(recipeId: String): Int {
        if (attributes?.recipesInfos.isNullOrEmpty()) return -1
        val recipeInfo =
            this.attributes!!.recipesInfos.find { recipeInfos -> recipeInfos.id.toString() == recipeId }
                ?: return -1
        return recipeInfo.guests
    }

    public fun missingRecipesIds(existingRecipes: List<Recipe>): List<String> {
        val missingIds = mutableListOf<String>()
        val allIds = this.attributes?.recipesInfos?.map { ri -> ri.id.toString() } ?: emptyList()
        val existingIds = existingRecipes.map { recipe -> recipe.id }
        allIds.forEach { recipeId ->
            if (existingIds.indexOf(recipeId) < 0) {
                missingIds.add(recipeId)
            }
        }
        return missingIds
    }

    public fun rebuildRecipesRelationships(missingRecipes: List<Recipe>, existingRecipes: List<Recipe>) {
        val newRecipesList = mutableListOf<Recipe>()
        val existingPresentRecipes = existingRecipes.filter { recipe -> this.hasRecipe(recipe.id) }
        newRecipesList.addAll(existingPresentRecipes)
        newRecipesList.addAll(missingRecipes)
        this.recipes = newRecipesList
    }
}


@Serializable
public data class GroceriesListAttributes constructor(
    var name: String,
    @SerialName("created-at")
    val createdAt: String, // TODO Romain: use Instant
    @SerialName("updated-at")
    val updatedAt: String, // TODO Romain: use Instant
    @SerialName("recipes-infos")
    val recipesInfos: MutableList<RecipeInfos>, // TODO Romain: Mutable is necessary?
    @SerialName("user-id")
    val userId: String? = null,
    @SerialName("append-recipes")
    var appendRecipes: Boolean = true,
) : Attributes()


@Serializable
public data class GroceriesListRelationships constructor(
    @SerialName("groceries-entries") var groceriesEntries: GroceriesEntryRelationshipList? = null,
) : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        groceriesEntries?.buildFromIncluded(includedRecords)
    }
}


