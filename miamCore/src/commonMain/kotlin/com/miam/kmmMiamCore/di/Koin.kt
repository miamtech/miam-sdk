package com.miam.kmmMiamCore.di

import com.miam.core.sdk.di.MiamDI
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Deprecated("This is no more useful to work with Miam SDK, you can use the DI framework you'd like.", level = DeprecationLevel.ERROR)
public fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication =
    startKoin { appDeclaration() }

// IOS
@Deprecated("This is no more useful to work with Miam SDK, you can use the DI framework you'd like.", level = DeprecationLevel.ERROR)
public val repositoryModule: Module = module {
    single { MiamDI.pricingRepository }
    single { MiamDI.recipeRepository }
    single { MiamDI.recipeLikeRepository }
    single { MiamDI.groceriesListRepository }
    single { MiamDI.pointOfSaleRepository }
    single { MiamDI.basketRepository }
    single { MiamDI.basketEntryRepository }
    single { MiamDI.dataSource }
    single { MiamDI.groceriesEntryRepository }
    single { MiamDI.supplierRepository }
    single { MiamDI.packageRepository }
    single { MiamDI.tagsRepository }
}

@Deprecated("This is no more useful to work with Miam SDK, you can use the DI framework you'd like.", level = DeprecationLevel.ERROR)
public val dispatcherModule: Module = module {
    factory { Dispatchers.Default }
}

@Deprecated("This is no more useful to work with Miam SDK, you can use the DI framework you'd like.", level = DeprecationLevel.ERROR)
public val storeModule: Module = module {
    single { MiamDI.userStore }
    single { MiamDI.groceriesListStore }
    single { MiamDI.basketStore}
    single { MiamDI.pointOfSaleStore }
    single { MiamDI.basketHandler }
    single { MiamDI.contextHandler }
    single { MiamDI.toasterHandler }
    single { MiamDI.itemSelectorViewModel }
    single { MiamDI.likeStore }
}

@Deprecated("This is no more useful to work with Miam SDK, you can use the DI framework you'd like.", level = DeprecationLevel.ERROR)
public val servicesModule: Module = module {
    single { MiamDI.analyticsService }
    single { MiamDI.routeService }
    single { MiamDI.userPreferences }
    single { MiamDI.preferencesViewModel }
    single { MiamDI.recipeFilterViewModel }
}
