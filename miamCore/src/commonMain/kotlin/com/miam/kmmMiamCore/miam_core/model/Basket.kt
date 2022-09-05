package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
@SerialName("baskets")
data class Basket private constructor(
    override val id: String,
    override var attributes: BasketAttributes? = null,
    override val relationships: BasketRelationships? = null,
    @Transient var recipes: List<Recipe> = listOf()
) : Record() {
    constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) null else jsonFormat.decodeFromJsonElement<BasketAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<BasketRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    fun updateBasketEntry(newBe: BasketEntry): Basket {
        var newBasket = this.copy()
        val entryToUpdateIndex =
            newBasket.relationships?.basketEntries?.data?.indexOfFirst { be -> be.id == newBe.id }
        if (entryToUpdateIndex != null && entryToUpdateIndex >= 0) {
            val newBasketEntries = newBasket.relationships!!.basketEntries!!.data.toMutableList()
            newBasketEntries[entryToUpdateIndex] = newBe
            val newRelationships = newBasket.relationships!!.copy(
                basketEntries = BasketEntryRelationshipList(newBasketEntries)
            )
            newBasket = newBasket.copy(relationships = newRelationships)
        }
        return newBasket
    }
}

@Serializable
data class BasketAttributes(
    val name: String?,
    val confirmed: Boolean? = false,
    val completion: BasketCompletion? = null,
    @SerialName("total-price")
    val totalPrice: Float,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = null,
    val token: String? = null,
) : Attributes()

@Serializable
data class BasketRelationships constructor(
    @SerialName("basket-entries") var basketEntries: BasketEntryRelationshipList? = null,
) : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        // println("Miam will build basket entries $basketEntries")
        basketEntries?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class BasketCompletion(
    val total: Int? = 0,
    val found: Int? = 0
)