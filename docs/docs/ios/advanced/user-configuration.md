# User customisation

 Miam can provide a personalized experience for customers. To achive this purpose we propose two solution :
 - Preferences: Localy stored _ Activable without customer consent by the retailer_
 - Profiling : Back hosted tastes _Can be disable by the customer_

:::note
Those two solutions are GDPR compliant
:::

 ## Preferences

The preferences use the native IOS preference to make them pesitente. To enable this feature 
you need to provide a **context** and change the Miam preference's configuration flag to `true`:
 
 example with **Swift ui** :

```swift
// file Miam.swift
import miamCore

class Miam {
   // CODE
  private let basketHandler: BasketHandler

  private init() {
   ContextHandlerInstance.shared.instance.setContext(context: MiamPreferencesContext())
    // CODE
  }

}
```

 ```swift
import MiamIOSFramework

struct CatalogTabView: View {
    var body: some View {
        VStack {
            ZStack(alignment: .bottom) {
                CatalogView(usesPreferences: true, recipesListColumns: 2)
                MyMealButtonView({})
            }
        }
    }
}

 ```


 ## Profiling

Miam offers to your customer a fully personalized experience based on their tastes.
Our IA will learn from the customer's choices and suggest more and more specific recipes and product.

This feature can be disable by customer if they wish to.

To do this, we expose this function:

```swift 
import miamCore

public class Miam {
  // CODE

  private init() {
    // CODE

      // allowance is a boolean, true by default
      UserHandler.shared.setProfilingAllowed(allowance: USER_PREF_IN_HOST_APP)
  }

  // CODE
}
```


## Like recipe

You can disable Like feature with UserHandler

```swift 
// file Miam.swift
import miamCore

public class Miam {
  // CODE

  private init() {
    // CODE
      UserHandler.shared.setEnableLike(isEnable: false)
  }

  // CODE
}

```

:::note
If not setted like feature will be enable by default
:::

