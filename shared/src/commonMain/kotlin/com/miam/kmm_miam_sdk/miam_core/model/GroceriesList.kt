package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroceriesListWrapper(
    @SerialName("data")
    val groceriesList: GroceriesList
)

@Serializable
data class GroceriesList(
    val id: Int,
    val attributes : GroceriesListAttributes
) {

   fun hasRecipe(recipeId: Int): Boolean {
       if (attributes.recipesIds.isNullOrEmpty()) return false
       return  attributes.recipesIds.contains(""+recipeId)
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
    @SerialName("recipes-ids")
    val recipesIds: List<String>? = emptyList(),
    @SerialName("recipes-infos")
    val recipesInfos: List<RecipeInfos>? = emptyList(),
    @SerialName("user-id")
    val userId: String? = null,

    //var groceriesEntries: GroceriesEntries? =  null
)

