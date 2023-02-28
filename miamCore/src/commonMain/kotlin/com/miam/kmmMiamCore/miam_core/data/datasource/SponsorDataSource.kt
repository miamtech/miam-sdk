package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Sponsor

public interface SponsorDataSource {
    public suspend fun getSponsorById(sponsorId: String, included: List<String>): Sponsor
}