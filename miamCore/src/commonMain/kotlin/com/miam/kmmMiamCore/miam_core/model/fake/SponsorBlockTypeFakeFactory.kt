package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.SponsorBlockType
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockTypeAttributes
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockTypeRelationships

public object SponsorBlockTypeFakeFactory {

    public const val FAKE_ID: String = "sponsorBlockUUID"
    public const val FAKE_NAME: String = "sponsorName"

    public fun create(
        id: String = FAKE_ID,
        attributes: SponsorBlockTypeAttributes? = createAttributes(),
        relationships: SponsorBlockTypeRelationships? = null
    ): SponsorBlockType = SponsorBlockType(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        name: String = FAKE_NAME,
        iconUrl: String = "https://www.iconspng.com/images/rick-astley/rick-astley.jpg",
        description: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        defaultHeight: Int = 1,
        defaultWidth: Int = 5,
        mandatoryFields: List<String> = listOf("Text", "video-url", "picture-url", "background-color")
    ): SponsorBlockTypeAttributes = SponsorBlockTypeAttributes(
        name = name,
        iconUrl = iconUrl,
        description = description,
        defaultHeight = defaultHeight,
        defaultWidth = defaultWidth,
        mandatoryFields = mandatoryFields
    )
}