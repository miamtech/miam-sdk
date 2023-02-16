# User customisation

 Miam can provide a custom experience for final user. To achive this purpose we propose two solution :
 - Preferences: Localy stored _ Activable without customer consent by the retailer_
 - Profiling : Back hosted tastes _Can be disable by the customer_

:::note
Those two solutions are RGPD compliente
:::

 ## Preferences

 Preference use native Android Prefrence to make them pesitente. Then to enable this functionality 
 you have to provide a **context** and change Miam preference configuration flag to `true` :

 ```
 ```


 ## Profiling

You can block custom recipe suggestions if the user wishes.

```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

class Miam() {
  init {
    // CODE

    OBSERVABLE_ON_USER_PREF.collect { user ->
      UserHandler.setProfilingAllowed(<boolean>user.allowProfiling)
    }
  }

  // CODE
}
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

