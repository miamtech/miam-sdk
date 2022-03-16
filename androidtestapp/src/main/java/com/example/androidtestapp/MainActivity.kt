package com.example.androidtestapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.di.KoinInitilizer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView

import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler
import com.miam.kmm_miam_sdk.handler.UserHandler

import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent

import kotlin.random.Random

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(InternalCoroutinesApi::class)
class MainActivity : ComponentActivity(), KoinComponent,  CoroutineScope by CoroutineScope(
    Dispatchers.Main) {


    private val retailerBasketSubject : MutableStateFlow<ExampleState> = MutableStateFlow(ExampleState())
    private val basketHandler = BasketHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KoinInitilizer.init(this)
        setListenToRetailerBasket(basketHandler)
        setPushProductToBasket(basketHandler)
        PointOfSaleHandler.updateStoreId("35290")
        PointOfSaleHandler.setSupplier(7)
        UserHandler.updateUserId("ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        initFakeBasket()
        setContent {
            content(this.applicationContext,retailerBasketSubject)
        }
    }

    @Composable
    fun content(context: Context, retailerBasketSubject : MutableStateFlow<ExampleState>){

        val state = retailerBasketSubject.asStateFlow().collectAsState()

        val recipe1 =  RecipeView(context)
        val recipe2 =  RecipeView(context)

        recipe1.bind(recipeId = 305)
        recipe2.bind(criteria = RandomCriteria())

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Panier du client", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Total Panier -> ${state.value.items.sumOf { it.price }} €")
            Divider()
            Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { pushProduct() }) {
                    Text(text = "Ajouter")
                }
                Button(onClick = { removeProduct() }) {
                    Text(text = "Retirer")
                }
            }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                state.value.items.forEach{
                    Box(modifier = Modifier.padding(4.dp).clip(RoundedCornerShape(16.dp))) {
                        Box(
                            Modifier
                                .background(Color.Gray).padding(vertical =  4.dp , horizontal = 8.dp) ,
                        ) {
                            Column() {
                                Text(text = it.name, color = Color.White, fontWeight = FontWeight.Bold)
                                Text(text = " X ${it.quantity} ", color = Color.White)
                            }
                        }
                    }
                }
            }
            Divider()
            recipe1.Content()
            recipe2.Content()
        }
    }

    private fun RandomCriteria() :SuggestionsCriteria{
        val randomProductId = mutableListOf<String>()
        for (i in 0..2) {
            val randomElement = productSampleCoursesU.random()
            randomProductId.add(randomElement.id)
        }

       return SuggestionsCriteria(shelfIngredientsIds = randomProductId )
    }

    private fun initFakeBasket(){
        val initialBasket = mutableListOf<CoursesUProduct>()
        for (i in 0..3) {
            val randomElement = productSampleCoursesU.random()
            initialBasket.add(randomElement.copy(quantity = Random.nextInt(1, 4)))
        }

        retailerBasketSubject.value.items.addAll(initialBasket)
        launch {
            retailerBasketSubject.emit(retailerBasketSubject.value)
        }
    }

    private fun setListenToRetailerBasket(basketHandler: BasketHandler) {
        basketHandler.listenToRetailerBasket = ::initBasketListener
    }

    private fun initBasketListener(callback: (products: List<RetailerProduct>) -> Unit) {
        launch {
            retailerBasketSubject.collect {
                print("DEMO basket EMT")
                callback(it.items.map { product -> coursesUProductTORetailerProduct(product)  })
            }
        }
    }

    private fun coursesUProductTORetailerProduct(product: CoursesUProduct): RetailerProduct {
        return RetailerProduct(product.id,product.quantity)
    }

    private fun pushProduct(){
        launch {
            val randomElement = productSampleCoursesU.random()
            retailerBasketSubject.value.items.add(randomElement.copy(quantity = Random.nextInt(1,4)))
            retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items))
        }
    }

    private fun removeProduct(){
        launch {
            if(retailerBasketSubject.value.items.isNotEmpty()) {
                retailerBasketSubject.value.items.removeAt(Random.nextInt(retailerBasketSubject.value.items.size))
                retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items))
            }
        }
    }

    private fun setPushProductToBasket(basketHandler: BasketHandler) {
        basketHandler.pushProductsToBasket = ::pushProductToRetailer

    }

    private fun pushProductToRetailer(coursesUProducts: List<RetailerProduct>){

        coursesUProducts.forEach { rp ->
          val productToUpdateIdx =  retailerBasketSubject.value.items.indexOfFirst { it.id == rp.id }
            if(productToUpdateIdx == -1){
                retailerBasketSubject.value.items.add(CoursesUProduct(rp.id, rp.id, rp.quantity, 0.0))
            } else if( rp.quantity == 0) {
                retailerBasketSubject.value.items.removeAt(productToUpdateIdx)
            } else {
                retailerBasketSubject.value.items[productToUpdateIdx] = retailerBasketSubject.value.items[productToUpdateIdx].copy(quantity = rp.quantity)
            }

        }
        launch {
            retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items))
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
            CoursesUProduct("88455","Emmental français rapé U, 29%mg, 100g",1,0.84),
            CoursesUProduct("2107653","Lait de coco KARA, 200ml",1,0.94),
            CoursesUProduct("5319173","Filet de blanc de poulet U, France, barquette",1,6.06),
            CoursesUProduct("6511680","Curry tradition en poudre DUCROS, 53g",1,3.40),
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

class ExampleState(
     val items: MutableList<CoursesUProduct> = mutableListOf()
)