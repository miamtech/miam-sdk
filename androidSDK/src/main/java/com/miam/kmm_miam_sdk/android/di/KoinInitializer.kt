package com.miam.kmm_miam_sdk.android.di

import android.content.Context
import org.koin.core.module.Module

@Deprecated("This is no more useful to work with Miam SDK, you can use the DI framework you'd like.", level = DeprecationLevel.ERROR)
class KoinInitializer {

    companion object {
        val miamModuleList = emptyList<Module>()
        fun init(context: Context) { }
    }
}