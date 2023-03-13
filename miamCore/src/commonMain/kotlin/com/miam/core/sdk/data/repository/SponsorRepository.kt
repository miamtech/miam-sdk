package com.miam.core.sdk.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.SponsorDataSource
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.SponsorRelationshipName

public interface SponsorRepository {
    public suspend fun getSponsorById(sponsorId: String): Sponsor
}

public class SponsorRepositoryImp(private val sponsorDataSource: SponsorDataSource): SponsorRepository {

    override suspend fun getSponsorById(sponsorId: String): Sponsor {
        return sponsorDataSource.getSponsorById(sponsorId, SponsorRelationshipName.values())
    }
}
