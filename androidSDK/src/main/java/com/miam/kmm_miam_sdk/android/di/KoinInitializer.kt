package com.miam.kmm_miam_sdk.android.di

import android.content.Context
import com.miam.kmm_miam_sdk.di.*
import org.koin.android.ext.koin.androidContext

class KoinInitializer {

    companion object {
        val  miamModuleList = listOf(
            viewModelModule,
            repositoryModule,
            dispatcherModule,
            useCasesModule,
            storeModule,
            platformModule()
        )
        fun init(context: Context){
            initKoin {
                androidContext(context)
                modules(
                    viewModelModule
                )
            }
     }

    }
}