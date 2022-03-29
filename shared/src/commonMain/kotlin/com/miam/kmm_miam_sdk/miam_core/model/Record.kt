package com.miam.kmm_miam_sdk.miam_core.model

import io.ktor.util.reflect.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
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
data class RecordWrapper(var data: JsonElement? = null, var included: JsonElement? = null) {

    private fun includedRecords(): List<Record> {
        println("Miam RecordWrapper included $included")
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
        println("Miam construct record whole object " + data + " " + included)
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

    private fun createRecord(type: String, id: String, attributes: JsonElement?, relationships: JsonElement?, includedRecords: List<Record>): Record {
        return when(type) {
            GroceriesList.TYPE -> GroceriesList(id, attributes, relationships, includedRecords)
            GroceriesEntry2.TYPE -> GroceriesEntry2(id, attributes, relationships, includedRecords)
            Recipe.TYPE -> Recipe(id, attributes, relationships, includedRecords)
            Ingredient.TYPE -> Ingredient(id, attributes, relationships, includedRecords)
            else -> throw Exception("Unsuported record type")
        }
    }

    companion object {
        fun fromRecord(record: Record): RecordWrapper {
            return RecordWrapper(record.toJsonElement())
        }

        fun fromRecords(records: List<Record>): RecordWrapper {
            return RecordWrapper(JsonArray(records.map {it.toJsonElement()}))
        }
    }
}


@Serializable
sealed class Record {
    abstract val id: String
    abstract val attributes: Attributes?
    abstract val relationships: Relationships?

    fun toJsonElement(): JsonElement {
        return jsonFormat.encodeToJsonElement(serializer(), this)
    }
}

@Serializable
sealed class Attributes

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

@Serializable
sealed class Relationship {
    abstract val data: Record
}

@Serializable
sealed class RelationshipList {
    abstract val data: List<Record>

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



@Serializable
@SerialName(GroceriesEntry2.TYPE)
data class GroceriesEntry2 private constructor(
    override val id: String,
    override val attributes: GroceriesEntry2Attributes? = null,
    override  val relationships: GroceriesEntry2Relationships? = null
): Record() {

    constructor(id: String, attributes: JsonElement?, relationships: JsonElement?, includedRecords: List<Record>?) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<GroceriesEntry2Attributes>(attributes),
        null,
    ) {

    }

    override fun toString(): String {
        return "GroceriesEntry2: $id - $attributes - $relationships"
    }

    companion object {
        const val TYPE: String = "groceries-entries"
    }
}

@Serializable
data class GroceriesEntry2Attributes constructor(
    var name: String,
    @SerialName("capacity-volume")
    var capacity_volume: String,
): Attributes() {
}

@Serializable
class GroceriesEntry2Relationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}