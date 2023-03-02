package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
public data class SupplierNotificationWrapper(
    val token: String,
    val status: String,
    val price: String?
)

@Serializable
@SerialName("suppliers")
public data class Supplier internal constructor(
    override val id: String,
    override val attributes: SupplierAttributes? = null,
    override val relationships: SupplierRelationships? = null
): Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<SupplierAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<SupplierRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class SupplierAttributes(
    @SerialName("language-id")
    val languageId: String?
): Attributes()

@Serializable
public object SupplierRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

