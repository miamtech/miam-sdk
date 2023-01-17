package com.example.androidtestapp.di

import com.example.androidtestapp.services.MyBasketService
import org.koin.dsl.module

val myServicesModule = module {
    single { MyBasketService() }
}