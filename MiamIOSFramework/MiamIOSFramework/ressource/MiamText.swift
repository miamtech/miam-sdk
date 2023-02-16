//
//  MiamText.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import Foundation

public class MiamText {

    public static let sharedInstance = MiamText()

    @available(*, deprecated, message: "Use Localization.recipe.add instead")
    public var addRecipe = Localization.recipe.add.localised
    @available(*, deprecated, message: "Use Localization.recipe.showBasketPreview instead")
    public var alreadyInCart = Localization.recipe.showBasketPreview.localised
    @available(*, deprecated, message: "Use Localization.recipe.cookTime instead")
    public var cookTime = Localization.recipe.cookTime.localised
    @available(*, deprecated, message: "Use Localization.recipe.add instead")
    public var checkBasketPreview = Localization.recipe.add.localised
    @available(*, deprecated, message: "Use Localization.price.currency instead")
    public var currency = Localization.price.currency.localised

    @available(*, deprecated, message: "Use Localization.recipe.numberOfIngredients instead")
    public var ingredients = "ingrédients"

    @available(*, deprecated, message: "Use Localization.price.perGuest instead")
    public var preGuests = Localization.price.perGuest.localised
    @available(*, deprecated, message: "Use Localization.recipe.flag instead")
    public var recipeFlag = Localization.recipe.flag.localised

    @available(*, deprecated, message: "Use Localization.itemSelector.replaceProduct instead")
    public var replaceBy = Localization.itemSelector.replaceProduct.localised
    @available(*, deprecated, message: "Use Localization.itemSelector.inBasket instead")
    public var selectedProduct = Localization.itemSelector.inBasket.localised
    @available(*, deprecated, message: "Use Localization.itemSelector.select instead")
    public let select = Localization.itemSelector.select.localised

    @available(*, deprecated, message: "Use Localization.recipe.preparationTime instead")
    public var preparation = Localization.recipe.preparationTime.localised
    public let recpeitDetailsInfo = "Plus d'informations"
    @available(*, deprecated, message: "Use Localization.basket.ownedProducts instead")
    public let mealRowAlready = "Déjà dans vos placards"
    @available(*, deprecated, message: "Use Localization.basket.unavailableProducts instead")
    public let mealRowNotFound = "Produits indisponibles"
    @available(*, deprecated, message: "Use Localization.basket.removedProducts instead")
    public let mealRowRemoved = "Articles retirés du panier"

    @available(*, deprecated, message: "Use Localization.basket.swapProduct instead")
    public let swapProduct = Localization.basket.swapProduct.localised
    @available(*, deprecated, message: "Use Localization.recipe.lowDifficulty instead")
    public var difficultyEasy = Localization.recipe.lowDifficulty.localised
    @available(*, deprecated, message: "Use Localization.recipe.mediumDifficulty instead")
    public var difficultyMid = Localization.recipe.mediumDifficulty.localised
    @available(*, deprecated, message: "Use Localization.recipe.highDifficulty instead")
    public var difficultyHard = Localization.recipe.highDifficulty.localised
    @available(*, deprecated, message: "Use Localization.recipe.preparationTime instead")
    public var preparationTime = Localization.recipe.preparationTime.localised
    @available(*, deprecated, message: "Use Localization.recipe.cookTime instead")
    public var cookingTime = Localization.recipe.cookTime.localised
    @available(*, deprecated, message: "Use Localization.recipe.restingTime instead")
    public var restingTime = Localization.recipe.restingTime.localised
    @available(*, deprecated, message: "Use Localization.catalog.loadingText instead")
    public var simmering = Localization.catalog.loadingText.localised
    @available(*, deprecated, message: "Use Localization.recipe.showDetails instead")
    public var viewRecipeDetail = Localization.recipe.showDetails.localised

    @available(*, deprecated, message: "Use Localization.recipe.steps instead")
    public var steps = Localization.recipe.steps.localised

    @available(*, deprecated, message: "Use Localization.catalog.browseRecipes instead")
    public var browseRecipesText = Localization.catalog.browseRecipes.localised
    @available(*, deprecated, message: "Use Localization.catalog.noRecipeFound instead")
    public var noRecipeFoundText = Localization.catalog.noRecipeFound.localised
    @available(*, deprecated, message: "Use Localization.catalog.tryAnotherSearch instead")
    public var tryAnotherSearchText = Localization.catalog.tryAnotherSearch.localised

    @available(*, deprecated, message: "Use Localization.catalog.filtersTitle instead")
    public var filtersTitle = Localization.catalog.filtersTitle.localised

    @available(*, deprecated, message: "Use Localization.catalog.difficulty instead")
    public var filtersDifficultySectionTitle = Localization.catalog.difficulty.localised

    @available(*, deprecated, message: "Use Localization.catalog.costPerPerson instead")
    public var filterCostSectionTitle = Localization.catalog.costPerPerson.localised

    @available(*, deprecated, message: "Use Localization.catalog.preparationTime instead")
    public var filterPreparationTimeSectionTitle = Localization.catalog.preparationTime.localised

    @available(*, deprecated, message: "Use Localization.catalog.removeFilters instead")
    public var removeFiltersButtonTitle = Localization.catalog.removeFilters.localised

    @available(*, deprecated, message: "Use Localization.favorites.noFavorites instead")
    public var noFavoritRecipeYet = Localization.favorites.noFavorites.localised
    @available(*, deprecated, message: "Use Localization.catalog.searchPlaceholder instead")
    public var search = Localization.catalog.searchPlaceholder.localised
    @available(*, deprecated, message: "Use Localization.catalog.showAll instead")
    public var showAll = Localization.catalog.showAll.localised
    @available(*, deprecated, message: "Use Localization.catalog.title instead")
    public var mealIdeas = Localization.catalog.title.localised

    @available(*, deprecated, message: "Use Localization.favorites.title instead")
    public var myMealIdeas = Localization.favorites.title.localised
    @available(*, deprecated, message: "Use Localization.myMeals.noMealIdeaInBasket instead")
    public var noMealIdeaInBasket = Localization.myMeals.noMealIdeaInBasket.localised

    @available(*, deprecated, message: "Use Localization.basket.addProduct instead")
    public var addIngredientText = Localization.basket.addProduct.localised
    @available(*, deprecated, message: "Use Localization.recipe.showDetails instead")
    public var showDetails = Localization.recipe.showDetails.localised

    @available(*, deprecated, message: "Use Localization.basket.removeRecipe instead")
    public var removeFromBasket = Localization.basket.removeRecipe.localised
    @available(*, deprecated, message: "Use Localization.basket.continueShopping instead")
    public var keepShopping = Localization.basket.continueShopping.localised

    @available(*, deprecated, message: "Use Localization.basket.swapProduct instead")
    public var replaceIngredient = Localization.basket.swapProduct.localised

    @available(*, deprecated, message: "Use Localization.counter.persons instead")
    public var persons = Localization.counter.persons.localised

    @available(*, deprecated, message: "Use Localization.preferences.numberOfGuests instead")
    public var numberOfPersons = Localization.preferences.numberOfGuests.localised

    public var mealsAdded = "repas ajouté"
    public var mealsAddedPlural = "repas ajoutés"

    @available(*, deprecated, message: "Use Localization.preferences.cancel instead")
    public var cancel = Localization.preferences.cancel.localised
    @available(*, deprecated, message: "Use Localization.preferences.addTag instead")
    public var addTag = Localization.preferences.addTag.localised

    @available(*, deprecated, message: "Use Localization.catalog.showResults instead")
    public var see = "Voir les"
    @available(*, deprecated, message: "Use Localization.catalog.showResults instead")
    public var meals = "repas"

    @available(*, deprecated, message: "Use Localization.preferences.searchPlaceholder instead")
    public var searchTagPlaceholder = Localization.preferences.searchPlaceholder.localised

    @available(*, deprecated, message: "Use Localization.preferences.title instead")
    public var preferencesTitle = Localization.preferences.title.localised
    @available(*, deprecated, message: "Use Localization.preferences.searchTitle instead")
    public var preferencesSearchTitle = Localization.preferences.searchTitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.dietTitle instead")
    public var dietTitle = Localization.preferences.dietTitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.dietSubtitle instead")
    public var dietSubtitle = Localization.preferences.dietSubtitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.cookingEquipmentTitle instead")
    public var cookingModesTitle = Localization.preferences.cookingEquipmentTitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.cookingEquipmentSubtitle instead")
    public var cookingModesSubtitle = Localization.preferences.cookingEquipmentSubtitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.tastesTitle instead")
    public var tastesTitle = Localization.preferences.tastesTitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.tastesSubtitle instead")
    public var tastesSubtitle = Localization.preferences.tastesSubtitle.localised

    @available(*, deprecated, message: "Use Localization.preferences.searchPrefix instead")
    public var prefixWordSearchTitle = Localization.catalog.searchPrefix.localised

    @available(*, deprecated, message: "Use Localization.preferences.searchTitle instead")
    public var filterSearchTitle = Localization.catalog.searchTitle.localised

    @available(*, deprecated, message: "Use Localization.favorites.title instead")
    public var favoriteTitle = Localization.favorites.title.localised

    private init() {}
}
