package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.SponsorBlock
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockAttributes
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockRelationships

public object SponsorBlockFakeFactory {

    public const val FAKE_ID: String = "sponsorBlockUUID"
    public const val FAKE_NAME: String = "sponsorName"

    public fun create(
        id: String = FAKE_ID,
        attributes: SponsorBlockAttributes? = createAttributes(),
        relationships: SponsorBlockRelationships? = null
    ): SponsorBlock = SponsorBlock(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        position: Int = 1,
        height: Int = 1,
        width: Int = 5,
        videoUrl: String? = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
        pictureUrl: String? = "https://kultt.fr/wp-content/uploads/2022/09/RickAstley-ad2022.jpg",
        text: String? = null,
        backgroundColor: String? = null
    ): SponsorBlockAttributes = SponsorBlockAttributes(
        position = position,
        height = height,
        width = width,
        videoUrl = videoUrl,
        pictureUrl = pictureUrl,
        text = text,
        backgroundColor = backgroundColor
    )
}