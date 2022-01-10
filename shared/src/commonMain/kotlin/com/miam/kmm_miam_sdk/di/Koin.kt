package com.miam.kmm_miam_sdk.di

import com.miam.kmm_miam_sdk.base.mvi.MiamStore
import com.miam.kmm_miam_sdk.domain.interactors.GetRecipeUseCase
import com.miam.kmm_miam_sdk.miam_core.service.MiamService
import com.miam.kmm_miam_sdk.network.service.IngredientServiceOld
import com.miam.kmm_miam_sdk.network.service.RecipeServiceOld
import io.ktor.client.*
import io.ktor.client.engine.cio.*
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
        miamStoreModule,
        platformModule()
    )
}

// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<MiamService> { MiamService(get(), get()) }
    single<RecipeServiceOld> { RecipeServiceOld(get(), get()) }
    single<IngredientServiceOld> { IngredientServiceOld(get(), get()) }

    single {
        HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                    }
                )
            }
        }
    }

    single { "https://api.miam.tech/api/v1/" }
}

val useCasesModule: Module = module {
    factory { GetRecipeUseCase(get()) }

}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val miamStoreModule = module {
    single<MiamStore> { MiamStore() }
}




