package com.miam.kmmMiamCore.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseIn<in IN> {
    suspend fun execute(param: IN): Unit
}