package com.miam.kmm_miam_sdk.android.di

import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { RecipeViewModel() }
}