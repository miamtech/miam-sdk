package com.miam.kmm_miam_sdk.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseOut<out OUT> {
    fun execute(): Flow<OUT>
}