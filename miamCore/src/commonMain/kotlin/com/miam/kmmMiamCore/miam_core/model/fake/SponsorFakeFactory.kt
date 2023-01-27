package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.SponsorAttributes
import com.miam.kmmMiamCore.miam_core.model.SponsorRelationships

public object SponsorFakeFactory {

    public const val FAKE_ID: String = "sponsorUUID"
    public const val FAKE_NAME: String = "sponsorName"

    public fun create(
        id: String = FAKE_ID,
        attributes: SponsorAttributes? = createAttributes(),
        relationships: SponsorRelationships? = null
    ): Sponsor = Sponsor(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        name: String = FAKE_NAME,
        logoUrl: String = ""
    ): SponsorAttributes = SponsorAttributes(
        name = name, logoUrl = logoUrl,
    )
}