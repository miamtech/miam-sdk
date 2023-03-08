# Groceries list

Some Miam internal informations an function are available they can be accesed
with `GroceriesListHandler`

## Recipes count

You can get the count of recipes added to cart by the user, for this you have two approche: 

- you can subscribe to the observable :

```kotlin
import com.miam.kmmMiamCore.handler.GroceriesListHandler

private var recipeCount = 0

launch {
    GroceriesListHandler.getRecipeCountChangeFlow().collect {
        recipeCount = it.newRecipeCount
        println("recipes count by flow : $recipeCount ")
    }
}
```

- or neither provide use a call back to use when it change

```kotlin
import com.miam.kmmMiamCore.handler.GroceriesListHandler

private var recipeCount = 0

// RecipeCountChanged -> {newRecipeCount :Int}
fun customCallBack(gle: RecipeCountChanged){
    recipeCount = gle.newRecipeCount
    println("recipes count by callback : $recipeCount ")
}

GroceriesListHandler.onRecipeCountChange(customCallBack)
```

:::note
Using `getRecipeCountChangeFlow` will only emit change witch occure after you start the observation. <br />
Using `onRecipeCountChange` will provide a first value and from then after each change.
:::

## Reset groceries list

You can provide a new groceries list to your customer :

```kotlin
import com.miam.kmmMiamCore.handler.GroceriesListHandler

GroceriesListHandler.resetGroceriesList()
```

:::tip
It depend on the user experience you want, but you can link this reset to your customer cart reset,
or use it only for development purpose to avoid weird test behavours.
:::