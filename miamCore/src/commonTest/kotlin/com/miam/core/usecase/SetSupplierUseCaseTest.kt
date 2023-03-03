package com.miam.core.usecase


import com.miam.core.sdk.data.repository.SponsorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class SetSupplierUseCaseTest: TestsWithMocks() {

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
    lateinit var supplierRepository: SponsorRepository

    //TODO interface point of sale Store
    //private val ssuct: SetSupplierUseCase by lazy { SetSupplierUseCase(supplierRepository,) }
}