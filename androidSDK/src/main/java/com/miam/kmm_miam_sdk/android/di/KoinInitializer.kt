package com.miam.kmm_miam_sdk.android.di

import android.content.Context
import com.miam.kmmMiamCore.di.platformModule
import com.miam.kmmMiamCore.di.*
import org.koin.android.ext.koin.androidContext

class KoinInitializer {

    companion object {
        val  miamModuleList = listOf(
            viewModelModule,
            repositoryModule,
            dispatcherModule,
            storeModule,
            platformModule()
        )
        fun init(context: Context){
            initKoin {
                androidContext(context)
                modules(
                    miamModuleList
                )
            }
     }

    }
}