# Groceries list

Some Miam internal informations an function are available they can be accesed
with `GroceriesListHandler`

## Recipes count

You can get the count of recipes added to cart by the user.You have to provide use a closure to use when this count change

```swift
GroceriesListHandler.shared.onRecipeCountChanges { count in
    // do stuff
    print(count)
}
```

:::note
Using `onRecipeCountChange` will provide a first value and from then after each change.
:::

## Reset groceries list

You can provide a new groceries list to your customer :

```swift
GroceriesListHandler.shared.resetGroceriesList()
```

:::tip
It depend on the user experience you want, but you can link this reset to your customer cart reset,
or use it only for development purpose to avoid weird test behavours.
:::