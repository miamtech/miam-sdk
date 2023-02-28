package com.miam.core.sdk.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.SponsorBlockDataSource
import com.miam.kmmMiamCore.miam_core.model.SponsorBlock

public interface SponsorBlockRepository {
    public suspend fun getSponsorBlocksBySponsorId(sponsorId: String): List<SponsorBlock>

}
internal class SponsorBlockRepositoryImp(private val sponsorBlockDataSource: SponsorBlockDataSource): SponsorBlockRepository {

    override suspend fun getSponsorBlocksBySponsorId(sponsorId: String): List<SponsorBlock> {
        return sponsorBlockDataSource.getSponsorBlocksBySponsorId(sponsorId, DEFAULT_INCLUDED)
    }

    companion object {
        val DEFAULT_INCLUDED: List<String> = listOf("sponsor-block-type")
    }
}