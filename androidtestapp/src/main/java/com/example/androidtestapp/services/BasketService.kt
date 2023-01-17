package com.example.androidtestapp.services

import com.example.androidtestapp.models.MyProduct
import com.example.androidtestapp.models.MyProductsRepository
import com.miam.kmmMiamCore.handler.GroceriesListHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.random.Random

class ExampleState(
    val items: MutableList<MyProduct> = mutableListOf(),
    var recipeCount: Int = 0
)


class MyBasketService: CoroutineScope by CoroutineScope(Dispatchers.Main), KoinComponent {

    private val _RetailerBasketSubject: MutableStateFlow<ExampleState> = MutableStateFlow(ExampleState())

    private fun initFakeBasket() {
        val initialBasket = mutableListOf<MyProduct>()
        for (i in 0..3) {
            val randomElement =
                initialBasket.add(MyProductsRepository.getRandomProduct().copy(quantity = Random.nextInt(1, 4)))
        }

        _RetailerBasketSubject.value.items.addAll(initialBasket)
        launch {
            _RetailerBasketSubject.emit(_RetailerBasketSubject.value)
        }
    }

    init {
        initFakeBasket()
    }

    fun getBasketFlow(): StateFlow<ExampleState> {
        return _RetailerBasketSubject.asStateFlow()
    }

    fun getBasket(): MutableStateFlow<ExampleState> {
        return _RetailerBasketSubject
    }

    fun pushProduct() {
        launch {
            val randomElement = MyProductsRepository.getRandomProduct()
            _RetailerBasketSubject.value.items.add(
                randomElement.copy(
                    quantity = Random.nextInt(
                        1,
                        4
                    )
                )
            )
            _RetailerBasketSubject.emit(
                ExampleState(
                    _RetailerBasketSubject.value.items,
                    _RetailerBasketSubject.value.recipeCount
                )
            )
        }
    }

    fun removeProduct() {
        launch {
            if (_RetailerBasketSubject.value.items.isNotEmpty()) {
                _RetailerBasketSubject.value.items.removeAt(Random.nextInt(_RetailerBasketSubject.value.items.size))
                _RetailerBasketSubject.emit(
                    ExampleState(
                        _RetailerBasketSubject.value.items,
                        _RetailerBasketSubject.value.recipeCount
                    )
                )
            }
        }
    }

    fun flushRecipes() {
        GroceriesListHandler.resetGroceriesList()
    }

}