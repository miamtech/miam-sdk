# Availability

 Even after adding **Miam** to your code base you can decide to disable the feature or 
 just know our internal status 

## Readiness

Miam need a initialisation time at the start of your app after you provide us the required function and props.

:::caution
Miam'll **never** be ready in those folowing case : 
 - the **user** is not authenticated
 - the **point of sale** is incorrect or not set
 - you have not provide an **initial cart** for miam'basket syncronisation
 - your supplier **origin** or **id** is incorrect
:::

There is two ways to check Miam readiness status:

- call a direct function

```kotlin
  val miamContext = ContextHandlerInstance.instance
// miamContext.isReady() return a bool
prtinln("is Miam ready ? ${miamContext.isReady()}")
```

- listen to Miam event

```kotlin
miamContext.observeReadyEvent().collect { event ->
    val isReady = event == ReadyEvent.isReady
    val isNotReady = event == ReadyEvent.isNotReady
    // Do stuff
}
```

## Point of sale filtering

It is possible to define a store as **active** or **inactive**. When a store is inactive, Miam
initialization process won't start even if the store is selected by the user.

```kotlin
import com.miam.kmmMiamCore.handler.PointOfSaleHandler

// List of store ids in the host app referential
private const val availableStoreIdLists = listof("454", "652")

// Your custom rule
private fun isActiveOnStore(): Boolean {
    return availableStoreIdLists.contains(< string > STORE_ID_IN_HOST_APP)
}

// pass your rule to miam
Miam.getInstance().PointOfSaleHandler.isAvailable = ::isActiveOnStore