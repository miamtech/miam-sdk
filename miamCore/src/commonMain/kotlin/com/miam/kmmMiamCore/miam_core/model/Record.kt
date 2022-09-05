package com.miam.kmmMiamCore.miam_core.model

import io.ktor.http.*
import io.ktor.util.reflect.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlin.reflect.KClass

fun JsonObject.getStringValueOrNull(attribute: String): String? {
    return this[attribute]?.jsonPrimitive?.content
}

fun JsonObject.getStringValueOrThrow(attribute: String): String {
    return this[attribute]!!.jsonPrimitive.content
}

fun JsonObject.getIntValueOrThrow(attribute: String): Int {
    return this[attribute]!!.jsonPrimitive.int
}

val jsonFormat = Json {
    ignoreUnknownKeys = true
}

@Serializable
data class RecordCounterWrapper(var links: RecordLink) {

    /**only work if page size is 1*/
    fun getCount(): Int {
        val lastPageParams = Url(this.links.last).parameters
        val pagesize = lastPageParams["page[size]"]?.toInt() ?: 0
        val pagenumber = lastPageParams["page[number]"]?.toInt() ?: 0
        return pagesize * pagenumber
    }
}

@Serializable
data class RecordLink(val first: String, val last: String)


/**
 * Wrapper to collect or send you records
 * use functions toRecord(s) and fromRecord(s) to receive or send to jsonapi with format "data : {...}"
 * for receiving you can receive format "data:{...}, included: {...}"
 */
@Serializable
data class RecordWrapper(var data: JsonElement? = null, var included: JsonElement? = null) {

    private fun includedRecords(): List<Record> {
        if (included == null) return listOf()

        return included!!.jsonArray.map { jsonElt ->
            createRecord(jsonElt)
        }
    }

    fun toRecords(): List<Record> {
        val dataObject = data!!.jsonArray
        return dataObject.map { jsonElt ->
            createRecord(jsonElt, includedRecords())
        }
    }

    fun toRecord(): Record {
        return createRecord(data!!, includedRecords())
    }

    private fun createRecord(data: JsonElement, includedRecords: List<Record> = listOf()): Record {
        val dataObject = data.jsonObject
        return createRecord(
            dataObject.getStringValueOrThrow("type"),
            dataObject.getStringValueOrThrow("id"),
            dataObject["attributes"],
            dataObject["relationships"],
            includedRecords
        )
    }

    private fun createRecord(
        type: String,
        id: String,
        attributes: JsonElement?,
        relationships: JsonElement?,
        includedRecords: List<Record>
    ): Record {
        return when (type) {
            GroceriesList.serializer().descriptor.serialName -> GroceriesList(
                id,
                attributes,
                relationships,
                includedRecords
            )
            GroceriesEntry.serializer().descriptor.serialName -> GroceriesEntry(
                id,
                attributes,
                relationships,
                includedRecords
            )
            Recipe.serializer().descriptor.serialName -> Recipe(
                id,
                attributes,
                relationships,
                includedRecords
            )
            Ingredient.serializer().descriptor.serialName -> Ingredient(
                id,
                attributes,
                relationships,
                includedRecords
            )
            RecipeProvider.serializer().descriptor.serialName -> RecipeProvider(
                id,
                attributes,
                relationships,
                includedRecords
            )
            RecipeStatus.serializer().descriptor.serialName -> RecipeStatus(
                id,
                attributes,
                relationships,
                includedRecords
            )
            Sponsor.serializer().descriptor.serialName -> Sponsor(
                id,
                attributes,
                relationships,
                includedRecords
            )
            RecipeStep.serializer().descriptor.serialName -> RecipeStep(
                id,
                attributes,
                relationships,
                includedRecords
            )
            RecipeType.serializer().descriptor.serialName -> RecipeType(
                id,
                attributes,
                relationships,
                includedRecords
            )
            Basket.serializer().descriptor.serialName -> Basket(
                id,
                attributes,
                relationships,
                includedRecords
            )
            BasketEntry.serializer().descriptor.serialName -> BasketEntry(
                id,
                attributes,
                relationships,
                includedRecords
            )
            Item.serializer().descriptor.serialName -> Item(
                id,
                attributes,
                relationships,
                includedRecords
            )
            RecipeLike.serializer().descriptor.serialName -> RecipeLike(
                id,
                attributes,
                relationships,
                includedRecords
            )
            Package.serializer().descriptor.serialName -> Package(
                id,
                attributes,
                relationships,
                includedRecords
            )
            else -> throw Exception("Unsuported record type $type")
        }
    }

    companion object {
        fun fromRecord(record: Record): RecordWrapper {
            return RecordWrapper(record.toJsonElement())
        }

        fun fromRecords(records: List<Record>): RecordWrapper {
            return RecordWrapper(JsonArray(records.map { it.toJsonElement() }))
        }
    }
}

/**
 * Any record should extend this class that describe any records
 */
@Serializable
sealed class Record {
    abstract val id: String?
    abstract val attributes: Attributes?
    abstract val relationships: Relationships?

    fun toJsonElement(): JsonElement {
        return jsonFormat.encodeToJsonElement(serializer(), this)
    }
}

@Serializable
sealed class Attributes

/**
 * got many relations that will be Relationship or RelationshipList
 */
@Serializable
sealed class Relationships {
    abstract fun buildFromIncluded(includedRecords: List<Record>)

    companion object {
        fun filterEmptyRelationships(relationships: JsonElement): JsonElement {
            val filteredMapObject = relationships.jsonObject.filter { entryElement ->
                entryElement.value.jsonObject["data"] != null
            }
            return JsonObject(filteredMapObject)
        }
    }
}

/**
 * present in record to wrap the record when coming from a relationship
 */
@Serializable
sealed class Relationship {
    abstract val data: Record

    fun buildedFromIncluded(includedRecords: List<Record>, subClass: KClass<out Record>): Record {
        // println("Miam Relationship start buildFromIncluded $subClass")
        val existingEntry =
            includedRecords.find { record -> record.instanceOf(subClass) && record.id == data.id }
        // println("Miam Relationship buildFromIncluded $subClass found $existingEntry")
        if (existingEntry != null) {
            // println("Miam existing relations " + existingEntry.relationships)
            existingEntry.relationships?.buildFromIncluded(includedRecords)
            return existingEntry
        } else return data
    }

    fun serialize(encoder: Encoder) {
        require(encoder is JsonEncoder)


        val relRecords = JsonObject(data.toJsonElement().jsonObject.filter { entry ->
            entry.key == "id" || entry.key == "type"
        })
        encoder.encodeJsonElement(JsonObject(mapOf("data" to JsonObject(relRecords))))
    }
}

@Serializable
sealed class RelationshipList {
    abstract val data: List<Record>

    fun buildedFromIncluded(
        includedRecords: List<Record>,
        subClass: KClass<out Record>
    ): List<Record> {
        // println("Miam RelationshipList start buildFromIncluded $subClass")
        return data.map { entry ->
            val existingEntry =
                includedRecords.find { record -> record.instanceOf(subClass) && record.id == entry.id }
            // println("Miam RelationshipList buildFromIncluded $subClass found $existingEntry")
            if (existingEntry != null) {
                // println("Miam existing relations " + existingEntry.relationships)
                existingEntry.relationships?.buildFromIncluded(includedRecords)
                existingEntry
            } else entry
        }
    }

    fun serialize(encoder: Encoder) {
        require(encoder is JsonEncoder)

        val relRecords = data.map { record ->
            JsonObject(record.toJsonElement().jsonObject.filter { entry ->
                entry.key == "id" || entry.key == "type"
            })
        }
        encoder.encodeJsonElement(JsonObject(mapOf("data" to JsonArray(relRecords))))
    }
}