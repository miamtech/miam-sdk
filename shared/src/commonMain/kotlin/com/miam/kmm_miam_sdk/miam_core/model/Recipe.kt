package com.miam.kmm_miam_sdk.miam_core.model

import com.miam.kmm_miam_sdk.miam_core.model.utils.DurationSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes


@Serializable
@SerialName("recipes")
data class Recipe private constructor(
    override val id: String,
    override val attributes: RecipeAttributes? = null,
    override  val relationships: RecipeRelationships? = null
): Record(), BasketPreviewEntry {

    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    val totalTime: String
        get() {
            var duration : Duration = 0.minutes
            duration = duration.plus(this.attributes!!.preparationTime ?: 0.minutes)
            duration = duration.plus(this.attributes.cookingTime  ?: 0.minutes )
            duration =  duration.plus(this.attributes.restingTime  ?: 0.minutes)
            if (duration.inWholeMinutes < 10) {
                return " < 10 min";
            }
            return duration.toString()
        }

    val difficultyLabel : String
        get () {
            return when(this.attributes!!.difficulty) {
                1 -> "facile"
                2 -> "moyen"
                3-> "difficile"
                else -> "facile"
            }
        }

    val costLabel : String
        get () {
            return when(this.attributes!!.difficulty) {
                1 -> "faible"
                2 -> "moyen"
                3-> "difficile"
                else -> "moyen"
            }
        }
}

@Serializable
data class RecipeAttributes constructor(
    val title: String,

    @SerialName("ext-id")
    val extId: String? = null,

    @SerialName("ext-link")
    val extLink: String? = null,

    val description: String? = null,

    @SerialName("number-of-guests")
    val numberOfGuests: Int? = 4,

    @SerialName("ingredients-str")
    val ingredientsStr: List<String>? = emptyList(),

    @Serializable(with = DurationSerializer::class)
    @SerialName("preparation-time")
    val preparationTime: Duration? = 0.minutes,

    @Serializable(with = DurationSerializer::class)
    @SerialName("preheating-time")
    val preheatingTime: Duration? = 0.minutes,

    @Serializable(with = DurationSerializer::class)
    @SerialName("cooking-time")
    val cookingTime: Duration? = 0.minutes,

    @Serializable(with = DurationSerializer::class)
    @SerialName("resting-time")
    val restingTime: Duration? = 0.minutes,

    @SerialName("media-url")
    val mediaUrl: String? = null,

    val source : String? = "",

    @SerialName("informational-page-html")
    val informationalPageHtml: String?= "",

    @SerialName("filigrane-logo-url")
    val filigraneLogoUrl: String? = "",

    @SerialName("informational-sentence")
    val informationalSentence: String? ="",

    val difficulty : Int? = 1,

    val cost : Int? = 2,

    val suggested: Boolean? = false,

    val popularity: Int? = 5,

    @SerialName("video-id")
    val videoId: String? = "",

    val promoted: Boolean? = false,
): Attributes()

@Serializable
data class RecipeRelationships (
    var ingredients: IngredientListRelationship? = null,
    @SerialName("recipe-provider")
    var recipeProvider: RecipeProviderRelationship? = null,
    @SerialName("recipe-status")
    var recipeStatus: RecipeStatusRelationship? = null,
    var sponsors: SponsorListRelationship? = null,
    @SerialName("recipe-steps")
    var recipeSteps: RecipeStepListRelationship? = null,
    @SerialName("recipe-type")
    var recipeType: RecipeTypeRelationship? = null
): Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        ingredients?.buildFromIncluded(includedRecords)
        recipeProvider?.buildFromIncluded(includedRecords)
        recipeStatus?.buildFromIncluded(includedRecords)
        sponsors?.buildFromIncluded(includedRecords)
        recipeSteps?.buildFromIncluded(includedRecords)
        recipeType?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class RecipeInfos (
    val id: Int,
    var guests: Int,
)

/**
 * Used from others relations
 */

@Serializable(with = RecipeRelationshipListSerializer::class)
class RecipeRelationshipList(override var data: List<Recipe>): RelationshipList() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, Recipe::class) as List<Recipe>
    }
}

@Serializer(forClass = RecipeRelationshipList::class)
object RecipeRelationshipListSerializer : KSerializer<RecipeRelationshipList> {
    override fun serialize(encoder: Encoder, value: RecipeRelationshipList) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}



