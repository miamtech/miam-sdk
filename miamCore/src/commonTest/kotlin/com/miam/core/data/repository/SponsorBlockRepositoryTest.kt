package com.miam.core.data.repository

import com.miam.core.sdk.data.repository.SponsorBlockRepository
import com.miam.core.sdk.data.repository.SponsorBlockRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.datasource.SponsorBlockDataSource
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockRelationships
import com.miam.kmmMiamCore.miam_core.model.fake.SponsorBlockFakeFactory
import com.miam.kmmMiamCore.miam_core.model.fake.SponsorFakeFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SponsorBlockRepositoryTest: TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Mock
    lateinit var sponsorBlockDataSource: SponsorBlockDataSource
    private val sbrt: SponsorBlockRepository by lazy { SponsorBlockRepositoryImp(sponsorBlockDataSource) }

    @Test
    fun `get Sponsor By SponsorId `() = runTest {
        val sponsor = SponsorFakeFactory.create()
        val sponsorBlock = SponsorBlockFakeFactory.create(relationships = SponsorBlockRelationships())

        everySuspending { sponsorBlockDataSource.getSponsorBlocksBySponsorId(isAny(), isAny()) } returns listOf(sponsorBlock)
        val returnedSponsor = sbrt.getSponsorBlocksBySponsorId(sponsorId = sponsor.id)
        assertTrue { listOf(sponsorBlock) == returnedSponsor }
    }
}