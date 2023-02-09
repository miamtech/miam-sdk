package com.miam.core.sdk.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.SponsorBlock

public interface SponsorRepository {
    public suspend fun getSponsorById(sponsorId: String): Sponsor
    public suspend fun getSponsorBlockWithSponsorId(sponsorId: String): List<SponsorBlock>
}

internal class SponsorRepositoryImp(private val sponsorDataSource: MiamAPIDatasource): SponsorRepository {

    override suspend fun getSponsorById(sponsorId: String): Sponsor {
        return sponsorDataSource.getSponsorById(sponsorId, DEFAULT_INCLUDED)
    }

    override suspend fun getSponsorBlockWithSponsorId(sponsorId: String): List<SponsorBlock> {
        return sponsorDataSource.getSponsorBlockWithSponsorId(sponsorId, DEFAULT_INCLUDED)
    }

    companion object {
        val DEFAULT_INCLUDED: List<String> = listOf("sponsor-blocks")
    }
}
