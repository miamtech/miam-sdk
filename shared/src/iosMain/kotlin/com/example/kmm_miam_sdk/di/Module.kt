package com.example.kmm_miam_sdk.di

import com.example.kmm_miam_sdk.domain.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MainDispatcher() }
}
