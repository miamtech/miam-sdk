package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.SponsorBlock
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockRelationshipName

public interface SponsorBlockDataSource {
    public suspend fun getSponsorBlocksBySponsorId(sponsorId: String, included: Array<SponsorBlockRelationshipName>): List<SponsorBlock>
}