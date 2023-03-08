package com.example.androidtestapp

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidtestapp.models.MyProduct
import com.example.androidtestapp.services.ExampleState
import com.example.androidtestapp.services.MyBasketService
import com.miam.kmmMiamCore.handler.Basket.BasketHandler
import com.miam.kmmMiamCore.handler.Basket.BasketHandlerInstance
import com.miam.kmmMiamCore.handler.ContextHandlerInstance
import com.miam.kmmMiamCore.handler.GroceriesListHandler
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.handler.PointOfSaleHandler
import com.miam.kmmMiamCore.handler.ToasterHandler
import com.miam.kmmMiamCore.handler.UserHandler
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct
import com.miam.kmmMiamCore.services.AnalyticsInstance
import com.miam.kmmMiamCore.usecase.SetSupplierUseCase
import com.miam.kmmMiamCore.usecase.SupplierInfo
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogModifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MiamManager: CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val basketService = MyBasketService()
    private val retailerBasketSubject = basketService.getBasket()
    private var basketHandler: BasketHandler = BasketHandlerInstance.instance

    init {

        LogHandler.info("Are you ready ? ${ContextHandlerInstance.instance.isReady()}")
        launch {
            ContextHandlerInstance.instance.observeReadyEvent().collect { it ->
                LogHandler.info("I know you are readdy !!! $it")
            }
        }
        launch { AnalyticsInstance.instance.observeSideEffect().collect { LogHandler.info("Analytics $it") } }

        setListenToRetailerBasket(basketHandler)
        setPushProductToBasket(basketHandler)
        // this set on inexisting pos will be cancelled by second one
        PointOfSaleHandler.setSupplierOrigin("miam.test")
        PointOfSaleHandler.updateStoreId("miam_test")
        SetSupplierUseCase.create.invoke(SupplierInfo(14))
        UserHandler.updateUserId("test_user")
        UserHandler.setProfilingAllowed(true)
        UserHandler.setEnableLike(true)
        CatalogModifier.categoryListContainer = Modifier.padding(bottom = 500.dp)
        ToasterHandler.setOnAddRecipeText("Well done !")
        ToasterHandler.setOnLikeRecipeText("Good taste !")



        launch {
            GroceriesListHandler.getRecipeCountChangeFlow().collect {
                println("recipes count by flow : ${retailerBasketSubject.value.recipeCount} ")
                retailerBasketSubject.emit(
                    ExampleState(
                        retailerBasketSubject.value.items,
                        it.newRecipeCount
                    )
                )
            }
        }
        GroceriesListHandler.onRecipeCountChange {
            println("recipes count by callback : ${retailerBasketSubject.value.recipeCount} ")
            launch {
                retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items, it))
            }
        }
    }

    private fun setListenToRetailerBasket(basketHandler: BasketHandler) {
        basketHandler.setListenToRetailerBasket(::initBasketListener)
        basketHandler.pushProductsToMiamBasket(retailerBasketSubject.value.items.map { product ->
            myProductTORetailerProduct(
                product
            )
        })
    }

    private fun initBasketListener() {
        launch {
            retailerBasketSubject.collect {
                LogHandler.debug("Demo basket emit")
                basketHandler.pushProductsToMiamBasket(it.items.map { product ->
                    myProductTORetailerProduct(
                        product
                    )
                })
            }
        }
    }

    private fun myProductTORetailerProduct(product: MyProduct): RetailerProduct {
        return RetailerProduct(product.id, product.quantity, product.name, product.identifier)
    }

    private fun setPushProductToBasket(basketHandler: BasketHandler) {
        basketHandler.setPushProductsToRetailerBasket(::pushProductToRetailer)
    }

    private fun pushProductToRetailer(coursesUProducts: List<RetailerProduct>) {
        coursesUProducts.forEach { rp ->
            val productToUpdateIdx =
                retailerBasketSubject.value.items.indexOfFirst { it.id == rp.retailerId }
            if (productToUpdateIdx == -1) {
                retailerBasketSubject.value.items.add(
                    MyProduct(
                        rp.retailerId,
                        rp.name ?: "item-${rp.retailerId}",
                        rp.quantity,
                        0.0,
                        "id_" + rp.retailerId
                    )
                )
            } else if (rp.quantity == 0) {
                retailerBasketSubject.value.items.removeAt(productToUpdateIdx)
            } else {
                retailerBasketSubject.value.items[productToUpdateIdx] =
                    retailerBasketSubject.value.items[productToUpdateIdx].copy(quantity = rp.quantity)
            }

        }
        launch {
            retailerBasketSubject.emit(
                ExampleState(
                    retailerBasketSubject.value.items,
                    retailerBasketSubject.value.recipeCount
                )
            )
        }
    }
}