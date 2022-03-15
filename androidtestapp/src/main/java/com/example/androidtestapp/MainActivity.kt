package com.example.androidtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.miam.kmm_miam_sdk.android.di.KoinInitilizer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView

import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler
import com.miam.kmm_miam_sdk.handler.UserHandler

import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import org.koin.core.component.KoinComponent

import kotlin.random.Random

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(InternalCoroutinesApi::class)
class MainActivity : AppCompatActivity(), KoinComponent,  CoroutineScope by CoroutineScope(
    Dispatchers.Main) {


    private val retailerBasketSubject : MutableSharedFlow<MutableList<CoursesUProduct>> = MutableSharedFlow()
    private val customerSubject : MutableSharedFlow< MutableList<CoursesUCustomer>> = MutableSharedFlow()
    private val PointOfSaleSubject : MutableSharedFlow< MutableList<CoursesUPointOfSale>> = MutableSharedFlow()
    private val basketHandler = BasketHandler()
    private var list : MutableList<CoursesUProduct> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KoinInitilizer.init(this)
        setListenToRetailerBasket(basketHandler)
        PointOfSaleHandler.updateStoreId("35290")
        PointOfSaleHandler.setSupplier(7)
        UserHandler.updateUserId("ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        initFakeBasket()
        setContentView(R.layout.activity_main)
        val recipeView = findViewById(R.id.miam2) as RecipeView
        recipeView.idRecipe = 9075
        val recipeView2 = findViewById(R.id.miam3) as RecipeView
        recipeView2.idRecipe = 1
        val buttonAdd = findViewById(R.id.Add) as Button
        buttonAdd.setOnClickListener {
            pushProduct()
        }
        val buttonRemove = findViewById(R.id.Remove) as Button
        buttonRemove.setOnClickListener {
            removeProduct()
        }
    }

    private fun initFakeBasket(){
        val initialBasket = mutableListOf<CoursesUProduct>()
        for (i in 0..3) {
            val randomElement = productSampleCoursesU.random()
            initialBasket.add(randomElement.copy(quantity = Random.nextInt(1,4)))
        }
        launch {
            list= initialBasket
            retailerBasketSubject.emit(initialBasket)
        }
    }

    private fun setListenToRetailerBasket(basketHandler: BasketHandler) {
        basketHandler.listenToRetailerBasket = ::initBasketListener
    }

    private fun initBasketListener(callback: (products: List<RetailerProduct>) -> Unit) {
        launch {
            retailerBasketSubject.collect {
                print("DEMO basket EMT")
                callback(it.map { product -> coursesUProductTORetailerProduct(product)  })
            }
        }
    }

    private fun coursesUProductTORetailerProduct(product: CoursesUProduct): RetailerProduct {
        return RetailerProduct(product.id,product.quantity)
    }

    private fun pushProduct(){
        launch {
            val randomElement = productSampleCoursesU.random()
            list.add(randomElement.copy(quantity = Random.nextInt(1,4)))
            retailerBasketSubject.emit(list)
        }
    }

    private fun removeProduct(){
        launch {
            list.removeAt(Random.nextInt(list.size))
            retailerBasketSubject.emit(list)
        }
    }

    companion object{
        val productSampleCoursesU = listOf(
            CoursesUProduct("12726","Farine de blé T45 FRANCINE, 1k",1,0.88),
            CoursesUProduct("484202","Lait UHT entier U, 6x1L",1,5.46),
            CoursesUProduct("809586","Mascarpone GALBANI - 250g",1,2.14),
            CoursesUProduct("970417","Beurre doux U, 125",1,2.12),
            CoursesUProduct("1298293","Sucre en morceaux prédécoupé n°4 DADDY, 1kg",1,1.35),
            CoursesUProduct("1922350","Oeufs Plein air ELEVEURS ENGAGES L'OEUF DE NOS VILLAGES - Boîte de 12",1,3.23),
            CoursesUProduct("1941111","Chocolat noir bio 75% Pérou ALTER ECO - Tablette 100g",1,1.95),
            CoursesUProduct("2021117","Mandarine Nadorcott à feuilles, calibre 3, catégorie 1, Espagne",1,1.79),
            CoursesUProduct("2276426","Sucre vanillé ALSA, 12 sachets, 90g",1,1.91),
            CoursesUProduct("2540700","Levure chimique AlSA, sachet de 8 soit 88g",1,0.62),
            CoursesUProduct("3895532","Cassonade fine DADDY, 600g",1,1.95),
            CoursesUProduct("4671939","Pain d'épices au miel BROSSARD, 350g",1,1.96),
            CoursesUProduct("5068663","Huile de tournesol U, 3l",1,5.99),
            CoursesUProduct("5774130","Cannelle moulue U, format petit, 17g",1,0.60),
            CoursesUProduct("6134471","Banane Cavendish BIO, calibre P14, catégorie 2, Republique Dominicaine, ruban 5 fruits",1,1.99),
            CoursesUProduct("6182231","Beurre de cacahuète creamy MENGUY'S 454g",1,3.99),
            CoursesUProduct("352902000000909790","Baguette Triskel",1,1.0),
        )
    }

}

data class CoursesUProduct(
    val id :String,
    val name: String,
    val quantity :Int,
    val price: Double
)

data class CoursesUCustomer(
    val id:String
)

data class CoursesUPointOfSale(
    val identifier: String
)