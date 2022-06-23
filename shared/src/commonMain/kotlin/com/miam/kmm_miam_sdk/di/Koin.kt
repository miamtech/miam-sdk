package com.miam.kmm_miam_sdk.di

import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.base.mvi.GroceriesListStore
import com.miam.kmm_miam_sdk.base.mvi.PointOfSaleStore
import com.miam.kmm_miam_sdk.base.mvi.UserStore
import com.miam.kmm_miam_sdk.component.itemSelector.ItemSelectorViewModel
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler
import com.miam.kmm_miam_sdk.handler.ContextHandler
import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.data.repository.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
startKoin {
    appDeclaration()
    modules(
        repositoryModule,
        dispatcherModule,
        storeModule,
        platformModule()
    )
}

// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single { PricingRepositoryImp(get())}
    single { RecipeRepositoryImp(get()) }
    single { GroceriesListRepositoryImp(get()) }
    single { PointOfSaleRepositoryImp(get()) }
    single { BasketRepositoryImp(get()) }
    single { BasketEntryRepositoryImp(get())}
    single { MiamAPIDatasource()}
    single { GroceriesEntryRepositoryImp(get())}
    single { SupplierRepositoryImp(get()) }
    single { PackageRepositoryImp(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val storeModule = module {
    single { UserStore() }
    single { GroceriesListStore() }
    single { BasketStore() }
    single { PointOfSaleStore() }
    single { BasketHandler() }
    single { ContextHandler() }
    single { ItemSelectorViewModel() }
}
