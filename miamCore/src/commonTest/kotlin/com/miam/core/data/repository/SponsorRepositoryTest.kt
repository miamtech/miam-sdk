package com.miam.core.data.repository

import com.miam.core.sdk.data.repository.SponsorRepository
import com.miam.core.sdk.data.repository.SponsorRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.datasource.SponsorDataSource
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


class SponsorRepositoryTest: TestsWithMocks() {
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
    lateinit var sponsorDataSource: SponsorDataSource
    private val srt: SponsorRepository by lazy { SponsorRepositoryImp(sponsorDataSource) }

    @Test
    fun `get Sponsor By SponsorId `() = runTest {
        val sponsor = SponsorFakeFactory.create()

        everySuspending { sponsorDataSource.getSponsorById(isAny(), isAny()) } returns sponsor
        val returnedSponsor = srt.getSponsorById(sponsorId = sponsor.id)
        assertTrue { sponsor == returnedSponsor }
    }
}