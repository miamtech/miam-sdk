package com.miam.kmmMiamCore.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun execute(): Flow<Unit>
}