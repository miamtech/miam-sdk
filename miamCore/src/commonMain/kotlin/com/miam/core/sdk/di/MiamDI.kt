package com.miam.core.sdk.di

import com.miam.core.sdk.data.repository.SponsorBlockRepository
import com.miam.core.sdk.data.repository.SponsorBlockRepositoryImp
import com.miam.core.sdk.data.repository.SponsorRepository
import com.miam.core.sdk.data.repository.SponsorRepositoryImp
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.base.mvi.GroceriesListStoreImpl
import com.miam.kmmMiamCore.base.mvi.LikeStore
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.base.mvi.UserStore
import com.miam.kmmMiamCore.base.mvi.UserStoreImpl
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
import com.miam.kmmMiamCore.usecase.SetSupplierUseCase

public object MiamDI {
    // Service
    internal val analyticsService: Analytics by lazy { Analytics() }
    public val routeService: RouteService by lazy { RouteService() }
    internal val userPreferences: UserPreferences by lazy { UserPreferences() }

    // DataSource
    internal val dataSource: MiamAPIDatasource by lazy { MiamAPIDatasource() }

    // Repository
    internal val basketRepository: BasketRepositoryImp by lazy { BasketRepositoryImp(dataSource) }
    internal val basketEntryRepository: BasketEntryRepositoryImp by lazy { BasketEntryRepositoryImp(dataSource) }
    internal val groceriesListRepository: GroceriesListRepositoryImp by lazy { GroceriesListRepositoryImp(dataSource) }
    internal val groceriesEntryRepository: GroceriesEntryRepositoryImp by lazy { GroceriesEntryRepositoryImp(dataSource) }
    internal val packageRepository: PackageRepositoryImp by lazy { PackageRepositoryImp(dataSource) }
    internal val pointOfSaleRepository: PointOfSaleRepositoryImp by lazy { PointOfSaleRepositoryImp(dataSource) }
    internal val pricingRepository: PricingRepositoryImp by lazy { PricingRepositoryImp(dataSource) }
    internal val recipeRepository: RecipeRepositoryImp by lazy { RecipeRepositoryImp(dataSource) }
    internal val recipeLikeRepository: RecipeLikeRepositoryImp by lazy { RecipeLikeRepositoryImp(dataSource) }
    internal val supplierRepository: SupplierRepositoryImp by lazy { SupplierRepositoryImp(dataSource) }
    internal val tagsRepository: TagsRepositoryImp by lazy { TagsRepositoryImp(dataSource) }
    internal val sponsorRepository: SponsorRepository by lazy { SponsorRepositoryImp(dataSource) }
    internal val sponsorBlockRepository: SponsorBlockRepository by lazy { SponsorBlockRepositoryImp(dataSource) }

    // Store
    internal val basketStore: BasketStore by lazy { BasketStore() }
    internal val groceriesListStore: GroceriesListStore by lazy { GroceriesListStoreImpl() }
    internal val likeStore: LikeStore by lazy { LikeStore() }
    internal val pointOfSaleStore: PointOfSaleStore by lazy { PointOfSaleStore() }
    internal val userStore: UserStore by lazy { UserStoreImpl(groceriesListStore) }

    //Use Cases
    internal val setSupplierUseCase: SetSupplierUseCase by lazy { SetSupplierUseCase(supplierRepository, pointOfSaleStore) }

    // ViewModel
    public val itemSelectorViewModel: ItemSelectorViewModel by lazy { ItemSelectorViewModel() }
    public val preferencesViewModel: SingletonPreferencesViewModel by lazy { SingletonPreferencesViewModel() }
    public val recipeFilterViewModel: SingletonFilterViewModel by lazy { SingletonFilterViewModel() }

    // Handler
    public val basketHandler: BasketHandler by lazy { BasketHandler() }
    public val contextHandler: ContextHandler by lazy { ContextHandler() }
    public val toasterHandler: ToasterHandler by lazy { ToasterHandler }
}
