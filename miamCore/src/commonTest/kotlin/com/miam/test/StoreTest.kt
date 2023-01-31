package com.miam.test

import com.miam.kmmMiamCore.base.mvi.Action
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class StoreTest<S: State, A: Action, E: Effect> : TestsWithMocks() {
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }
}
