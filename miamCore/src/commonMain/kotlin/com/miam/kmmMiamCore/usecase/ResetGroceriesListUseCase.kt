package com.miam.kmmMiamCore.usecase

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.UseCase
import com.miam.kmmMiamCore.miam_core.data.repository.GroceriesListRepository
import com.miam.kmmMiamCore.services.Analytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

public class ResetGroceriesListUseCase(
private val groceriesListRepository: GroceriesListRepository = MiamDI.groceriesListRepository,
private val analyticsService: Analytics = MiamDI.analyticsService
): UseCase<Unit>, CoroutineScope by MainScope() {
    override fun invoke() {
        launch {
            analyticsService.sendEvent(Analytics.EVENT_RECIPE_RESET, "", Analytics.PlausibleProps())
            val groceriesList = groceriesListRepository.reset()
            SetGroceriesListUseCase().invoke(groceriesList)
        }
    }
}
