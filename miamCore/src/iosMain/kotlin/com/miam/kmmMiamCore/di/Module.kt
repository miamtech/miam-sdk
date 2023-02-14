package com.miam.kmmMiamCore.di

import com.miam.kmmMiamCore.domain.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MainDispatcher() }
}