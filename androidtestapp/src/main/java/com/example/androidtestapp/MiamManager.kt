package com.example.androidtestapp

import com.example.androidtestapp.models.MyProduct
import com.example.androidtestapp.services.ExampleState
import com.example.androidtestapp.services.MyBasketService
import com.miam.kmmMiamCore.handler.Basket.BasketHandler
import com.miam.kmmMiamCore.handler.Basket.BasketHandlerInstance
import com.miam.kmmMiamCore.handler.ContextHandlerInstance
import com.miam.kmmMiamCore.handler.GroceriesListHandler
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.handler.PointOfSaleHandler
import com.miam.kmmMiamCore.handler.UserHandler
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MiamManager: CoroutineScope by CoroutineScope(Dispatchers.Main), KoinComponent {

    private val basketService: MyBasketService by inject()
    private val retailerBasketSubject = basketService.getBasket()
    private var basketHandler: BasketHandler = BasketHandlerInstance.instance

    init {

        LogHandler.info("Are you ready ? ${ContextHandlerInstance.instance.isReady()}")
        launch {
            ContextHandlerInstance.instance.observeReadyEvent().collect { it ->
                LogHandler.info("I know you are readdy !!! $it")
            }
        }

        setListenToRetailerBasket(basketHandler)
        setPushProductToBasket(basketHandler)
        // this set on inexisting pos will be cancelled by second one
        PointOfSaleHandler.updateStoreId("35290")
        PointOfSaleHandler.setSupplier(7)
        PointOfSaleHandler.setSupplierOrigin("app.qualif.coursesu")
        UserHandler.updateUserId("test_user")
        UserHandler.setProfilingAllowed(true)
        UserHandler.setEnableLike(true)
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