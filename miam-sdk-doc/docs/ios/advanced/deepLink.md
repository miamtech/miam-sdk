# Link


## DeepLink
Miam provide a bind function on catalog that show desired categories.
it's design to be use with deeplink just pass **MIAM_CATEGORY_ID** as parametter in your link then bind it like this : 

```swift
import SwiftUI
import MiamIOSFramework

struct CatalogTabView: View {
    val MIAM_CATEGORY_ID : string
    
    var body: some View {
        VStack {
            CatalogView(categoryId: MIAM_CATEGORY_ID, recipesListColumns: "Some title") 
        }
    }
}

```

:::tip
You can find miam categories id on our back office    <a target="https://partners.miam.tech/" href='https://partners.miam.tech/'> **miam partner**</a> 
:::

## Direct Link

You can also use this feature directly in the application, for that you need 
to get current active catalog's categories that you can get by passing a closure :

```swift
PointOfSaleHandler.shared.getCatalogCategories { categories in
    self.categories.categoriesList = categories
}
```

Then you can pass it to a component and use it to navigate directly to a category

```swift
import SwiftUI
import miamCore

struct CategoriesMenu: View {

    @ObservedObject var categorie: Categories

    var body: some View {
        Menu("categories") {
            ForEach(categorie.categoriesList, id: \.self) { cat in
                Button(cat.title, action: { /*Do stuff here*/ })
            }
        }
    }
}

```