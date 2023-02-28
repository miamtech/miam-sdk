package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.SponsorBlock

public interface SponsorBlockDataSource {
    public suspend fun getSponsorBlocksBySponsorId(sponsorId: String, included: List<String>): List<SponsorBlock>
}