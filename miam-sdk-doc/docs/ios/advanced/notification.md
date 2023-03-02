# Notifications

Miam provide a notifiaction service to make your UI more interactive with customer
Currently when handle two event : 

  - **Recipe added to cart**
  - **Recipe liked**

First you can set a message to send in those case

```swift
ToasterHandler.shared.setOnAddRecipeText("Well done !")
ToasterHandler.shared.setOnLikeRecipeText("Good taste !")
```

:::info
By default notifications are disabled. You must provide strings for the events you wish to enable for this to work.
:::

You can use this service by passing a closure :

```swift
ToasterHandler.shared.setOnSuccess { message in
  print(message)
}
```

