package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroceriesEntries(
    @SerialName("data")
    val groceriesEntries: List<GroceriesEntry>,
)

@Serializable
data class GroceriesEntryWrapper(
   val data: GroceriesEntry
)

@Serializable
data class GroceriesEntry(
    val id: Int,
    val type: String? = "groceries-entries",
    val attributes: GroceriesEntryAttributes,
)

@Serializable
data class GroceriesEntryAttributes(
    val name : String?,
    @SerialName("capacity-volume")
    val capacityVolume: String? =null,
    @SerialName("capacity-unit")
    val capacityUnit: String? =null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? =null,
    val status: String? =null,
    @SerialName("recipe-ids")
    val recipeIds: List<Int>? = emptyList()
)

@Serializable
data class GroceriesEntryUpdate(
    val id: Int,
    val type: String,
    val attributes: GroceriesEntryAttributes,
)


@Serializable
data class GroceriesEntryUpdateWrapper( val data: GroceriesEntryUpdate)
