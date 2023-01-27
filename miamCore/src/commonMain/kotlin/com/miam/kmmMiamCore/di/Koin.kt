package com.miam.kmmMiamCore.di

import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.base.mvi.LikeStore
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.base.mvi.UserStore
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmmMiamCore.handler.Basket.BasketHandler
import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.handler.ToasterHandler
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.data.repository.*
import com.miam.kmmMiamCore.services.Analytics
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.UserPreferences
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

public fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            dispatcherModule,
            storeModule,
            servicesModule,
            platformModule()
        )
    }

// IOS
public fun initKoin(): KoinApplication = initKoin {}

public val repositoryModule: Module = module {
    single { PricingRepositoryImp(get()) }
    single { RecipeRepositoryImp(get()) }
    single { RecipeLikeRepositoryImp(get()) }
    single { GroceriesListRepositoryImp(get()) }
    single { PointOfSaleRepositoryImp(get()) }
    single { BasketRepositoryImp(get()) }
    single { BasketEntryRepositoryImp(get()) }
    single { MiamAPIDatasource() }
    single { GroceriesEntryRepositoryImp(get()) }
    single { SupplierRepositoryImp(get()) }
    single { PackageRepositoryImp(get()) }
    single { TagsRepositoryImp(get()) }
}

public val dispatcherModule: Module = module {
    factory { Dispatchers.Default }
}

public val storeModule: Module = module {
    single { UserStore() }
    single { GroceriesListStore() }
    single { BasketStore() }
    single { PointOfSaleStore() }
    single { BasketHandler() }
    single { ContextHandler() }
    single { ToasterHandler }
    single { ItemSelectorViewModel() }
    single { LikeStore() }
}

public val servicesModule: Module = module {
    single { Analytics() }
    single { RouteService() }
    single { UserPreferences() }
    single { SingletonPreferencesViewModel() }
    single { SingletonFilterViewModel() }
}
