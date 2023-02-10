package com.miam.core.data.repository

import com.miam.core.sdk.data.repository.SponsorRepository
import com.miam.core.sdk.data.repository.SponsorRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.datasource.SponsorDataSource
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test


class SponsorRepositoryTest: TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var sponsorDataSource: SponsorDataSource
    private val srt: SponsorRepository by lazy { SponsorRepositoryImp(sponsorDataSource) }

    @Test
    fun `get Sponsor Blocks By SponsorId `() = runTest {
        // When
        // every { sponsorDataSource.getSponsorById(isAny(), isAny()) } runs { }
    }
}