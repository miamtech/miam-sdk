---
sidebar_position: 3
label: "Initialisation"
---

# Initialisation
## Miam class

We recommend that all the mapping functions that will define the interactions between the SDK and
the host app be wrapped in a main **Miam** class.
This class will use methods and attributes defined in SDK **handler** classes to manage objects such as the User profile, the Basket, or the selected Store. These haldlers are all singletons.

:::tip
Make sure this main **Miam** class is a singleton and instantiated only once in your runtime. Here is a basic implementation
:::

```swift
// file Miam.swift
import Foundation

public class Miam {
  // Will contain calls to Miam SDK handler classes (User, Basket, Store...)
  
  public static let sharedInstance = Miam()
}
```


```swift
//IosApp.swift

import SwiftUI

@main
struct ios_miam_integrationApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // init miam
        let _ = Miam.sharedInstance
        return true
    }
}

```

## Minimun requirement

:::info
You need to ask Miam team for your supplier id and your origin (unique for all your apps and websites integrations).
:::

To differentiate **platforms** and **retailers** in Miam API Miam sdk require  **supplier id** and your **origin**. Once you got your ids you can implement your **Miam** class as follow :

```swift
// file Miam.swift
import miamCore

class Miam {
  
  private init() {
    //  CODE
      PointOfSaleHandler.shared.setSupplierOrigin(origin : <string>YOUR_SUPPLIER_ORIGIN)
      PointOfSaleHandler.shared.setSupplier(supplierId: <string>YOUR_SUPPLIER_ID)
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


Here is how to pass the user ID to the SDK, directly within the host app:

```swift
// From anywhere
import miamCore

// USER_ID_IN_HOST_APP is your user id type String is expected
UserHandler.shared.updateUserId(userId: USER_ID_IN_HOST_APP)
```

Here is how to inform the SDK whenever the user login state changes. We recommend using Observables or EventListeners to that end.

```swift
// file Miam.swift
import miamCore

public class Miam {
  // CODE

  private init() {
    // CODE

    OBSERVABLE_ON_USER_OBJECT.sink { _  in
      // USER_ID_IN_HOST_APP is your user id type String is expected
      UserHandler.shared.updateUserId(userId: USER_ID_IN_HOST_APP)
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

```swift
// From anywhere
import miamCore

// STORE_ID_IN_HOST_APP is your user id type String is expected
PointOfSaleHandler.updateStoreId(storeId: <string>STORE_ID_IN_HOST_APP)

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

```swift
import miamCore

// Defined in the kotlin SDK, but can be used in swift
// data class RetailerProduct(val retailerId :String, val quantity: Int, val name: String?)

 private func yourProductsToRetailerProducts(products: Array<YourProduct>) -> Array<RetailerProduct> {
      return products.map {
       return RetailerProduct(
            retailerId: $0.id,
            quantity: Int32($0.quantity),
            name: $0.name,
            productIdentifier: nil
        )
      }
    }

private func retailerProductsToYourProducts(products: Array<RetailerProduct>) -> Array<YourProduct> {
  return RetailerProduct.map { 
    return YourProduct(
       id: $0.retailerId,
       name: "\($0.name)",
       quantity: Int($0.quantity)
    )
  }
}     

```


For the next step **Miam** needs to listen to any change applied to your basket in your app. To that end, you have to pass to **Miam** a closure to `BasketHandler` with the flowing signature:
`(callback : (products: List<RetailerProduct>) -> Unit) -> Unit`

It can be done this way :

```swift
class Miam {

   private let basketHandler: BasketHandler 

  init {
    
    basketHandler = BasketHandlerInstance.shared.instance
     // give miam a function walled when everything is ready to listen to your basket
    basketHandler.listenToRetailerBasket(func: initBasketListener) 
    // push a first basket to Miam so we can sync your current basket we Miam ones
    // then Miam will call initBasketListener function to listen to any change
    val firstRetailerbasket = yourProductsToRetailerProducts(basket.productsList)
    basketHandler.pushProductsToMiamBasket(firstRetailerbasket)

    // CODE
  }

  private func initBasketListener() {
    OBSERVABLE_ON_BASKET_OBJECT.sink  { receiveValue in
            // callback will be triggered on every basket change
             basketHandler.pushProductsToMiamBasket(
               yourProductsToRetailerProducts(receiveValue)
               )
       }
  }

  // CODE
}

```

Almost there ! now the other way around : everytime Miam's basket changes (every time a recipe is added or removed
for example), the added or removed subsequent products have to be pushed to the in-app basket.
Another function has to be defined on BasketHandler, with the
signature: `(products: List<RetailerProduct>) -> Unit`.

```swift
// file Miam.swift
import miamCore

class Miam {
   // CODE
  private let basketHandler: BasketHandler

  private init() {
    basketHandler = BasketHandlerInstance.shared.instance
    basketHandler.pushProductsToRetailerBasket(func: pushProductsToYourBasket)
    // CODE
  }

  private func pushProductsToYourBasket (products: [RetailerProduct]) {
    // Convert "Miam products" to your own product objects
    retailerProductsToYourProducts(products).foreach( { 
      if ($0.quantity <= 0) {
        // Removes product from host app basket
        yourDeleteFunction($0)
      } else if (yourTestFunctionAlreadyInBasket($0.id)){
        // Updates quantity of product in host app basket
        yourUpdateFunction($0)
      } else {
        // Add product to host app basket
        yourAddFunction($0)
      }
    })
  }

  // CODE
}   

```

Finally, Miam basket will be confirmed and cleared once the payment has been validated by the user.
We have to trigger this event on the BasketHandler as well:

```swift
class Miam {

    private let basketHandler: BasketHandler = BasketHandler()

    private init()
    {
        // CODE
        basketHandler.paymentTotal = getTotalPayment
    }

    private func getTotalPayment() -> KotlinDouble
    {
        return ORDER_PAID_AMOUNT_IN_APP()
    }

    // CODE
}

// Confirm basket when payment confirmed in app:
Miam.getInstance().basketHandler.handlePayment()

```

Congratulation **Miam** is good to go 🥳