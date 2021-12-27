package com.example.kmm_miam_sdk.di

import com.example.kmm_miam_sdk.domain.interactors.GetRecipeUseCase
import com.example.kmm_miam_sdk.network.service.RecipeService
import com.example.kmm_miam_sdk.repository.Repository
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
startKoin {
    appDeclaration()
    modules(
        repositoryModule,
        dispatcherModule,
        useCasesModule,
        platformModule()
    )
}

// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<Repository> { Repository(get()) }
    single<RecipeService> { RecipeService(get(), get()) }

    single {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                    }
                )
            }
        }
    }

    single { "https://api.miam.tech/api/v1/recipes" }
}

val useCasesModule: Module = module {
    factory { GetRecipeUseCase(get()) }

}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}




