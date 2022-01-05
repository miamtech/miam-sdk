package com.miam.kmm_miam_sdk.android.di

import com.miam.kmm_miam_sdk.component.recipeCard.RecipeCardViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { RecipeCardViewModel() }
}