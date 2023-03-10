# 3.10.0
- [FEA][COR] Enable explicit API mode and compiler flag warning as error
- [FEA][COR] Draft factories to help creating fakes in previews and tests
- [FEA][COR] Adapt BaseViewModel to work with only one dispatcher
- [FEA][COR] Introduce Gradle Version Catalog + Improve configuration
- [FEA][COR] I18n
# 3.9.5
- [FIX][IOS] Fix wrong product description in basketPreviewRowTemplate

# 3.9.4
- [FIX][IOS] Add missing parameters to RecipeModalTemplate

# 3.9.3
- [FIX][IOS] Make all view models public

# 3.9.2
- [FIX][IOS] Change visibility of published properties to public in PreferencesSearchVM
- [FIX][IOS] Fix navigation link appearing in wireframe at the bottom of basket view
- [FIX][IOS] Use users' locale to format price 
- [FIX][AND] Use users' locale to format price 

# 3.9.1
- [FIX][IOS] Make PreferencesSearchView and CatalogPreferencesView public

# 3.9.0
- [FEA][AND] Add generic error template 
- [FIX][IOS] Fix extra padding in my meal view
- [FIX][IOS] Add flag to show/hide "meal ideas" image in navigation bar
- [FEA][IOS] Add generic error template 

# 3.8.1
- [FIX][AND] Prefrence search modal now handle back press correctly
- [FIX][AND] Fix crash on itemSelector openning
- [FIX][AND] Add a loader on guest counter in basket preview
- [FIX][IOS] Add a loader on guest counter in basket preview and on items row
- [FIX][IOS] Counter in recipe detail can't go under 1 add a isReloading parameter to template

# 3.8.0
- [FEA][COR] Expose analytic events and closure
- [FEA][IOS] Use native navigation for recipe details, basket preview, basket tags list and item selector

- [FIX][IOS] Remove more info buton on recipe detail
- [FIX][IOS] Hide recipe times when equal to 0 on recipe detail
- [FIX][IOS] Remove n/a when price is reloading
- [FIX][IOS] Remove force unwrapp on picture URL add a default picture fall back
- [FIX][IOS] Fix recipe detail header overloap by body
- [FIX][AND] Remove padding around SearchPreferencesTemplate
- [FIX][AND] Navigation on catalog was perturb by mutliple instance its controleur 
- [FIX][COR] Round ingr??dients quantity in recipe detail

# 3.7.0
- [FEA][AND] Add price template 
- [FIX][AND] Use android app context

# 3.6.0
- [FIX][AND] Recipes page no long reloading after closing a dialog
- [FIX][AND] Template for categories page now expose a CatalogPageTitleTemplateParameters that contains subtitle 
- [FEA][AND] Guest prefrences avalaible
- [FEA][AND] New template for recipe search page 
- [FEA][IOS] Guest prefrences avalaible
- [FIX][IOS] Template for categories page now expose a CatalogPageTitleTemplateParameters that contains subtitle 
- [FIX][IOS] New template basket preview title and MyMeals action column
- [FIX][AND] Counter local state falsly remembered

# 3.5.0
- [FEA][COR] Enable guests preferences
- [FIX][COR] Fix navigation when a dialog was between two pages
- [FEA][COR] Preference dependence on supplier
- [FEA][AND] Comfirme prefrerences now close dialog instead of redirecting to a search result
- [FEA][AND] CatalogSearch is now a stand alone component 
- [FIX][IOS] Add onQuantityChanged closure to basketPreviewRowTemplate and change item link avaibility 
- [FIX][IOS] package subtitle exposed
- [FIX][IOS] MyMealButton onTapped closure used
- [FIX][COR] Ingredient search
- [FIX][COR] Better like listenning
- [FIX][AND] fix navigation loop between absket preview and item selector


# 3.4.2
- [FIX][COR] Like initial state
- [FIX][COR] Recipe card, do not set on error on unknow state

# 3.4.1
- [FIX][COR] Report 3.3.1 fix on filter to 3.4
- [FEA][COR] Router service previous now return new current route

# 3.4.0
- [FEA][COR] New Route Service
- [FEA][IOS] Native navigation in Catalog
- [FIX][IOS] Typography harmonisation and overridable
- [FEA][AND] Backpress supported
- [FIX][AND] Price format
- [FIX][AND] Padding ouside of a template in BasketPreviewItem
- [BRK][AND] SearchPreferencesTemplate template signature changed
- [BRK][AND] CatalogHeader template signature changed


# 3.3.1
- [FIX][COR] Probl??me d'application des filtres

# 3.3.0
- [FEA][COR] Clean counter usage
- [FEA][COR] Optimize price component
- [FIX][AND] Add missing templates in like button and preferences search
- [FIX][IOS] Unwrapp AsyncImage URL and replace placeholder 
- [FIX][IOS] Add function preferenceTapped to catalogViewToolbarTemplate
- [FIX][AND] Fix catalog Empty state background color not overridable
- [FEA][AND] add entrie line templates in basket preview 
- [FIX][AND] Add a floating template to category list alow to customize container modifier

# 3.2.0
- [FIX][AND] Add missing templates in myMeal and item selector
- [FIX][AND] Add missing parameter in catalog header template
- [FIX][AND] Remove divider not overridable from recipe detail
- [FIX][IOS] Fix AsyncImage constructor visibility
- [FIX][IOS] Add missing parametter to like button template
- [FEA][IOS] SPM is available for IOS 12
- [FIX][IOS] Add missing parameter to like button template
- [FIX][COR] Do not need context to be set to be ready
- [FIX][COR] Pr??ferences didn not take into account default value to get filter string
- [FIX][COR] Expendable entry line template. Action can be null (usd when no item is found)

## 3.1.0
- [FIX][AND] Fix Recipe card CTA text is now customizable
- [FIX][AND] Fix modal now fit correctly in screen
- [FIX][COR] Analytics domain, user agent and multiple sent show event
- [FIX][IOS] Add templates and some public modifiers
- [FEA][AND] Change lazy column into lazy grid in recipes list
- [BRK][FIX][AND] Fix recipes page lazy loading by upgrading compose (1.3.1) and android SDK (33)

## 3.0.1
- [FIX][AND] Fix loader on prefrences screen
- [FIX][AND] Disable guests pref
- [FIX][COR] Fetch local excluded ingredient pref on init
- [FIX][COR] Can't add two time same ingredient to excluded list in pref
- [FIX][COR] Check an ingredient when you search and select it

## 3.0.0
- [FEA][COR] Toaster handler for add and like recipes
- [FEA][COR] Singleton catalog filter service
- [FEA][COR] Picture is avalaible in recipes ingredients
- [FEA][AND] Add user preferences
- [FEA][AND] Refact catalog header
- [FEA][AND] My meal counter button
- [FEA][IOS] Add user preferences
- [FEA][IOS] My meal counter button
- [BRK][AND] Upgrade koin version 

## 2.5.2
- [FIX][IOS] Pass browse catalog action to favorites empty view template

## 2.5.1
- [FIX][IOS] Add missing browse catalog action in FavoritesView

## 2.5.0
- [FEA][COR] Prevent infinit loading on basket preview if miam is not well configure
- [FIX][IOS] Add missing template for recipe card loading view
- [FEA][IOS] Add a parameter to control the height of recipe card view. 
- [FEA][IOS] Recipe carousel

## 2.4.0
- [FEA][COR] carousel view model
- [FEA][AND] recipe carousel component
- [FIX][IOS] Infinite loading on recipe card when using a template
- [FIX][IOS] Add missing templates

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
- [FIX][COR] Dynamic stand alone favorite page controleur
- [FIX][COR] Analytics add pos id to confirm event
- [FIX][COR] add params to template

## 2.2.7
- [FIX][COR] Avoid div by zero

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
