---
sidebar_position: 2
label: "Initialisation"
---


# Initialisation
## Miam class

We recommend that all the mapping functions that will define the interactions between the SDK and
the host app be wrapped in a main **Miam** class.

This class will use methods and attributes defined in SDK `handler` classes to manage objects such
as the User profile, the Basket, or the selected Store. These haldlers are all singletons.

```kotlin
class Miam {
    // Will contain calls to Miam SDK handler classes (User, Basket, Store...)
}
```

:::tip
Make sure this main "Miam" class is a singleton and instantiated only once in your runtime.
:::

## Minimun requirement

:::info
You need to ask Miam team for your **supplier id** and your **origin** (unique for all your apps and websites integrations).
:::

To differentiate **platforms** and **retailers** in Miam API Miam sdk require  **supplier id** and your **origin**. Once you got your ids you can implement your **Miam** class as follow :

```kotlin 
import com.miam.kmmMiamCore.handler.PointOfSaleHandler

class Miam() {
  init {
    //  CODE
    PointOfSaleHandler.setSupplier(YOUR_SUPPLIER_ID)
    PointOfSaleHandler.setSupplierOrigin(<string>YOUR_SUPPLIER_ORIGIN)
  }

  //  CODE
}
```
:::tip
Make sure to specify a different origin between your development and production environments
:::

Miam initialization process will only start after the user is **logged** and has **selected a valid store**.
then it'll need the **basket synchronization**

## User setup 

Here is how to inform the SDK whenever the user login state changes. We recommend using Observables or EventListeners to that end.

```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

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
:::info
   To get full list of user feature check [**User handler**](../advanced/user-configuration).
:::

## Store setup

Has user you can inform the SDK whenever the user selected store changes :

```kotlin 
import com.miam.kmmMiamCore.handler.PointOfSaleHandler

class Miam() {
  init {
    // CODE

    OBSERVABLE_ON_POINT_OF_SALE_OBJECT.collect { pointOfSale ->
      PointOfSaleHandler.updateStoreId(<string>pointOfSale.id)
    }
  }

  // CODE
}
```

## Basket synchronization Setup

Last but not least, the SDK embeds a complex synchronization system that will ensure Miam always
keeps the knowledge of what products have been pushed to or removed from the in-app basket. This
mechanism is **mandatory** to ensure products added via Miam recipes are kept consistent with the
interactions users will have with the basket outside of Miam components.

:::tip 
 If at some point, you feel like products magically disappear from Miam recipes, or are not removed from the app basket while they should be, this is probably related to this part.
:::

By convenience, we recommend to define a mapping function that transforms the host app **YourProduct**
objects to **Miam products** objects (named `RetailerProduct` in the SDK). The opposite function can
also be defined:

```kotlin
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct

// Defined in the SDK
data class RetailerProduct(val retailerId: String, val quantity: Int, val name: String?)
//

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

For the next step **Miam** needs to listen to any change applied to your basket in your app. To that end, you have to
pass to **Miam** a function to `BasketHandler` with the flowing signature:
`(callback : (products: List<RetailerProduct>) -> Unit) -> Unit`

It can be done this way :

```kotlin
import com.miam.kmmMiamCore.handler.Basket.BasketHandler

class Miam {

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

Almost there ! now the other way around : everytime Miam's basket changes (every time a recipe is added or removed
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

Congratulation **Miam** is good to go 🥳

