package com.example.kmm_miam_sdk.android.di

import com.example.kmm_miam_sdk.component.recipeCard.RecipeCardViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { RecipeCardViewModel() }
}