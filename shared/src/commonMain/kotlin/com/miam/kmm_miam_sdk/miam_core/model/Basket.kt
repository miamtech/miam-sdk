package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Baskets(
    @SerialName("data")
    val baskets: List<Basket>,
)

@Serializable
data class BasketWrapper(
    val data : Basket
)

@Serializable
data class Basket(
    val id: Int,
    val type: String? = null,
    val attributes: BasketAttributes,
){
    var _relationships: BasketRelationships? = null

    private fun deepCopy(
        id: Int = this.id,
        type: String? = this.type,
        attributes: BasketAttributes = this.attributes,
    ): Basket {
        val copy = this.copy(id=id, type=type, attributes=attributes)
        copy._relationships = this._relationships
        return copy
    }

    fun updateBasketEntry(newBe: BasketEntry): Basket {
        var copy = this.deepCopy()
        val entryToUpdateIndex = copy._relationships?.basketEntries?.indexOfFirst { be -> be.id == newBe.id }
        if (entryToUpdateIndex != null && entryToUpdateIndex >= 0) {
            val newBasketEntries = copy._relationships!!.basketEntries!!.toMutableList()
            newBasketEntries[entryToUpdateIndex] = newBe
            copy._relationships!!.basketEntries = newBasketEntries
        }
        return copy
    }
}

@Serializable
data class BasketAttributes(
    val name : String?,
    val confirmed: Boolean? = false,
    val completion : BasketCompletion? = null,
    @SerialName("total-price")
    val totalPrice: Float? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? =null,
    val token :String? = null,
)

@Serializable
data class BasketRelationships(
    var basketEntries: List<BasketEntry>? = emptyList<BasketEntry>(),
)

@Serializable
data class  BasketCompletion(
    val total: Int? = 0,
    val found: Int? = 0
)