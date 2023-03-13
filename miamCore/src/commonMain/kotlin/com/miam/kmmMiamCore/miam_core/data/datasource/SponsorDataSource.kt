package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.SponsorRelationshipName

public interface SponsorDataSource {
    public suspend fun getSponsorById(sponsorId: String, included: Array<SponsorRelationshipName>): Sponsor
}