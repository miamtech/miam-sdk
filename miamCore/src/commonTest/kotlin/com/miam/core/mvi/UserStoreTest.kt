package com.miam.core.mvi

import app.cash.turbine.test
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.base.mvi.UserAction
import com.miam.kmmMiamCore.base.mvi.UserEffect
import com.miam.kmmMiamCore.base.mvi.UserState
import com.miam.kmmMiamCore.base.mvi.UserStore
import com.miam.kmmMiamCore.base.mvi.UserStoreImpl
import com.miam.test.StoreTest
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UserStoreTest: StoreTest<UserState, UserAction, UserEffect>() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock lateinit var groceriesListStore: GroceriesListStore
    private val sut: UserStore by lazy { UserStoreImpl(groceriesListStore) }

    @Test
    fun `refresh user with 123 update the state userId`() = runTest {
        // When
        every { groceriesListStore.dispatch(isAny()) } returns Job()
        sut.refreshUser("123")
        // Then
        sut.state.test {
            assertEquals("123", awaitItem().userId)
        }
    }

    @Test
    fun `check same user when update the state userId`() = runTest {
        // When
        every { groceriesListStore.dispatch(isAny()) } returns Job()
        sut.refreshUser("123")
        // Then
        assertTrue { sut.sameUser("123") }
        assertFalse { sut.sameUser("321") }
    }

    @Test
    fun `refresh user with null empty state userId`() = runTest {
        // Given
        every { groceriesListStore.dispatch(isAny()) } returns Job()
        sut.refreshUser("123")
        sut.state.first()
        // When
        sut.refreshUser(null)
        // Then
        sut.state.test {
            assertNull(awaitItem().userId)
        }
    }

    @Test
    fun `check same user when removing state userId`() = runTest {
        // Given
        every { groceriesListStore.dispatch(isAny()) } returns Job()
        sut.refreshUser("123")
        sut.state.first()
        // When
        sut.refreshUser(null)
        // Then
        assertTrue { sut.sameUser(null) }
    }
}
