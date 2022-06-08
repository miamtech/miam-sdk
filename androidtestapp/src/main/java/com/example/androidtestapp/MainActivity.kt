package com.example.androidtestapp

import android.annotation.SuppressLint
import android.content.Context

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.di.KoinInitializer
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.myMeal.MyMeal
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePage
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.di.initKoin
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandlerInstance
import com.miam.kmm_miam_sdk.handler.ContextHandlerInstance
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler
import com.miam.kmm_miam_sdk.handler.UserHandler
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.ui.components.tag.Tag

import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

import kotlin.random.Random

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(InternalCoroutinesApi::class)
class MainActivity : ComponentActivity(), KoinComponent,  CoroutineScope by CoroutineScope(
    Dispatchers.Main
) {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in main activity $exception ${exception.stackTraceToString()}")
    }

    private val retailerBasketSubject : MutableStateFlow<ExampleState> = MutableStateFlow(ExampleState())
    private lateinit var basketHandler: BasketHandler

    private fun initMiam() {
        initKoin{
            androidContext(this@MainActivity)
            modules(
                KoinInitializer.miamModuleList
            )
        }

        basketHandler = BasketHandlerInstance.instance
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
        PointOfSaleHandler.setSupplierOrigin("www.coursesu.com")
        UserHandler.updateUserId("ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
    }

    private val recipeloader:  @Composable () -> Unit = { Box(
        Modifier
            .size(40.dp)
            .background(Color.Blue)) }

    private val recipeFunctionTemplateVariable: @Composable (recipe: Recipe, vmRecipe: RecipeViewModel, look : () -> Unit, buy: () -> Unit) -> Unit =
        { recipe: Recipe, vmRecipe: RecipeViewModel,look : () -> Unit, buy: () -> Unit ->
            Column() {
                Clickable(
                    onClick = {look()},
                    children = {
                        Image(
                            painter = rememberImagePainter(recipe.attributes!!.mediaUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "time"
                    )
                    Text(text = recipe.totalTime)
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.Person,
                        contentDescription = "person"
                    )
                    Text(text = recipe.attributes!!.numberOfGuests.toString())
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.School,
                        contentDescription = "hat"
                    )
                    Text(text ="Difficulté  ${recipe.difficultyLabel}")
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Text(text = recipe.attributes!!.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMiam()
        initFakeBasket()
        setContent {
            var isFavoritePage by remember { mutableStateOf(false) }
            var isTagPage by remember { mutableStateOf(false) }
            Column() {
                Row(){
                    Button(onClick = {
                        isFavoritePage= !isFavoritePage
                        isTagPage = false
                    }) {
                        Text("Toggle favorite")
                    }
                    Button(onClick = {
                        isTagPage= !isTagPage
                        isFavoritePage= false}) {
                        Text("Toggle tags")
                    }
                }

                if(isFavoritePage){
                    FavoritePage(this@MainActivity).Content()
                } else if(isTagPage) {
                    if (ContextHandlerInstance.instance.isReady()) {
                        val tag = Tag(this@MainActivity)
                        val items = retailerBasketSubject.asStateFlow().collectAsState().value.items
                        if (items.size > 0) {
                            tag.bind(items[items.size - 1].id)
                            tag.Content()
                        }

                    }
                }
                   else {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        content(retailerBasketSubject)
                        recipes(this@MainActivity)
                    }
                }
            }
        }
        initTemplate()
    }

    @Composable
    fun content(retailerBasketSubject : MutableStateFlow<ExampleState>){

        val state = retailerBasketSubject.asStateFlow().collectAsState()
        Column(
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
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(16.dp))) {
                        Box(
                            Modifier
                                .background(Color.Gray)
                                .padding(vertical = 4.dp, horizontal = 8.dp) ,
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
        }
    }

    @Composable
    fun recipes(context: Context) {
        val recipe1 =  RecipeView(context)
        val recipe2 =  RecipeView(context)
        val recipe3 =  RecipeView(context)

        recipe1.bind(recipeId = "305")
        recipe2.bind(criteria = RandomCriteria())
        recipe3.bind(recipeId = "1")

        Column {
            MyMeal(context).Content()
            recipe1.Content()
            recipe2.Content()
            recipe3.Content()
        }
    }

    private fun initTemplate(){
   /*     Template.recipeCardTemplate = recipeFunctionTemplateVariable
        Template.recipeLoaderTemplate = recipeloader*/
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
        launch(coroutineHandler) {
            retailerBasketSubject.emit(retailerBasketSubject.value)
        }
    }

    private fun setListenToRetailerBasket(basketHandler: BasketHandler) {
        basketHandler.listenToRetailerBasket = ::initBasketListener
        basketHandler.pushProductsToMiamBasket(retailerBasketSubject.value.items.map { product -> coursesUProductTORetailerProduct(product) })
    }

    private fun initBasketListener() {
        launch(coroutineHandler) {
            retailerBasketSubject.collect {
                LogHandler.debug("Demo basket emit")
                basketHandler.pushProductsToMiamBasket(it.items.map { product -> coursesUProductTORetailerProduct(product) })
            }
        }
    }

    private fun coursesUProductTORetailerProduct(product: CoursesUProduct): RetailerProduct {
        return RetailerProduct(product.id, product.quantity, product.name)
    }

    private fun pushProduct(){
        launch(coroutineHandler) {
            val randomElement = productSampleCoursesU.random()
            retailerBasketSubject.value.items.add(randomElement.copy(quantity = Random.nextInt(1,4)))
            retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items))
        }
    }

    private fun removeProduct(){
        launch(coroutineHandler) {
            if(retailerBasketSubject.value.items.isNotEmpty()) {
                retailerBasketSubject.value.items.removeAt(Random.nextInt(retailerBasketSubject.value.items.size))
                retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items))
            }
        }
    }

    private fun setPushProductToBasket(basketHandler: BasketHandler) {
        basketHandler.pushProductsToRetailerBasket = ::pushProductToRetailer
    }

    private fun pushProductToRetailer(coursesUProducts: List<RetailerProduct>){

        coursesUProducts.forEach { rp ->
          val productToUpdateIdx =  retailerBasketSubject.value.items.indexOfFirst { it.id == rp.retailerId }
            if(productToUpdateIdx == -1){
                retailerBasketSubject.value.items.add(CoursesUProduct(rp.retailerId, rp.name?: "", rp.quantity, 0.0, "id_" + rp.retailerId))
            } else if( rp.quantity == 0) {
                retailerBasketSubject.value.items.removeAt(productToUpdateIdx)
            } else {
                retailerBasketSubject.value.items[productToUpdateIdx] = retailerBasketSubject.value.items[productToUpdateIdx].copy(quantity = rp.quantity)
            }

        }
        launch(coroutineHandler) {
            retailerBasketSubject.emit(ExampleState(retailerBasketSubject.value.items))
        }
    }

    companion object{
        val productSampleCoursesU = listOf(
            CoursesUProduct("12726","Farine de blé T45 FRANCINE, 1k",1,0.88, "id_12726"),
            CoursesUProduct("484202","Lait UHT entier U, 6x1L",1,5.46, "id_484202"),
            CoursesUProduct("809586","Mascarpone GALBANI - 250g",1,2.14, "id_809586"),
            CoursesUProduct("970417","Beurre doux U, 125",1,2.12, "id_970417"),
            CoursesUProduct("1298293","Sucre en morceaux prédécoupé n°4 DADDY, 1kg",1,1.35, "id_1298293"),
            CoursesUProduct("1922350","Oeufs Plein air ELEVEURS ENGAGES L'OEUF DE NOS VILLAGES - Boîte de 12",1,3.23, "id_1922350"),
            CoursesUProduct("1941111","Chocolat noir bio 75% Pérou ALTER ECO - Tablette 100g",1,1.95, "id_1941111"),
            CoursesUProduct("2021117","Mandarine Nadorcott à feuilles, calibre 3, catégorie 1, Espagne",1,1.79, "id_2021117"),
            CoursesUProduct("2276426","Sucre vanillé ALSA, 12 sachets, 90g",1,1.91, "id_2276426"),
            CoursesUProduct("2540700","Levure chimique AlSA, sachet de 8 soit 88g",1,0.62, "id_2540700"),
            CoursesUProduct("3895532","Cassonade fine DADDY, 600g",1,1.95, "id_3895532"),
            CoursesUProduct("4671939","Pain d'épices au miel BROSSARD, 350g",1,1.96, "id_4671939"),
            CoursesUProduct("5068663","Huile de tournesol U, 3l",1,5.99, "id_5068663"),
            CoursesUProduct("5774130","Cannelle moulue U, format petit, 17g",1,0.60,"id_5774130"),
            CoursesUProduct("6134471","Banane Cavendish BIO, calibre P14, catégorie 2, Republique Dominicaine, ruban 5 fruits",1,1.99, "id_6134471"),
            CoursesUProduct("6182231","Beurre de cacahuète creamy MENGUY'S 454g",1,3.99, "id_6182231"),
            CoursesUProduct("352902000000909790","Baguette Triskel",1,1.0, "id_352902000000909790"),
            CoursesUProduct("88455","Emmental français rapé U, 29%mg, 100g",1,0.84, "id_88455"),
            CoursesUProduct("2107653","Lait de coco KARA, 200ml",1,0.94, "id_2107653"),
            CoursesUProduct("5319173","Filet de blanc de poulet U, France, barquette",1,6.06, "id_5319173"),
            CoursesUProduct("6511680","Curry tradition en poudre DUCROS, 53g",1,3.40, "id_6511680"),
        )
    }
}

data class CoursesUProduct(
    val id :String,
    val name: String,
    val quantity :Int,
    val price: Double,
    val identifier: String
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

