# Point of sale

Miam initialization process will start only after the user has **selected a valid store**.

Firstly, ask Miam team for your "supplier id" (unique for all your apps and websites integrations).
We will also spoof the origin header of all the requests sent to Miam API.

Then, initialize the PointOfSaleHandler with this information:

```kotlin 
import com.miam.kmmMiamCore.handler.PointOfSaleHandler

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
Miam.getInstance().PointOfSaleHandler.updateStoreId(< string > STORE_ID_IN_HOST_APP)
```

It is possible to define a store as "active" or "inactive". When a store is inactive, Miam
initialization process won't start even if the store is selected by the user.

```kotlin
import com.miam.kmmMiamCore.handler.PointOfSaleHandler

// List of store ids in the host app referential
private const val availableStoreIdLists = listof("454", "652")

private fun isActiveOnStore(): Boolean {
    return availableStoreIdLists.contains(< string > STORE_ID_IN_HOST_APP)
}

Miam.getInstance().PointOfSaleHandler.isAvailable = ::isActiveOnStore
```

Miam provide a function that give you current active catalog's categories This feature is design to
be use with deeplink that redirect to catalog

```kotlin
  CatalogCategory(
    val id : String,
val title: String
)
```

An example of implementation :

```kotlin
 class MainActivity : ComponentActivity() {
    val categoriesState: MutableState<List<CatalogCategory>> = mutableStateOf(listOf())

    fun init() {
        // CODE
        PointOfSaleHandler.getCatalogCategories(::fetchCategory)
        // CODE
    }

    private fun fetchCategory(categories: List<CatalogCategory>) {
        categoriesState.value = categories
    }

    @Composable
    fun content() {
        Column {
            categoriesState.value.forEach {
                Text(text = it.title)
            }
        }
    }
}
```

#### Basket synchronization

Last but not least, the SDK embeds a complex synchronization system that will ensure Miam always
keeps the knowledge of what products have been pushed to or removed from the in-app basket. This
mechanism is **mandatory** to ensure products added via Miam recipes are kept consistent with the
interactions users will have with the basket outside of Miam components.

> If at some point, you feel like products magically disappear from Miam recipes,
or are not removed from the app basket while they should be, this is probably related to this part.

By convenience, we recommend to define a mapping function that transforms the host app YourProduct
objects to "Miam products" objects (named `RetailerProduct` in the SDK). The opposite function can
also be defined:

```kotlin
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct

// Defined in the SDK
data class RetailerProduct(val retailerId: String, val quantity: Int, val name: String?)

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

Miam needs to listen to any change applied to the basket in the host app. To that end, you have to
pass a function to `BasketHandler` with the flowing signature:
`(callback : (products: List<RetailerProduct>) -> Unit) -> Unit`

```kotlin
import com.miam.kmmMiamCore.handler.Basket.BasketHandler

class Miam {

    // to do after koin initialization, use lateinit var if necessary
    private val basketHandler: BasketHandler = BasketHandlerInstance.instance

    init {
        // give miam a function walled when everything is ready to listen to your basket
        basketHandler.setListenToRetailerBasket(::initBasketListener)
        // push a first basket to Miam so we can sync your current basket we Miam ones
        // then Miam will call initBasketListener function to listen to any change
        val firstRetailerbasket =
            yourProductsToRetailerProducts(< List < YourProduct > > basket.productsList)
        basketHandler.pushProductsToMiamBasket(firstRetailerbasket)
        // CODE
    }

    private fun initBasketListener() {
        OBSERVABLE_ON_BASKET_OBJECT.collect { basket ->
            // function will be triggered on every basket change
            val yourBasketAsRetailerproducts =
                yourProductsToRetailerProducts(< List < YourProduct > > basket.productsList)
            basketHandler.pushProductsToMiamBasket(yourBasketAsRetailerproducts)
        }
    }

    // CODE
}
```

Now, the other way around : everytime Miam's basket changes (every time a recipe is added or removed
for example), the added or removed subsequent products have to be pushed to the in-app basket.
Another function has to be defined on BasketHandler, with the
signature: `(products: List<RetailerProduct>) -> Unit`.

```kotlin
import com.miam.kmmMiamCore.handler.Basket.BasketHandler

class Miam {

    private val basketHandler: BasketHandler = BasketHandlerInstance.instance

    init {
        basketHandler.setPushProductsToRetailerBasket(::pushProductsToYourBasket)
        // CODE
    }

    private fun pushProductsToYourBasket(products: List<RetailerProduct>) {
        // Convert "Miam products" to your own product objects
        for (product in retailerProductsToYourProducts(products)) {
            if (it.quantity <= 0) {
                // Removes product from host app basket
                yourDeleteFunction(it)
            } else if (yourTestFunctionAlreadyInBasket(it.id)) {
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

Finally, Miam basket will be confirmed and cleared once the payment has been validated by the user.
We have to trigger this event on the BasketHandler as well:

```kotlin
import com.miam.kmmMiamCore.handler.Basket.BasketHandler

class Miam {

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
