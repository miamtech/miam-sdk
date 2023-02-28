package com.miam.core.sdk.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.SponsorDataSource
import com.miam.kmmMiamCore.miam_core.model.Sponsor

public interface SponsorRepository {
    public suspend fun getSponsorById(sponsorId: String): Sponsor
}

public class SponsorRepositoryImp(private val sponsorDataSource: SponsorDataSource): SponsorRepository {

    override suspend fun getSponsorById(sponsorId: String): Sponsor {
        return sponsorDataSource.getSponsorById(sponsorId, DEFAULT_INCLUDED)
    }

    public companion object {
        public val DEFAULT_INCLUDED: List<String> = listOf("sponsor-blocks")
    }
}
