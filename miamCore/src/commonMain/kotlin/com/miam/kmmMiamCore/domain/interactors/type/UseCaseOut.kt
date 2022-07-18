package com.miam.kmmMiamCore.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseOut<out OUT> {
    fun execute(): Flow<OUT>
}