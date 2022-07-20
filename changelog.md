## 2.0.1
- [FIX] Recipe 'is in cart' not well refreshed

## 2.0.1
- [FIX] Missing clock icon in recipe card and recipe details
- [FIX] Clip images that were bigger than their frame
- [FIX] Right/left padding in item selector
- [FIX] Truncated icon in title bar
- [FIX][CORE] Remove miam core pods spec file

## 2.0.0
- [BRK] shared module is now called miamCore module impact Ios and android
- [BRK] kmm-miam-sdk in core module now call kmmMiamCore fix bundleId issue on Ios impact on android

## 1.2.0
- [FEA][IOS] Like recipe.
- [FEA][IOS][Android] You can disable like feature.
- [FEA][IOS][Android] Recipe card v2.
- [FEA][IOS][Android] Catalog.
- [FIX][IOS] Removed navigation view from my meals view.
- [FIX][IOS][Android] Provide a first value when using onRecipeCountChange
- [FIX][IOS] Infinit loading on add recipe from recipe card

## 1.1.0
- [BRK] getReadyIos is now called onReadyEvent
- [BRK][FEA] MiamGroceriesList is replaced by GroceriesListHandler with two functions onRecipeCountChange and getRecipeCountChangeFlow
- [FEA] Expose resetGroceriesList on GroceriesListHandler
