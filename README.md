# KMM Miam SDK

[[_TOC_]]
## Introduction

Miam's Kmm sdk provide logic add component for ios and android native app.

But how does it work ?! 🪄

Our SDK is divide in threre main module
  
  - Shared Module
  - Android SDK
  - IOS Framwork

### Shared module

Basicly all the work is done here. The porpuse of our architecture is to share all the logic between Android
and IOS

You'll find in this module our service, our object mapper , our component view Model and our data class.

This module is imported in both android SDK and IOS framwork and you can imagine create your own compoment lib and map them with our view model.


## Android integration (Kotlin)

> Minimum requirement Kotlin version is `1.6.10`
### Initialization

Miam SDK is not web hosted yet, so you first need to download our repository
and then launch a build as production. it'll generate a `miam-sdk-release.aar` in `/androidSDK/build/outputs`

You can then put it in your project in the folder `libs` if you dont have one yet create one.

Add `implementation(name:'miam-SDK-release', ext:'aar')` in the dependencies section of your sub `build.gradle` file.

Our component are using [Jetpack Compose](https://developer.android.com/jetpack/compose?gclsrc=aw.ds&gclid=CjwKCAjwrfCRBhAXEiwAnkmKmWkwGezGLmmfauda5_ACVVNtTVPUw576netuScD2mLnGacjr2cB30RoCC24QAvD_BwE) it require following third party libraries :

```kotlin
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.compose.compiler:compiler:1.0.5")
    implementation("androidx.compose.ui:ui:1.0.5")

    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.5")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.5")

all    // Material Design
    implementation("androidx.compose.material:material:1.0.5")

    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.5")
    implementation("androidx.compose.material:material-icons-extended:1.0.5")

    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.5")

    // async image
    implementation("io.coil-kt:coil-compose:1.3.1")
    implementation ("io.coil-kt:coil-svg:1.3.1")
```

> Use Jetpack is not mandatory it's just our implemation of miam CORE

We are also using our own HttpClient [Ktor]("https://ktor.io/docs/welcome.html") whitch need :

```kotlin
    implementation("io.ktor:ktor-client-android:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-logging:1.6.7")
```

We are also using [Koin]("https://insert-koin.io/") for dependency injection inside of the CORE but it's embed and dont need to add extra dependencies. 

> If you are already using Koin into your project it can cause issues 

 #### Main class

Before using Miam you have configure special Miam function, as we're runing in parallel of your app and to interacting with the basket, the store or the user , you have to provide to Miam a way to interact with them.

For that we are using `StoreHandler`, `UserHandler` and `BasketHandler` class. They are singleton you only have to provide setting once.

One way of achevied it is to create in your app a class `Miam`
witch 'll a sigleton to and be availabe across your app.

```kotlin
class Miam() {

     init {
       KoinInitilizer.init(context = yourAppContext)
     }

    companion object {
        private var instance: Miam? = null
        fun getInstance(): Miam {
            if (instance != null) return instance!!
            instance = Miam()
            return instance!!
        }
    }
}
```
Inside the `init` function of this class we can now configure our three handlers

#### Connection to Miam API

#### User

To use Miam you have to be logged and linked to a valid Store

To alow miam to recognize your custommer we need at least a custommer id

it can be set by using :

```kotlin
Miam.getInstance().UserHandler.updateUserId(user.id)
```

> user id is a String

We have to kown if the user is logging in,logging out or has changed, so to achived it we recomend to use a observable as [MutableSharedFlow]("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-mutable-shared-flow/") and call our function in the call back.

```kotlin 
class Miam() {

   init {
     [ ... ]

     yourUser.collect { user ->
        UserHandler.updateUserId(user.id)
     }

    [ ... ]
   }

   [ ... ]
}
```

#### Store and Retailer

As every retailer have there own product price and store, you'll have to passe to Miam your Retailer Id.
You can find your Id by using a REST client and get :

`https://api.miam.tech/api/v1/suppliers/?filter[active]=true,false`

then pass the config to miam 


```kotlin 
class Miam() {

   init {
      PointOfSaleHandler.setSupplier(Retailer_ID)
      PointOfSaleHandler.setSupplierOrigin("https://yourOrigin.com")
   }

   [ ... ]
}
```

> Your origin must be different between development and production environment

Now as user we have to set Store Id by using

```kotlin
Miam.getInstance().updateStoreId(store.id)
```

> store id is a String


Miam also provide an `isAvailable` option that will enable or disable the SDK 

```kotlin

 private const val availableStoreIdLists = listof("454", "652")

 private fun isActiveOnStore(): Boolean {
      return availableStoreIdLists.contains(getCurrentStore().id)   
  }

 // config miam

 PointOfSaleHandler.isAvailable = ::isActiveOnStore

```

#### Basket synchronization

For a mapping purpose we'll create a function that will tranform your product into  `RetailerProduct`

```kotlin
data class RetailerProduct(val retailerId :String , val quantity: Int, val name: String?)
```

```kotlin
private fun yourProductTORetailerProduct(Products: List<YourProduct>): List<RetailerProduct> {
        return YourProduct.map { yourP ->
            RetailerProduct(
                yourP.id,
                yourP.quantity,
                yourP.name
            )
        }
    }    
```

As Miam is only going to listen to your basket if it's ready or avaible
you have to passe a function to `BasketHandler` with the flowing signature: 
`(callback : (products: List<RetailerProduct>) -> Unit) -> Unit`

```kotlin
class Miam() {

   private val basketHandler: BasketHandler = BasketHandler()

   init {
     basketHandler.listenToRetailerBasket = ::initBasketListener

    [ ... ]
   }

  private fun initBasketListener(
        callback: (
            products: List<RetailerProduct>
        ) -> Unit
    ){
        yourBasket.collect { basket ->

          // call the signature's callback here
          callback(yourProductTORetailerProduct(basket.productsList))
      }
    }
  
   [ ... ]
}
```

Next will add a function to push product update or delete product in your basket, with the following signature `(products: List<RetailerProduct>) -> Unit`. This function will be call if miam want to add 
update or delete a product.  

```kotlin
class Miam() {

   private val basketHandler: BasketHandler = BasketHandler()

   init {
      basketHandler.pushProductsToBasket = ::pushProductsToYourBasket

    [ ... ]
   }

   private fun pushProductsToYourBasket (products: List<RetailerProduct>){
     for(product in Products) { 
       if(it.quantity <= 0){
         yourDeleteFunction(it)
       } else if (yourTestFunctionAlreadyInBasket(it.id)){
         yourUpdateFunction(it)
       } else {
         yourAddFunction(it)
       }
     }
   }
   
```

And finaly we have to detect payment to reset Miam's basket


```kotlin
class Miam() {

   private val basketHandler: BasketHandler = BasketHandler()

   init {
      basketHandler.paymentTotal = fun(): Double { 
        return getYourBasketTotalPaid() 
      }
    [ ... ]
   }

   fun confirmBasket() {
        basketHandler.handlePayment()
  }
}
```

You'll have to call `Miam.getInstance().confirmBasket()` in your app at the end of your client order

### Components injection

Currently there is only one component to inject : `recipeCard` if you want to use our SDK
you can both implement it in a app using compose or in a classique one

#### With Jetpack (preferred)

First init an object recipe with current context

```kotlin
  val recipe =  RecipeView(this@MainActivity)
```

Then you can choose between fixed recipe or suggestion


```kotlin
        val recipe1 =  RecipeView(this@MainActivity)
        val recipe2 =  RecipeView(this@MainActivity)

        // will load recipe with given id
        recipe1.bind(recipeId = 305)

        // will load a suggested recipe
        recipe2.bind(
          criteria = SuggestionsCriteria(
            shelfIngredientsIds= listOf(
              "your_local_id_1",
              "your_local_id_2"     
            )
          )
        )
        
/** Already in MIAM */
data class SuggestionsCriteria(
    val shelfIngredientsIds: List<String>? = null,
    val currentIngredientsIds: List<String>? = null,
    val basketIngredientsIds: List<String>? = null,
    val groupId: String? = null
)
```
then just add it into your compose function :

```kotlin
        val recipe1 =  RecipeView(this@MainActivity)
        val recipe2 =  RecipeView(this@MainActivity)

        [...]

        setContent {
          Column {
            recipe1.Content()
            recipe2.Content()
          }
        }
```
#### With XML injection

If you're using a regular app you can inject miam recipecard in yout XML view

```xml
<com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

And then bind property like this 

```kotlin
 
 val miamCard = R.layout.item_miam as RecipeView
 miamCard.bind(criteria = criteria)

```
> you can achived it by using an `id` and a `findViewById` too
### Styling
 
 There is two level of customization :  
  - top level that'll override the whole application
  - component level that 'll overrive for a specific component

  > if you override both level, compoment'll have it's custom style
  > other'll get top level style

  > if you don't override a property you'll have miam's default setted

  At this point there no more differents between app using jepack compose or not.

#### Colors

> Not available at component level yet

  you can override a color by :

  ```kotlin
    Colors.primary = Color( 0xFF44D6B3)
  ``` 

> `0x` means hexa , `FF` stand for opacity , `44D6B3` is the color

List of color you can override

| Name |  Default value | Use |
 |:-------------|:-------------:|:-------------:|
 | primary | `#037E92` | 
 | secondary | `#209B8F` | 
 | ternary | `#E61845` | 
 | success | `#44D6B3` | 
 | info | `#44D6B3` | 
 | warning | `#FFDAA3` | 
 | danger | `#F47F7A` | 
 | grey | `#676767` | 
 | white | `#FAFCFE` | 
 | unpureWhite | `#fefefe` | 
 | black | `#252525` |

#### Wording

All text comme from miam object `Text`, you can override a text in the whole application by 
setting a value : 

```kotlin
  Text.alreadyInCart = "ajoutée"
```

> if you override this property in a lower level the lower level win

You can find all available custom texts in `androidSDK/src/main/ressource/text.kt`
#### Typography

> Comming soon

#### Icon / Image

All text comme from miam object `Image`, you can override a text in the whole application by 
setting a value : 

```kotlin
  Text.alreadyInCart = "ajoutée"
```

> if you override this property in a lower level the lower level win

#### Dimension

> Comming soon

#### Component (example: customize the RecipeCard component)

Each time a customization is a available for a component you'll find a file starting by the name a of the component and ending by customization category like : `recipeCardStyle.kt` 

You can override property by setting a new value :

```kotlin
 RecipeCardText.alreadyInCart = "ok c'est dedans"
```

To customise a icon passe your ressource to the right property :

```kotlin
RecipeCardImage.time = R.drawable.your_time_icon
```

You can override style too,for that we are using  [Modifier]("https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier") several container Modifier are exposed and alow you to change there container style , you'll find the complet list in `ComponentNameStyle.kt`

```kotlin
   // you can set those option in Miam class 
   RecipeCardStyle.image = Modifier.height(245.dp).width(245.dp).clip(shape = CircleShape)
```

| *before* | *after* | 
|:-------------|:-------------:|
|![alt text](pic/defaultCard.png "default card") | ![alt text](pic/alteredStyle.png "custom card")|


  ### Planned improvements
  #### Architecture readability
  - Reduce `Flow` use accrose SDK
  #### Deployment and hosting
  - Hosted dependency 
  #### More components styling

Component available for low level customization :

| Name | Style | Color | Icon | Text | 
|:-------------|:-------------:|:-------------:|:-------------:|:-------------:|
| Recipe card |✅ | ❌ | ✅ |✅
| Basket preview |❌ | ❌ |❌ | ❌
| Price | ❌ | ❌ |❌|❌
| Item selector |❌ | ❌ |❌|❌
| Recipe detail |❌ | ❌ |❌|❌
| Counter | ❌| ❌ |❌|❌
| PopUp |❌ | ❌ |❌|❌

they 'll all avaible soon

  #### SDK: performance
  - Improve build 
  - Improve feches and cache
  #### SDK: new components
  - Catalog
  - My dinner page
  - Favorite page
  - Recipe Creator

## Getting started for IOS

> Comming soon