# Internationalisation - I18n

This section is about how we can define localised content in the context of the Miam SDK.

## How it works

To simplify the declaration and consumption of localised strings across the Miam SDK there two things to know:

### - The SDK uses [Moko Resources](https://github.com/icerockdev/moko-resources)

It is used to centralize and retrieve localisations in a Gradle module. Thus, in a module that is configured with **Moko Resources**, you should find resources with the following file hierarchy:

```text
KMM Module/
└── src/
    └── commonMain/
        |── kotlin/
            └── ...
        └── resources/
            └── MR/
                ├── base/
                │   └── string.xml
                ├── en/
                │   └── string.xml
                └── es/
                    └── string.xml
```

Every directory under `MR` represents a localisation (e.g. French, English, Spanish, etc.).
The base is the default language defined in the Gradle configuration (default is English).

`strings.xml` files contains the localised keys:
```xml
<resources>
    <string name="com_miam_i18n_recipe_add">Add ingrédients</string>
    <string name="com_miam_i18n_recipe_added">1 meal added to your basket :</string>
</resources>
```

Depending on the Gradle configuration, Moko will generate code to easily access localisation resources, in common code.

If we consider Miam SDK configuration

```kotlin
multiplatformResources {
    multiplatformResourcesPackage = "com.miam.sdk.resources"
    multiplatformResourcesClassName = "MiamSdkResources"
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Internal
}
```

Resources can be accessed from `MiamSdkResources` class:
```kotlin
MiamSdkResources.strings.com_miam_i18n_recipe_add
```

Then resources can be localised on each platform (iOS / Android). This is why we created an abstraction in the `:core`. 

### The module `:core` encapsulate _Moko's_ API

We have created an API under `com.miam.core.localisation.i18n` to manage two things:
- `accessibilityKey`: This is just a unique key to be used by any application that needs to declare accessibility items in their own dictionary.
- `localised`: This represents the value localised depending on the current application context.

Underneath, `I18n` uses an implement of `I18nResolver`, for each platform (iOS and Android), to retrieve the correct localisations.

To create `I18n` that can be localised there is two functions:

- `I18n.string(accessibilityKey, resource, args)`

```kotlin
I18n.string(
    accesibilityKey = "com_miam_i18n_recipe_add", 
    resource = miamStrings?.com_miam_i18n_recipe_add,
    // In the case of a parameterized string you can put multiple (vararg) values 
)
```

- `I18n.plural(key, resource,, quantity, args)`

```kotlin
I18n.plural(
    accesibilityKey = "com_miam_i18n_recipe_items", 
    resource = miamStrings?.com_miam_i18n_recipe_items,
    quantity = 5,
    // In the case of a parameterized plural you can put multiple (vararg) values 
)
```

Finally, we need to see how to handle localisation, so they can be consumed from any platform.

## Declare new localised values

First thing to do to add localisation is to edit your `strings.xml` files in Miam SDK:

```xml
<resources>
    ...
    <string name="com_miam_i18n_basket_title">Panier</string>
</resources>
```

Then, Moko will generate a new entry in `MiamSdkResources.strings(.com_miam_i18n_basket)`. 

To make accessible from any platform you could create objects that hold the localisation references or create accessors that provide I18n instances:

```kotlin
private val miamStrings = runCatching { MiamSdkResources.strings }.getOrNull()

object BasketLocalisation {
    val title: I18n
        get() = I18n.string(
            "com_miam_i18n_basket_title", 
            miamStrings?.com_miam_i18n_basket_title
        )
}
```

## Consume localised strings

Consumption of localised strings depends the platform you are working on.

### On Android

**Android** platform needs a `Context` to access resources, either from the `Application` or an `Activity`. Thus, before retrieving any Miam SDK resource you need to register the application`s `Context` in `I18nResolver`. 

```kotlin
class MyApplication : Application {
    init {
        I18nResolver.registerContext(this.applicationContext)
    }
}
// OR
class MainActivity : ComponentActivity() {
    init {
        I18nResolver.registerContext(this.applicationContext)
    }
}
```

Then you can easily retrieve any `I18n` defined in the SDK:

```kotlin
// Accessing basket title
val basketTitle: String = BasketLocalisation.title.localised
```


### On iOS

On iOS, accessing localised strings is really easy. Moko doesn't need any context you retrieve the right localisation.

```swift
// Accessing basket title
let basketTitle: String = BasketLocalisation.shared.title.localised
```

In Kotlin, we have defined `BasketLocalisation` as an object, thus swift needs to get the instance with the `shared` property.

To hide this to the use, we could define the following code in Swift:

```swift
struct L {
    static let basket: BasketLocalisation = { BasketLocalisation.shared }()
}
```

Making it usable like: 

```swift
let basketTitle: String = L.basket.title.localised
```

### Override Miam SDK values

You can override Miam SDK's localised content. However, the mechanism is different for each platform. 

Let's assume that the Miam SDK embed the following localisation string:

```xml
<resources>
    <string name="com_miam_i18n_recipe_add">Add ingredients</string>
</resources>
```

### On Android

On Android, it is fairly simple to override resources that come from a library. Just declare values with the same identifier, and it's done. 


In your application, just add the following string declaration inside a `res/values(-LANG)/<FILENAME>.xml` (the filename doesn't matter) to override it:

```xml
<resources>
    <string name="com_miam_i18n_recipe_add">Add recipe to basket</string>
</resources>
```

When consuming `com_miam_i18n_recipe_add`, you should see **Add recipe to basket** instead of **Add ingredients**.   

### On iOS

Overriding localisation on Android is a little big trickier. 
First thing is to create a new entry in your `Localizable.strings` file, to provide your localisation:

```text
// Localizable.strings
"com_miam_i18n_recipe_add" = "Add recipe to basket"
```

Then, as Moko is reading localisations from a Bundle, that by default is the Miam SDK's bundle, you need to register your app's bundle on the `I18nResolver` before consuming any override resources.

```swift
I18nResolver.shared.registerAppBundle(Bundle.main)
```

After that, consuming `com_miam_i18n_recipe_add` on iOS, should display **Add recipe to basket** instead of **Add ingredients**.   