package com.miam.kmm_miam_sdk.android.di

import android.content.Context
import com.miam.kmm_miam_sdk.android.BuildConfig
import com.miam.kmm_miam_sdk.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class KoinInitilizer {

    companion object {
        fun init(context: Context){
            initKoin {
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
                androidContext(context)
                modules(
                    viewModelModule
                )
            }
    }
    }
}