## 2.3.0
- [FIX][IOS] Fix Auto resize for UIkit table cells
- [FIX][IOS] Improve recipe detail view recomposition
- [FIX][IOS] Add CTA in empty favorite page
- [FIX][IOS] Catalog filter is now a scrollable view
- [FEA][IOS] Add stand alone favorite page
- [FEA][AND] Improve recipe card view
- [FEA][AND] Improve recipe detail view recomposition
- [FEA][AND] Improve product matching view recomposition
- [FEA][AND] Improve product matching default design
- [FEA][AND] Direct access to a category on catalog with an id
- [FIX][AND] Add piture field in product and recipe templates in basket preview
- [FIX][AND] Text in catalog research ans favorit empty view is centred
- [FEA][COR] Simplifying Price ViewModel
- [FEA][COR] Change category navigation event on Catalog ViewModel
- [FEA][COR] Exposition of catalog categories list
- [FIX][COR] Gests counter block at 1 or 100
- [FIX][COR] Analytics add pos id to confirm event

## 2.2.6
- [FIX][AND] Difficulty checkbox in filter are now working
- [FIX][COR] Number of records given on filters is wrong
- [FIX][AND] Fix search's textfield text disparition on typing

## 2.2.5
- [FIX][COR] recipe like heart not well recycled makes unwanted apparition
- [FIX][COR] basket preview guest change was broken
- [FIX][COR] basket preview when deleting a product its quantity artificialy stay on next one
- [FEA][COR] add missing Analytics

## 2.2.4
- [FIX][IOS] Fix SwiftUI imports

## 2.2.3
- [FIX][IOS] Fix crash IOS older than 13

## 2.2.2
- [FIX][IOS] Fix checkbox in recipe detail
- [FIX][IOS] Fix like icon oppening next recipe card on catalog
- [FIX][IOS] Fix recipe image size in recipe details
- [FIX][IOS] Fix catalog recipe loader size
- [FIX][IOS] Reduce meal tag border radius on recipe card
- [FIX][IOS] Fix like button style
- [FIX][IOS] Reverse add button in recipe details
- [FIX][IOS] Fix recipe image size in recipe details
- [FIX][IOS] Shelf recipes has no longer border radius
- [FIX][IOS] Fix replace product button size
- [FIX][IOS] Fix itemSelector CTA button size and text
- [FIX][IOS] Fix ingredients name first lettre to uppercase in basket preview
- [FIX][IOS] Fix bigger product quantity button
- [FIX][IOS] Recipes details has no longer more infos button
- [FIX][IOS] Recipes details infos are now align left
- [FIX][IOS] Recipes card infos are now align center vertically
- [FIX][IOS] Margin between recipe in catalog result page
- [FIX][IOS] Title position in catalog result page
- [FIX][IOS] Change ingredients background color in recipe details
- [FIX][IOS] Item selector feet figma style
- [FIX][IOS] Recipe title goes in header after scrolling down a break point
- [FIX][IOS] Ingredients now have a tag if they are used in more than one recipe
- [FIX][IOS] Ingredients and step tilte is black in recipe detail
- [FIX][AND] Button delete on ingredient row is no longer hide if description is too long
- [FIX][AND] Title on recipe card is now clicable and redirect to detail
- [FIX][AND] New design on recipe detail header
- [FIX][AND] Remove "more infos" section
- [FIX][AND] Change recipe icon size on recipe card
- [FIX][COR] Tag on ingredient wording changed
- [FIX][COR] Price initial state is now Loading avoid showing 0 when repainting view

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
- [BRK][FEA] MiamGroceriesList is replaced by GroceriesListHandler with two functions
  onRecipeCountChange and getRecipeCountChangeFlow
- [FEA] Expose resetGroceriesList on GroceriesListHandler
