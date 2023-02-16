# Notifications

Miam provide a notifiaction service to make your UI more interactive with customer
Currently when handle two event : 

  - **Recipe added to cart**
  - **Recipe liked**

First you can set a message to send in those case

```kotlin
import com.miam.kmmMiamCore.handler.ToasterHandler

ToasterHandler.setOnAddRecipeText("Well done !")
ToasterHandler.setOnLikeRecipeText("Good taste !")
```

:::info
By default notifications are disabled. You must provide strings for the events you wish to enable for this to work.
:::

You can use this service by passing a call back :

```kotlin
import com.miam.kmmMiamCore.handler.ToasterHandler

// With a call back
ToasterHandler.setOnSuccess {
  val duration = Toast.LENGTH_SHORT
  val toast = Toast.makeText(applicationContext, it, duration)
  toast.show()
}
```

