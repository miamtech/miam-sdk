package com.miam.kmmMiamCore.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseInOut<in IN, out OUT> {
    suspend fun execute(param: IN): OUT
}