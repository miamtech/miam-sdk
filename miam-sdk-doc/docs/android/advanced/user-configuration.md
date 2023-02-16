# User customisation

 Miam can provide a personalized experience for customers. To achive this purpose we propose two solution :
 - Preferences: Localy stored _ Activable without customer consent by the retailer_
 - Profiling : Back hosted tastes _Can be disable by the customer_

:::note
Those two solutions are GDPR compliant
:::

 ## Preferences

The preferences use the native Android preference to make them pesitente. To enable this feature 
you need to provide a **context** and change the Miam preference's configuration flag to `true`:
 
 example with **Jetpack Compose** :

 ```kotlin
 import com.miam.kmmMiamCore.handler.ContextHandlerInstance

 class MainActivity: ComponentActivity(){
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         // Make preferences persistent, do it once 
         ContextHandlerInstance.instance.setContext(this@MainActivity)
         setContent {
                Column {
                  // EnablePreference show preference button on Catalog
                  Catalog(this@MainActivity).apply { enablePreference(true) }.Content()
                }
        }
 }
 ```


 ## Profiling

Miam offers to your customer a fully personalized experience based on their tastes.
Our IA will learn from the customer's choices and suggest more and more specific recipes and product.

But this feature can be disable by customer if they wish to.

TO do this, we expose this function:

```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

UserHandler.setProfilingAllowed(<boolean>user.allowProfiling)

```


## Like recipe

You can disable Like feature with UserHandler

```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

class Miam() {
  init {
    // CODE
    // false if you don't want like on recipe card
    UserHandler.setEnableLike(false)
  }

  // CODE
}
```

:::note
If not setted like feature will be enable by default
:::

