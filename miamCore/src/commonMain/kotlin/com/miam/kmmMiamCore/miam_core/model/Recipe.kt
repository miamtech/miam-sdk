package com.miam.kmmMiamCore.miam_core.model

import com.miam.kmmMiamCore.miam_core.model.utils.CustomDurationSerializer
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
public data class RecipesListWrapper internal constructor(
    val data: List<Recipe>,
)

@Serializable
@SerialName("recipes")
public data class Recipe internal constructor(
    override val id: String,
    override val attributes: RecipeAttributes? = null,
    override val relationships: RecipeRelationships? = null,
    val recipeLike: RecipeLike? = null
): Record(), BasketPreviewEntry {

    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    val totalTime: String
        get() {
            var duration: Duration = 0.minutes
            duration = duration.plus(this.attributes!!.preparationTime ?: 0.minutes)
            duration = duration.plus(this.attributes.cookingTime ?: 0.minutes)
            duration = duration.plus(this.attributes.restingTime ?: 0.minutes)
            if (duration.inWholeMinutes < 10) {
                return " < 10 min"
            }
            return duration.toString()
        }

    val preparationTimeIos: String
        get() {
            val duration = this.attributes?.preparationTime ?: 0.minutes
            return duration.toString()
        }

    val cookingTimeIos: String
        get() {
            val duration = this.attributes?.cookingTime ?: 0.minutes
            return duration.toString()
        }

    val restingTimeIos: String
        get() {
            val duration = this.attributes?.cookingTime ?: 0.minutes
            return duration.toString()
        }

    val sortedStep: List<RecipeStep>
        get() {
            val temp = mutableListOf<RecipeStep>()
            temp.addAll(this.relationships?.recipeSteps?.data ?: emptyList())
            temp.sortBy { it.attributes?.stepNumber }
            return temp
        }
    val isSponsored: Boolean
        get() {
            return (this.relationships?.sponsors?.data?.size ?: 0) > 0
        }
}

public enum class RecipeAttributeName(public val attributeName: String) {
    TITLE("title"),
    EXT_ID("ext-id"),
    EXT_LINK("ext-link"),
    DESCRIPTION("description"),
    INGREDIENTS_STRING("ingredients-str"),
    NUMBER_OF_GUESTS("number-of-guests"),
    PREPARATION_TIME("preparation-time"),
    PREHEATING_TIME("preheating-time"),
    COOKING_TIME("cooking-time"),
    RESTING_TIME("resting-time"),
    MEDIA_URL("media-url"),
    INFORMATIONAL_PAGE_HTML("informational-page-html"),
    FILIGRANE_LOGO_URL("filigrane-logo-url"),
    INFORMATIONAL_SENTENCE("informational-sentence"),
    DIFFICULTY("difficulty"),
    COST("cost"),
    SUGGESTED("suggested"),
    POPULARITY("popularity"),
    VIDEO_ID("video-id"),
    PROMOTED("promoted"),
    SLUG("slug"),
    POSITION("position");

    public companion object {
        public fun allAttributes(): Array<RecipeAttributeName> {
            return values()
        }

        public fun recipeCardAttributes(): Array<RecipeAttributeName> {
            return arrayOf(TITLE, COOKING_TIME, RESTING_TIME, PREPARATION_TIME, DIFFICULTY, MEDIA_URL, NUMBER_OF_GUESTS)
        }
    }
}

public enum class RecipeRelationshipName(public val relationshipName: String) {
    INGREDIENTS("ingredients"),
    RECIPE_STEPS("recipe-steps"),
    RECIPE_PROVIDERS("recipe-provider"),
    RECIPE_STATUS("recipe-status"),
    RECIPE_TYPE("recipe-type"),
    SPONSORS("sponsors");

    public companion object {
        public fun allRelationships(): Array<RecipeRelationshipName> {
            return values()
        }

        public fun relationshipsForRecipeCard(): Array<RecipeRelationshipName> {
            return arrayOf()
        }
    }
}

@Serializable
public data class RecipeAttributes constructor(
    val title: String,

    @SerialName("ext-id")
    val extId: String? = null,

    @SerialName("ext-link")
    val extLink: String? = null,

    // description is a key word in swift
    @SerialName("description")
    val recipeDescription: String? = null,

    @SerialName("number-of-guests")
    val numberOfGuests: Int = 4,

    @SerialName("ingredients-str")
    val ingredientsStr: List<String>? = emptyList(),

    @Serializable(with = CustomDurationSerializer::class)
    @SerialName("preparation-time")
    val preparationTime: Duration? = 0.minutes,

    @Serializable(with = CustomDurationSerializer::class)
    @SerialName("preheating-time")
    val preheatingTime: Duration? = 0.minutes,

    @Serializable(with = CustomDurationSerializer::class)
    @SerialName("cooking-time")
    val cookingTime: Duration? = 0.minutes,

    @Serializable(with = CustomDurationSerializer::class)
    @SerialName("resting-time")
    val restingTime: Duration? = 0.minutes,

    @SerialName("media-url")
    val mediaUrl: String,

    val source: String? = "",

    @SerialName("informational-page-html")
    val informationalPageHtml: String? = "",

    @SerialName("filigrane-logo-url")
    val filigraneLogoUrl: String? = "",

    @SerialName("informational-sentence")
    val informationalSentence: String? = "",

    val difficulty: Int? = 1,

    val cost: Int? = 2,

    val suggested: Boolean? = false,

    val popularity: Int? = 5,

    @SerialName("video-id")
    val videoId: String? = "",

    val promoted: Boolean? = false,
): Attributes()

@Serializable
public data class RecipeRelationships(
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
public data class RecipeInfos(
    val id: Int,
    var guests: Int,
)

/**
 * Used from others relations
 */
@Serializable(with = RecipeRelationshipListSerializer::class)
public class RecipeRelationshipList(override var data: List<Recipe>): RelationshipList() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, Recipe::class)
            .filterIsInstance<Recipe>()
    }
}

@Serializer(forClass = RecipeRelationshipList::class)
public object RecipeRelationshipListSerializer: KSerializer<RecipeRelationshipList> {
    override fun serialize(encoder: Encoder, value: RecipeRelationshipList) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}



