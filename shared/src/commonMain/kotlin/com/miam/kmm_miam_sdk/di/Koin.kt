package com.miam.kmm_miam_sdk.di

import com.miam.kmm_miam_sdk.base.mvi.MiamStore
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.domain.interactors.GetRecipeUseCase
import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.data.datasource.RecipeDataSource
import com.miam.kmm_miam_sdk.miam_core.data.repository.MiamRepository
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
        bottomSheetModule,
        platformModule()
    )
}

// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<MiamRepository> { MiamRepository(get()) }
    single<RecipeDataSource> { MiamAPIDatasource()}
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

val bottomSheetModule = module {
    single<BottomSheetViewModel> { BottomSheetViewModel() }
}




