package com.miam.kmmMiamCore.domain

import kotlinx.coroutines.CoroutineDispatcher

public expect class MainDispatcher() {
    public val dispatcher: CoroutineDispatcher
}