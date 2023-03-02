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

```swift
val miamContext = ContextHandlerInstance.shared.instance.isReady()
// isReady() return a bool
print("is Miam ready ? \(miamContext)")
```

- listen to Miam event

```swift
 ContextHandlerInstance.shared.instance.onReadyEvent(callback: {
       // do your call back here
 })
```

## Point of sale filtering

It is possible to define a store as **active** or **inactive**. When a store is inactive, Miam
initialization process won't start even if the store is selected by the user.

```swift
// file Miam.swift

// List of store ids in the host app referential
private  let availableStoreIdLists = ["454", "652"]

func isActiveOnStore() -> KotlinBoolean {
        return  KotlinBoolean(value: availableStoreIdLists.contains("35290"))
}
    
PointOfSaleHandler.shared.isAvailable = isActiveOnStore
```