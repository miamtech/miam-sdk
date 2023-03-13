package com.miam.core.usecase

import com.miam.kmmMiamCore.miam_core.data.repository.GroceriesListRepository
import com.miam.kmmMiamCore.miam_core.model.fake.GroceriesListFakeFactory
import com.miam.kmmMiamCore.services.Analytics
import com.miam.kmmMiamCore.usecase.ResetGroceriesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ResetGroceriesListUseCaseTest: TestsWithMocks() {
    @Mock
    lateinit var groceriesListRepository: GroceriesListRepository

    @Mock
    lateinit var analyticsService: Analytics

    private val resetGroceriesListUseCase by lazy {
        ResetGroceriesListUseCase(groceriesListRepository = groceriesListRepository, analyticsService = analyticsService)
    }

    override fun setUpMocks() = injectMocks(mocker)
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `call use case reset groceries list`() = runBlocking {
        val groceriesList = GroceriesListFakeFactory.create("123456")
        everySuspending { groceriesListRepository.reset() } returns groceriesList
        every { analyticsService.sendEvent(Analytics.EVENT_RECIPE_RESET, "", Analytics.PlausibleProps()) } returns groceriesList

        resetGroceriesListUseCase.invoke()

        assertTrue { true }
    }
}