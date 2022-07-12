package com.miam.kmm_miam_sdk.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun execute(): Flow<Unit>
}