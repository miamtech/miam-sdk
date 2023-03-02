# Components injection

There are two ways to inject Miam components into the host app:

with SwiftUi (preferred as nothing has to be changed on the component itself, except styling
adjustments)
Or with UIKit



## With SwiftUi (preferred)
For the sake of the example, we will inject a component showing a recipe card in the host app. InMiam, recipe cards can either be "fixed" (= fetched by on a predefined ID) or "suggested" (= fetched
based on the user navigation context)

```swift
// ContentView.swift

// Implemented in miamCore can be use directly
data class SuggestionsCriteria(
  // Ids of products displayed in the search results, right before and after the recipe card
  val shelfIngredientsIds: List<String>? = null,
  // Ids of products displayed on a product details page (optional)
  val currentIngredientsIds: List<String>? = null,
  // Ids of products already in app basket (optional)
  val basketIngredientsIds: List<String>? = null,
  // (optional)
  val groupId: String? = null
)

 var criteria = SuggestionsCriteria(
        shelfIngredientsIds: ["5319173","970417"],
            currentIngredientsIds:nil,
            basketIngredientsIds: nil,
            groupId: nil
        
    )

 var body: some View {
      VStack {
          // Direct recipe load
          RecipeCardView(recipeId: "1")
          // load recipe with criteria
          RecipeCardView(criteria: criteria)
      }

```

## With UIKit

You first need to wrappe our component in a Hosting View Controller :

```swift
//HostingViewController.swift
import UIKit
import SwiftUI
import MiamIOSFramework

class HostingViewController: UIHostingController<RecipeCardView> {
    // Initialize our controller with RecipeCardView as a root view and show
    // recipe 1.
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder, rootView: RecipeCardView(recipeId: "1"))
    }
}
```

Then it can be use this way : 

```xml
<!-- Main.storyboard -->  
<?xml version="1.0" encoding="UTF-8"?>
<document type="com.apple.InterfaceBuilder3.CocoaTouch.Storyboard.XIB" version="3.0" toolsVersion="20037" targetRuntime="iOS.CocoaTouch" propertyAccessControl="none" useAutolayout="YES" useTraitCollections="YES" useSafeAreas="YES" colorMatched="YES" initialViewController="QP6-eJ-Dne">
    <device id="retina6_1" orientation="portrait" appearance="light"/>
    <dependencies>
        <plugIn identifier="com.apple.InterfaceBuilder.IBCocoaTouchPlugin" version="20020"/>
    </dependencies>
    <scenes>
        <!--Hosting View Controller-->
        <scene sceneID="rfR-4P-zX7">
            <objects>
                <hostingController id="QP6-eJ-Dne" customClass="HostingViewController" customModule="SampleUIKitIntegration" customModuleProvider="target" sceneMemberID="viewController"/>
                <placeholder placeholderIdentifier="IBFirstResponder" id="jnC-Hw-boD" userLabel="First Responder" customClass="UIResponder" sceneMemberID="firstResponder"/>
            </objects>
            <point key="canvasLocation" x="-157" y="-571"/>
        </scene>
    </scenes>
</document>

```