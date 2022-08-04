## 2.2.2
- [FIX][IOS] Fix checkbox in recipe detail
- [FIX][IOS] Fix like icon oppening next recipe card on catalog 
- [FIX][IOS] Fix catalog recipe loader size
- [FIX][IOS] Reduce meal tqg border radius on recipe card


## 2.2.1
- [FIX][CI] fix commit variable name CI
- [FIX][CI] target only android SDK task

## 2.2.0
- [FEA][AND] AAR available on maven central
- [FEA][AND] CI:CD deploy and build

## 2.1.0
- [BRK][IOS] RecpieDetails has been renamed to RecipeDetails
- [FEA][IOS] Customizable colors
- [FEA][IOS] Available on swift package manager
- [FIX][IOS] Recipe steps are now correctly marked as checked
- [FIX][COR] Infinit loading of myMeal page
- [FEA][IOS] Customizable icons
- [FIX][IOS] Loader display

## 2.0.2
- [FIX][CORE] Recipe 'is in cart' not well refreshed
- [FIX][CORE] Handle loading and Empty state in MyMealViewModel
- [FIX][IOS] Add my meal EmptyStateView
- [FIX][IOS] Add my meal LoadingStateView
- [FIX][IOS] Missing clock icon in recipe card and recipe details

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
