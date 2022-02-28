package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val data: List<Item>
)

@Serializable
data class Item (
    val id: Int,
    val attributes: ItemAttributes,
) {

    private var _inBasket: Boolean? = null
    private var _variableCapacity: String? = null

    val capacity: String
        get() {
            if (this.hasVariableCapacity) return variableCapacity
            return fixedCapacity;
        }

    private val hasVariableCapacity :Boolean
        get() = (this.attributes.variableCapacity == true && this.variableCapacity != "");


    private val fixedCapacity: String
        get() {
            var cap = this.attributes.capacityVolume ?: ""
            if (this.attributes.capacityFactor != null && this.attributes.capacityFactor > 1) {
                cap = "${this.attributes.capacityFactor} x ${cap}"
            }
            if (this.attributes.capacityUnit != null  && this.attributes.capacityUnit.isNotEmpty()) {
                cap = "$cap ${this.attributes.capacityUnit}"
            }
        return cap;
    }

    private var variableCapacity :String
        get() = this._variableCapacity ?: ""
        set(cap: String) {
        if (cap.isNotEmpty()) {
            this._variableCapacity = "$cap ${this.attributes.capacityUnit}"
        }
    }
}

@Serializable
data class ItemAttributes (
    @SerialName("ext_id")
    val extId: Int? = 0,
    val name: String? = "",
    val status: String?= "",
    val description: String? = "",
    val brand: String? = "",
    @SerialName("product-page")
    val productPage: String? = "",
    val image : String? = "",
    @SerialName("unit-price")
    val unitPrice: Float? = null,
    val currency: String? = "",
    @SerialName("capacity-unit")
    val capacityUnit: String? = null,
    @SerialName("capacity-volume")
    val capacityVolume: String? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = 1,
    @SerialName("created-at")
    val createdAt: String? = null,
    @SerialName("updated-at")
    val updatedAt: String? = null,
    val promoted : Boolean = false,
    @SerialName("variable-capacity")
    val variableCapacity: Boolean? = false
)