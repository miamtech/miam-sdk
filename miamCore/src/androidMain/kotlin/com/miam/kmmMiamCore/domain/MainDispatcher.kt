package com.miam.kmmMiamCore.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

public actual class MainDispatcher {
    public actual val dispatcher: CoroutineDispatcher = Dispatchers.Main
}