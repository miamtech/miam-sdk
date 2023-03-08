package com.miam.core.usecase

import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.miam_core.model.GroceriesList
import com.miam.kmmMiamCore.miam_core.model.fake.GroceriesListFakeFactory
import com.miam.kmmMiamCore.usecase.SetGroceriesListUseCase
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

class SetGroceriesListUseCaseTest: TestsWithMocks() {
    @Mock
    lateinit var groceriesListStore: GroceriesListStore

    @Mock
    lateinit var basketStore: BasketStore

    private val setGroceriesListUseCase by lazy {
        SetGroceriesListUseCase(
            basketStore,
            groceriesListStore
        )

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
    fun `call use case refresh stores`() = runBlocking {
        val groceriesList = GroceriesListFakeFactory.create("123456")
        everySuspending { groceriesListStore.setGroceriesList(isAny()) } returns Unit
        every { groceriesListStore.getGroceriesList() } returns groceriesList
        every { basketStore.getBasket() } returns null

        setGroceriesListUseCase.invoke(groceriesList)

        assertTrue { groceriesListStore.getGroceriesList() == groceriesList }
    }
}