package com.example.kmm_miam_sdk.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseInOut<in IN, out OUT> {
    fun execute(param: IN): Flow<OUT>
}