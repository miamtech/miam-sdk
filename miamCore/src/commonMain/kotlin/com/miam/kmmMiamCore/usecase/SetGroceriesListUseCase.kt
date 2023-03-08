package com.miam.kmmMiamCore.usecase

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.ParameterisedUseCase
import com.miam.kmmMiamCore.base.mvi.BasketAction
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.GroceriesListStore
import com.miam.kmmMiamCore.miam_core.model.GroceriesList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

internal class SetGroceriesListUseCase(
    private val basketStore: BasketStore = MiamDI.basketStore,
    private val groceriesListStore: GroceriesListStore = MiamDI.groceriesListStore
): ParameterisedUseCase<GroceriesList, Unit>, CoroutineScope by MainScope() {
    override fun invoke(input: GroceriesList) {
        launch {
            groceriesListStore.setGroceriesList(input)
            basketStore.dispatch(BasketAction.RefreshBasket)
        }
    }
}