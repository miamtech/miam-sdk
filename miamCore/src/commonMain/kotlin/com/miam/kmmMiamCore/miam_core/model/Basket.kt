package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

public enum class BasketRelationshipName(public val relationshipName: String) {
    BASKET_ENTRIES("basket-entries"),
    BASKET_ENTRIES_GROCERIES("basket-entries.groceries-entry"),
    BASKET_ENTRIES_ITEMS("basket-entries.items")

}
@Serializable
@SerialName("baskets")
public data class Basket internal constructor(
    override val id: String,
    // TODO Romain: is var really needed?
    // TODO Romain: really optional?
    override var attributes: BasketAttributes? = null,
    // TODO Romain: really optional?
    override val relationships: BasketRelationships? = null,
    @Transient var recipes: List<Recipe> = listOf()
) : Record() {
    public constructor(
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

    public fun updateBasketEntry(newBe: BasketEntry): Basket {
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
public data class BasketAttributes(
    val name: String?,
    val confirmed: Boolean? = false, // TODO Romain: is it relevant to have a nullable boolean
    val completion: BasketCompletion? = null,
    @SerialName("total-price")
    val totalPrice: Float,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = null,
    val token: String? = null,
) : Attributes()

@Serializable
public data class BasketRelationships constructor(
    @SerialName("basket-entries") var basketEntries: BasketEntryRelationshipList? = null,
) : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        // println("Miam will build basket entries $basketEntries")
        basketEntries?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class BasketCompletion(
    val total: Int? = 0,
    val found: Int? = 0
)