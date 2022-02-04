package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroceriesListWrapper(
    @SerialName("data")
    val data: GroceriesList
)

@Serializable
data class GroceriesList(
    val id: Int,
    val type: String,
    val attributes : GroceriesListAttributes,
    var relationships: GroceriesEntryRelationship?
) {

   fun hasRecipe(recipeId: Int): Boolean {
       if (attributes.recipesInfos.isNullOrEmpty()) return false
       return  attributes.recipesInfos.any { el -> el.id == recipeId }
    }

    fun guestsForRecipe(recipeId: Int): Int {
        if (attributes.recipesInfos.isNullOrEmpty()) return -1
        val recipeInfo = this.attributes.recipesInfos.find { recipeInfos -> recipeInfos.id == recipeId }
            ?: return -1
        return recipeInfo.guests
    }
}

@Serializable
data class GroceriesListAttributes(
    val name : String?,
    @SerialName("created-at")
    val createdAt: String? =null,
    @SerialName("updated-at")
    val updatedAt: String? =null,
    @SerialName("recipes-infos")
    val recipesInfos: List<RecipeInfos>? = emptyList(),
    @SerialName("user-id")
    val userId: String? = null,
    @SerialName("append-recipes")
    var appendRecipes: Boolean = true,

)

@Serializable
data class GroceriesEntryRelationship(
    var groceriesEntries: List<GroceriesEntry>? = emptyList(),
    var recipes: List<Recipe>? = emptyList()
)

@Serializable
data class GroceriesListWithoutRelationship(
    val id: Int,
    val type: String,
    val attributes : GroceriesListAttributes
    )

@Serializable
class GroceriesListWithoutRelationshipWrapper(val data :GroceriesListWithoutRelationship)


