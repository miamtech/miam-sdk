package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
@SerialName("packages")
public data class Package internal constructor(
    override val id: String,
    override var attributes: PackageAttributes? = null,
    override val relationships: PackageRelationships? = null
): Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) null else jsonFormat.decodeFromJsonElement<PackageAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<PackageRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    public fun buildRecipes(recipes: List<Recipe>): Package {
        val newRel = PackageRelationships(RecipeRelationshipList(recipes))
        return copy(relationships = newRel)
    }

    val subtitle: String?
        get() = attributes?.settings?.subtitle
}

@Serializable
public data class PackageAttributes(
    val title: String,
    @SerialName("author-id")
    val authorId: String?,
    val editable: Boolean? = false,
    val shared: Boolean? = false,
    @SerialName("catalog-category")
    val catalogCategory: Boolean? = false,
    @SerialName("catalog-position")
    val catalogPosition: Int?,
    @SerialName("recipes-count")
    val recipesCount: Int,
    val settings: PackageSettings
): Attributes()

@Serializable
public data class PackageRelationships constructor(
    var recipes: RecipeRelationshipList? = null,
): Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        // println("Miam will build package recipes")
        recipes?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class PackageSettings(
    var subtitle: String? = null,
)
