# KMM Miam SDK

[[_TOC_]]

## Introduction

This SDK aims to facilitate the integration of Miam eCommerce to any grocery shopping mobile application.

It implements a series of components that can be re-styled to your standards, and injected into your app. Miam Components interact with each other and take care of the communication with Miam API. Using this SDK, you should not need to communicate with Miam API directly from your app.

### 3 steps integration

The integration of the SDK into your app will take three steps:
1. **Initialization** : define the few mapping functions the SDK needs to interact with your app (push products to basket, retrieve the user unique id...)
2. **Components injection** : fill your app with Miam components wherever relevant (recipe cards in search results grids, recipes catalog on a dedicated page...)
3. **Styling** : apply your own stylesheets, globally at the SDK level, and specifically for each component 

### Project architecture

This SDK is leveraging Kotlin Multiplatform Mobile so most of the Models, Controllers, and Services (interactions with Miam API) can be implemented only once and reused both in iOS and Android apps. Only the Views have to be implemented separately for each platform.

Consequently, this SDK is organized in three main modules (TODO: naming to be reviewed):
- /shared : contains the core logic shared between the two platforms
- /androidSDK : the SDK to be built and imported in an Android app, containing the core logic + Android-related Views
- /MiamIOSFramework : same thing, but for iOS apps

For instance, in the case of an Android application, you shouldn't have to import the built archive of /shared and /androidSDK : building /androidSDK to an APK and importing it will be enough, as this APK will contain the whole logic (/shared + Android views).

## Android integration (Kotlin)

> Minimum required Kotlin version is `1.6.10`

### Initialization

#### Build and import

CI/CD is not setup yet and the built archived are not hosted anywhere. You will need to clone this repository and build the archive in production mode.

The archive will be generated as `miam-sdk-release.aar` in `/androidSDK/build/outputs`.

Import it into your project, in the `libs` folder.

Finally, import it to your Gradle configuration:

```kotlin
// In the app build.gradle file
implementation(name:'miam-SDK-release', ext:'aar')
```

#### Dependencies

Our components are using [Jetpack Compose](https://developer.android.com/jetpack/compose?gclsrc=aw.ds&gclid=CjwKCAjwrfCRBhAXEiwAnkmKmWkwGezGLmmfauda5_ACVVNtTVPUw576netuScD2mLnGacjr2cB30RoCC24QAvD_BwE) which requires the following third-party libraries:

```kotlin
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.compose.compiler:compiler:1.0.5")
    implementation("androidx.compose.ui:ui:1.0.5")

    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.5")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.5")

    // Material Design
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

> Jetpack is not mandatory but facilitates the injection of Miam components - See part 2 Components injection

We are using [Ktor]("https://ktor.io/docs/welcome.html") as a Http client, which requires:

```kotlin
    implementation("io.ktor:ktor-client-android:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-logging:1.6.7")
```

As a side note, the SDK embeds [Koin]("https://insert-koin.io/") for dependency injection. as it is embedded, no extra import is needed.

> Caveat: we've noticed potential compatibility issues if you are already using Koin in your own app... TODO: improve dependency injection

#### Main class

We recommend that all the mapping functions that will define the interactions between the SDK and the host app be wrapped in a main "Miam" class.

This class will use methods and attributes defined in SDK "handler" classes to manage objects such as the User profile, the Basket, or the selected Store. These haldlers are all singletons.

Make sure this main "Miam" class is a singleton and instantiated only once in your runtime. Here is a basic implementation:

```kotlin
import com.miam.kmm_miam_sdk.android.di.KoinInitilizer

class Miam() {

  // Will contain calls to Miam SDK handler classes (User, Basket, Store...)
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

#### Connection to Miam API

**TODO**

#### User

Miam initialization process will start only after the user is **logged**.

Here is how to pass the user ID to the SDK, directly within the host app:

```kotlin
import com.miam.kmm_miam_sdk.handler.UserHandler

// Reference to your main "Miam" class
Miam.getInstance().UserHandler.updateUserId(USER_ID_IN_HOST_APP (string))
```
Here is how to inform the SDK whenever the user login state changes. We recommend using Observables or EventListeners to that end. For instance : [MutableSharedFlow]("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-mutable-shared-flow/").

```kotlin 
import com.miam.kmm_miam_sdk.handler.UserHandler

class Miam() {
  init {
    // CODE

    OBSERVABLE_ON_USER_OBJECT.collect { user ->
      UserHandler.updateUserId(<string>user.id)
    }
  }

  // CODE
}
```

#### Store

Miam initialization process will start only after the user has **selected a valid store**.

Firstly, ask Miam team for your "supplier id" (unique for all your apps and websites integrations). We will also spoof the origin header of all the requests sent to Miam API.

Then, initialize the PointOfSaleHandler with this information:

```kotlin 
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler

class Miam() {
  init {
    //  CODE
    PointOfSaleHandler.setSupplier(<string>YOUR_SUPPLIER_ID)
    PointOfSaleHandler.setSupplierOrigin(<string>YOUR_SUPPLIER_ORIGIN)
  }

  //  CODE
}
```

> Make sure to specify a different origin between your development and production environments

Finally, send the store ID to the SDK (in the example, from the host app):
```kotlin
Miam.getInstance().PointOfSaleHandler.updateStoreId(<string>STORE_ID_IN_HOST_APP)
```

It is possible to define a store as "active" or "inactive". When a store is inactive, Miam initialization process won't start even if the store is selected by the user. 

```kotlin
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler

// List of store ids in the host app referential
private const val availableStoreIdLists = listof("454", "652")

private fun isActiveOnStore(): Boolean {
  return availableStoreIdLists.contains(<string>STORE_ID_IN_HOST_APP)   
}

Miam.getInstance().PointOfSaleHandler.isAvailable = ::isActiveOnStore
```

#### Basket synchronization

Last but not least, the SDK embeds a complex synchronization system that will ensure Miam always keeps the knowledge of what products have been pushed to or removed from the in-app basket. This mechanism is **mandatory** to ensure products added via Miam recipes are kept consistent with the interactions users will have with the basket outside of Miam components.

> If at some point, you feel like products magically disappear from Miam recipes, or are not removed from the app basket while they should be, this is probably related to this part.

By convenience, we recommend to define a mapping function that transforms the host app YourProduct objects to "Miam products" objects (named `RetailerProduct` in the SDK). The opposite function can also be defined:

```kotlin
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

// Defined in the SDK
data class RetailerProduct(val retailerId :String, val quantity: Int, val name: String?)

private fun yourProductsToRetailerProducts(products: List<YourProduct>): List<RetailerProduct> {
  return YourProduct.map { yourP ->
    RetailerProduct(
      yourP.id,
      yourP.quantity,
      yourP.name
    )
  }
}

private fun retailerProductsToYourProducts(products: List<RetailerProduct>): List<YourProduct> {
  return RetailerProduct.map { product ->
    YourProduct(
      product.id,
      product.quantity,
      product.name
    )
  }
}     
```

Miam needs to listen to any change applied to the basket in the host app. To that end, you have to pass a function to `BasketHandler` with the flowing signature: 
`(callback : (products: List<RetailerProduct>) -> Unit) -> Unit`

```kotlin
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler

class Miam() {

   private val basketHandler: BasketHandler = BasketHandler()

  init {
    basketHandler.listenToRetailerBasket = ::initBasketListener

    // CODE
  }

  private fun initBasketListener(
    callback: (products: List<RetailerProduct>) -> Unit
  ) {
    OBSERVABLE_ON_BASKET_OBJECT.collect { basket ->
      // callback will be triggered on every basket change
      callback(yourProductsToRetailerProducts(<List<YourProduct>>basket.productsList))
    }
  }

  // CODE
}
```

Now, the other way around : everytime Miam's basket changes (every time a recipe is added or removed for example), the added or removed subsequent products have to be pushed to the in-app basket. Another function has to be defined on BasketHandler, with the signature: `(products: List<RetailerProduct>) -> Unit`.

```kotlin
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler

class Miam() {

  private val basketHandler: BasketHandler = BasketHandler()

  init {
    basketHandler.pushProductsToBasket = ::pushProductsToYourBasket
    // CODE
  }

  private fun pushProductsToYourBasket (products: List<RetailerProduct>) {
    // Convert "Miam products" to your own product objects
    for (product in retailerProductsToYourProducts(products)) { 
      if (it.quantity <= 0) {
        // Removes product from host app basket
        yourDeleteFunction(it)
      } else if (yourTestFunctionAlreadyInBasket(it.id)){
        // Updates quantity of product in host app basket
        yourUpdateFunction(it)
      } else {
        // Add product to host app basket
        yourAddFunction(it)
      }
    }
  }

  // CODE
}   
```

Finally, Miam basket will be confirmed and cleared once the payment has been validated by the user. We have to trigger this event on the BasketHandler as well:

```kotlin
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler

class Miam() {

  private val basketHandler: BasketHandler = BasketHandler()

  init {
    // CODE

    basketHandler.paymentTotal = fun(): Double { 
      return ORDER_PAID_AMOUNT_IN_APP()
    }
  }

  // CODE
}

// Confirm basket when payment confirmed in app:
Miam.getInstance().basketHandler.handlePayment()
```

### Components injection

There are two ways to inject Miam components into the host app:
- with **Jetpack Compose** (preferred as nothing has to be changed on the component itself, except styling adjustments)
- by injecting **your own full XML** version of the component (a bit more complex, but lets you the full flexibility of changing every aspects of the component)

#### With Jetpack Compose (preferred)

For the sake of the example, we will inject a component showing a recipe card in the host app.

Initialize a RecipeView object, passing your current context:

```kotlin
val recipe = RecipeView(this@MainActivity)
```

In Miam, recipe cards can either be "fixed" (= fetched by on a predefined ID) or "suggested" (= fetched based on the user navigation context)

```kotlin
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria

// Implemented in Miam SDK
data class SuggestionsCriteria(
  // Ids of products displayed in the search results, right before and after the recipe card
  val shelfIngredientsIds: List<String>? = null,
  // Ids of products displayed on a product details page (optional)
  val currentIngredientsIds: List<String>? = null,
  // Ids of products already in app basket (optional)
  val basketIngredientsIds: List<String>? = null,
  // (optional)
  val groupId: String? = null
)

val recipe1 = RecipeView(this@MainActivity)
val recipe2 = RecipeView(this@MainActivity)

// Instanciate a fixed recipe card
recipe1.bind(recipeId = 305)

// Instanciate a suggested recipe card
recipe2.bind(
  criteria = SuggestionsCriteria(
    shelfIngredientsIds = listOf(
      PRODUCT_ID_IN_APP,
      PRODUCT_ID_IN_APP     
    )
  )
)

// Inject in the page using Compose
setContent {
  Column {
    recipe1.Content()
    recipe2.Content()
  }
}
```

> All injectable components definitions can be found in the /androidSDK folder => have a look at each View file to discover which attributes must be passed to instantiate the view. 

#### With XML injection

If you are not using Jetpack Compose, you can inject Miam recipe cards directly into your own XML View:

```xml
<!-- defined in layout/item_miam.xml -->
<com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

And then bind the properties like this: 

```kotlin
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria

// Implemented in Miam SDK
data class SuggestionsCriteria(
  // Ids of products displayed in the search results, right before and after the recipe card
  val shelfIngredientsIds: List<String>? = null,
  // Ids of products displayed on a product details page (optional)
  val currentIngredientsIds: List<String>? = null,
  // Ids of products already in app basket (optional)
  val basketIngredientsIds: List<String>? = null,
  // (optional)
  val groupId: String? = null
)

val miamCard = R.layout.item_miam as RecipeView

miamCard.bind(criteria = SuggestionsCriteria(
  shelfIngredientsIds = listOf(
    PRODUCT_ID_IN_APP,
    PRODUCT_ID_IN_APP     
  )
))
```

> Can also be done by replacing `R.layout.item_miam` by the usual `findViewById`...

### Styling

This SDK lets you adjust the components styling so they can be naturally inserted in your app without confusing the end user. 

There are two level of customization:  
- Globally: styles defined here will be applied to all components
- Per component: styles defined here will be applied to a specific component only

**Note**:
- Component styling overrides global styling
- Properties that aren't overriden neither globally nor per component will keep their default values defined by Miam in the SDK

> Components injected using Jetpack or XML can both have their styling customized the same way

#### Colors

> Global variables only!!

Here is how to override a color variable globally:

```kotlin
import com.miam.kmm_miam_sdk.android.theme.Colors

// Colors object is defined in SDK
Colors.primary = Color(0xFF44D6B3)
``` 

> Hint: `0x` means hexa , `FF` stands for opacity , `44D6B3` is the color code

List of colors you can override:

| Name |  Default value | Use (TODO) |
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
| unpureWhite | `#FEFEFE` | 
| black | `#252525` |

#### Wording

All wordings are injected using Miam `Text` objects, which can be overriden globally or component by component as follows:

```kotlin
import com.miam.kmm_miam_sdk.android.ressource.Text

Text.alreadyInCart = "ajoutée"
```

The full list of customizable wordings can be found in file: `androidSDK/src/main/ressource/text.kt`

#### Typography

All font use across SDK are defined here they can be override globaly in `androidSDK/src/main/theme/typography.kt` or in lower level
Our typography are of type [TextStyle]("https://www.jetpackcompose.net/textstyle-in-jetpack-compose")


```kotlin
import com.miam.kmm_miam_sdk.android.theme.Typography

typography.h1 = TextStyle(
        color = Color.Red,
        fontSize = 16.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.W800,
        fontStyle = FontStyle.Italic,
        letterSpacing = 0.5.em,
        background = Color.LightGray,
        textDecoration = TextDecoration.Underline
    )
```

#### Icon / Images

All icons and images are injected using Miam `Image` objects, which can be overriden globally or component by component as follows:

```kotlin
import com.miam.kmm_miam_sdk.android.ressource.Image

Image.basketIcon = R.drawable.your_basket_icon
```

#### Dimensions

All padding, width or height are injected using Miam `Dimension` object, which can be overriden globally or component by component as follows:

```kotlin
import com.miam.kmm_miam_sdk.android.theme.Dimension

Dimension.xlPadding = 40.dp
```

#### Component-specific properties (example: customize the RecipeCard component)

Everytime a component has customizable properties, its folder will contain a `componentStyle.kt` file describing these properties. eg: `recipeCardStyle.kt`.

You can override property by changing its value:

```kotlin
RecipeCardText.alreadyInCart = "ok c'est dedans"
```

Here is an example to customize the time icon on the RecipeCard component:

```kotlin
RecipeCardImage.time = R.drawable.your_time_icon
```

We recommend using [Modifier]("https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier") to override other styles. The full list is available in `ComponentNameStyle.kt`.

Example:

```kotlin
// You can set those option in Miam class 
RecipeCardStyle.image = Modifier.height(245.dp).width(245.dp).clip(shape = CircleShape)
```

| *before* | *after* | 
|:-------------|:-------------:|
|![alt text](pic/defaultCard.png "default card") | ![alt text](pic/alteredStyle.png "custom card")|


### Planned improvements

#### Architecture readability

- Reduce use of `Flow` pattern accross SDK
- Review naming of mapping functions
- Simplify mapping functions to be defined in host apps

#### Deployment and hosting

- CI/CD using Gitlab
- Expose archives in hosted dependency management services 

#### More components styling

Component available for low level customization :

| Name | Style | Color | Icon | Text | Preview |
|:-------------|:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
| Recipe card |✅ | ❌ | ✅ |✅ | ❌
| Basket preview |❌ | ❌ |❌ | ❌ | ❌
| Price |  ✅ |  ✅ |➖| ✅ | ✅
| Item selector |✅ | ✅ |✅ |✅ | ❌
| Recipe detail |❌ | ❌ |❌|❌ | ❌
| Counter | ✅| ✅ |✅|➖ |❌
| Dialog |✅ | ➖|➖|➖ | ❌

- Add component preview for development

#### SDK: performance

- Improve build
- Improve fetches and add cache

#### SDK: new components

- Recipes catalog
- Selected recipes history page
- Favorites recipes
- Personal recipes creation
- Recipe tags

## iOS integration (Swift)

run ./gradlew assembleXCFramework

