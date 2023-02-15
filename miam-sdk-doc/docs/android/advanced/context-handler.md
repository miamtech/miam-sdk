# Groceries list

Some Miam internal informations are available for reading only they can be accesed
with `GroceriesListHandler`

## Recipes count

```kotlin
import com.miam.kmmMiamCore.handler.GroceriesListHandler

private var recipeCount = 0

launch {
    GroceriesListHandler.getRecipeCountChangeFlow().collect {
        recipeCount = it.newRecipeCount
        println("recipes count by flow : $recipeCount ")
    }
}
GroceriesListHandler.onRecipeCountChange {
    recipeCount = it
    println("recipes count by callback : $recipeCount ")
}
```

You can reset Miam recipe list :

```kotlin
  GroceriesListHandler.resetGroceriesList()
```