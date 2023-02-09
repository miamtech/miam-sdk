//
//  MiamText.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import Foundation

public class MiamText {

    public static let sharedInstance = MiamText()

    public var addRecipe = "Découvrir la recette"
    public var alreadyInCart = "Voir les produits"
    public var cookTime = "Temps de cuisson"
    public var checkBasketPreview = Localization.recipe.add.localised
    public var currency = Localization.price.currency.localised
    public var ingredients = "ingredients"
    public var preGuests = Localization.price.perGuest.localised
    public var recipeFlag = "Idée repas"
    public var replaceBy = "Remplacer cet article par : "
    public var preparation = "Préparation"
    public var totalTime = "Temps total"
    public let recpeitDetailsInfo = "Plus d'informations"
    public let mealRowAlready = "Déjà dans vos placards"
    public let mealRowNotFound = "Produits indisponibles"
    public let mealRowRemoved = "Articles retirés du panier"
    public let select = "Sélectionner"
    public let swapProduct = "Remplacer le produit"
    public var difficultyEasy = Localization.recipe.lowDifficulty.localised
    public var difficultyMid = Localization.recipe.mediumDifficulty.localised
    public var difficultyHard = Localization.recipe.highDifficulty.localised
    public var preparationTime = "Préparation"
    public var cookingTime = Localization.recipe.cookTime.localised
    public var restingTime = Localization.recipe.restingTime.localised
    public var simmering = Localization.catalog.loadingText.localised
    public var viewRecipeDetail = Localization.recipe.showDetails.localised
    public var selectedProduct = "Dans le panier"
    public var steps = Localization.recipe.steps.localised
    public var browseRecipesText = "Parcourir les idées repas"
    public var noRecipeFoundText = "Oups, aucune recette n'a été trouvée"
    public var tryAnotherSearchText = "Essayez une nouvelle recherche"
    public var addRecipeText = "Ajouter une idée repas"

    public var filtersTitle = "Affiner ma sélection"
    public var filtersDifficultySectionTitle = Localization.catalog.difficulty.localised
    public var filterCostSectionTitle = Localization.catalog.costPerPerson.localised
    public var filterPreparationTimeSectionTitle = "Temps de préparation"
    public var removeFiltersButtonTitle = Localization.catalog.removeFilters.localised

    public var noFavoritRecipeYet = "Oups, vous n'avez pas encore d'idée repas"
    public var search = "Rechercher"
    public var showAll = "Tout voir"
    public var mealIdeas = "Idées repas en 1 clic"
    public var myMealIdeas = "Mes idées repas"

    public var noMealIdeaInBasket = "Vous n'avez aucune idée repas dans votre panier."
    public var addIngredientText = "Ajouter"
    public var showDetails = "Voir le détail"

    public var removeFromBasket = "Retirer du panier"
    public var keepShopping = "Continuer mes achats"

    public var moreInformation = "Plus d'infos"
    public var replaceIngredient = Localization.basket.swapProduct.localised

    public var persons = "pers."
    public var numberOfPersons = "Nombre de personnes"

    public var mealsAdded = "repas ajouté"
    public var mealsAddedPlural = "repas ajoutés"
    public var cancel = "Annuler"
    public var see = "Voir les"
    public var meals = "repas"
    public var addTag = "Ajouter +"

    public var searchTagPlaceholder = Localization.preferences.searchPlaceholder.localised

    public var preferencesTitle = Localization.preferences.title.localised
    public var preferencesSearchTitle = "Je n'aime pas"
    public var dietTitle = Localization.preferences.dietTitle.localised
    public var dietSubtitle = Localization.preferences.dietSubtitle.localised
    public var cookingModesTitle = Localization.preferences.cookingEquipmentTitle.localised
    public var cookingModesSubtitle = Localization.preferences.cookingEquipmentSubtitle.localised
    public var tastesTitle = Localization.preferences.tastesTitle.localised
    public var tastesSubtitle = Localization.preferences.tastesSubtitle.localised

    public var prefixWordSearchTitle = "Votre recherche :"
    public var filterSearchTitle = "Votre Sélection"
    public var favoriteTitle = "Mes idées repas"

    private init() {}
}
