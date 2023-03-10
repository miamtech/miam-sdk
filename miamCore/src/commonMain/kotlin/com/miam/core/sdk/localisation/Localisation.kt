package com.miam.core.sdk.localisation

import com.miam.core.localisation.i18n.I18n
import com.miam.sdk.resources.MiamSdkResources

private val miamStrings = runCatching { MiamSdkResources.strings }.getOrNull()
private val miamPlurals = runCatching { MiamSdkResources.plurals }.getOrNull()

public object Localisation {
    public object Recipe {
        public val add: I18n
            get() = I18n.string("com_miam_recipe_add", miamStrings?.com_miam_recipe_add)

        public val added: I18n
            get() = I18n.string("com_miam_recipe_added", miamStrings?.com_miam_basket_recipe_added)

        public val lowDifficulty: I18n
            get() = I18n.string("com_miam_recipe_difficulty_low", miamStrings?.com_miam_recipe_difficulty_low)

        public val mediumDifficulty: I18n
            get() = I18n.string("com_miam_recipe_difficulty_medium", miamStrings?.com_miam_recipe_difficulty_medium)

        public val highDifficulty: I18n
            get() = I18n.string("com_miam_recipe_difficulty_high", miamStrings?.com_miam_recipe_difficulty_high)

        public val showDetails: I18n
            get() = I18n.string("com_miam_recipe_is_in_cart", miamStrings?.com_miam_recipe_is_in_cart)

        public val cookTime: I18n
            get() = I18n.string("com_miam_recipe_cook_time", miamStrings?.com_miam_recipe_cook_time)

        public val preparationTime: I18n
            get() = I18n.string("com_miam_recipe_preparation_time", miamStrings?.com_miam_recipe_preparation_time)

        public val restingTime: I18n
            get() = I18n.string("com_miam_recipe_resting_time", miamStrings?.com_miam_recipe_resting_time)

        public val steps: I18n
            get() = I18n.string("com_miam_recipe_steps", miamStrings?.com_miam_recipe_steps)

        public val showBasketPreview: I18n
            get() = I18n.string("com_miam_recipe_check_basket_preview", miamStrings?.com_miam_recipe_check_basket_preview)

        public val flag: I18n
            get() = I18n.string("com_miam_recipe_flag", miamStrings?.com_miam_recipe_flag)

        public fun numberOfIngredients(numberOfIngredients: Int): I18n {
            return I18n.string("com_miam_recipe_number_of_ingredients", miamStrings?.com_miam_recipe_number_of_ingredients, numberOfIngredients)
        }
    }

    public object ItemSelector {
        public val replaceProduct: I18n
            get() = I18n.string("com_miam_item_selector_replace_by", miamStrings?.com_miam_item_selector_replace_by)

        public val inBasket: I18n
            get() = I18n.string("com_miam_item_selector_in_basket", miamStrings?.com_miam_item_selector_in_basket)

        public val select: I18n
            get() = I18n.string("com_miam_item_selector_select", miamStrings?.com_miam_item_selector_select)
    }

    public object Basket {
        public val recipeAdded: I18n
            get() = I18n.string("com_miam_basket_recipe_added", miamStrings?.com_miam_basket_recipe_added)

        public val continueShopping: I18n
            get() = I18n.string("com_miam_basket_continue_shopping", miamStrings?.com_miam_basket_continue_shopping)

        public val moreDetails: I18n
            get() = I18n.string("com_miam_basket_more_details", miamStrings?.com_miam_basket_more_details)

        public val removeRecipe: I18n
            get() = I18n.string("com_miam_basket_remove_recipe", miamStrings?.com_miam_basket_remove_recipe)

        public fun unavailableProducts(numberOfProducts: Int): I18n {
            return I18n.string("com_miam_basket_unavailable_products", miamStrings?.com_miam_basket_unavailable_products, numberOfProducts)
        }

        public fun ownedProducts(numberOfProducts: Int): I18n {
            return I18n.string("com_miam_basket_owned_products", miamStrings?.com_miam_basket_owned_products, numberOfProducts)
        }

        public fun removedProducts(numberOfProducts: Int): I18n {
            return I18n.string("com_miam_basket_removed_products", miamStrings?.com_miam_basket_removed_products, numberOfProducts)
        }

        public val swapProduct: I18n
            get() = I18n.string("com_miam_basket_swap_product", miamStrings?.com_miam_basket_swap_product)

        public val addProduct: I18n
            get() = I18n.string("com_miam_basket_add_product", miamStrings?.com_miam_basket_add_product)

        public val person: I18n
            get() = I18n.string("com_miam_basket_person", miamStrings?.com_miam_basket_person)
    }

    public object Favorites {
        public val title: I18n
            get() = I18n.string("com_miam_favorites_title", miamStrings?.com_miam_favorites_title)

        public val noFavorites: I18n
            get() = I18n.string("com_miam_favorites_no_favorites", miamStrings?.com_miam_favorites_no_favorites)

        public val goToCatalog: I18n
            get() = I18n.string("com_miam_favorites_go_to_catalog", miamStrings?.com_miam_favorites_go_to_catalog)
    }

    public object Catalog {
        public val title: I18n
            get() = I18n.string("com_miam_catalog_title", miamStrings?.com_miam_catalog_title)

        public val showAll: I18n
            get() = I18n.string("com_miam_catalog_show_all", miamStrings?.com_miam_catalog_show_all)

        public val loadingText: I18n
            get() = I18n.string("com_miam_catalog_loading_text", miamStrings?.com_miam_catalog_loading_text)

        public val favoriteTitle: I18n
            get() = I18n.string("com_miam_catalog_favorites_title", miamStrings?.com_miam_catalog_favorites_title)

        public val difficulty: I18n
            get() = I18n.string("com_miam_catalog_filter_difficulty", miamStrings?.com_miam_catalog_filter_difficulty)

        public val costPerPerson: I18n
            get() = I18n.string("com_miam_catalog_filter_cost_per_person", miamStrings?.com_miam_catalog_filter_cost_per_person)

        public val preparationTime: I18n
            get() = I18n.string("com_miam_catalog_filter_preparation_time", miamStrings?.com_miam_catalog_filter_preparation_time)

        public val filtersTitle: I18n
            get() = I18n.string("com_miam_catalog_filter_title", miamStrings?.com_miam_catalog_filter_title)

        public val removeFilters: I18n
            get() = I18n.string("com_miam_catalog_filter_remove_filters", miamStrings?.com_miam_catalog_filter_remove_filters)

        public val searchPlaceholder: I18n
            get() = I18n.string("com_miam_catalog_filter_search_placeholder", miamStrings?.com_miam_catalog_filter_search_placeholder)

        public val searchPrefix: I18n
            get() = I18n.string("com_miam_catalog_filter_search_prefix", miamStrings?.com_miam_catalog_filter_search_prefix)

        public val searchTitle: I18n
            get() = I18n.string("com_miam_catalog_filter_search_title", miamStrings?.com_miam_catalog_filter_search_title)

        public val preferencesNoResult: I18n
            get() = I18n.string("com_miam_catalog_preferences_no_result", miamStrings?.com_miam_catalog_preferences_no_result)

        public val preferencesTryAnother: I18n
            get() = I18n.string("com_miam_catalog_prefrences_try_another", miamStrings?.com_miam_catalog_prefrences_try_another)

        public val browseRecipes: I18n
            get() = I18n.string("com_miam_catalog_browse_recipes", miamStrings?.com_miam_catalog_browse_recipes)

        public val noRecipeFound: I18n
            get() = I18n.string("com_miam_catalog_no_recipe_found", miamStrings?.com_miam_catalog_no_recipe_found)

        public val tryAnotherSearch: I18n
            get() = I18n.string("com_miam_catalog_try_another_search", miamStrings?.com_miam_catalog_try_another_search)

        public fun showResults(numberOfResults: Int): I18n {
            return I18n.string("com_miam_catalog_filter_show_results", miamStrings?.com_miam_catalog_filter_show_results, numberOfResults)
        }
    }

    public object Preferences {
        public val title: I18n
            get() = I18n.string("com_miam_preferences_title", miamStrings?.com_miam_preferences_title)

        public val cancel: I18n
            get() = I18n.string("com_miam_preferences_cancel", miamStrings?.com_miam_preferences_cancel)

        public val addTag: I18n
            get() = I18n.string("com_miam_preferences_add_tag", miamStrings?.com_miam_preferences_add_tag)

        public val numberOfGuests: I18n
            get() = I18n.string("com_miam_preferences_number_of_guests", miamStrings?.com_miam_preferences_number_of_guests)

        public val dietTitle: I18n
            get() = I18n.string("com_miam_preferences_diet_title", miamStrings?.com_miam_preferences_diet_title)

        public val tastesTitle: I18n
            get() = I18n.string("com_miam_preferences_tastes_title", miamStrings?.com_miam_preferences_tastes_title)

        public val cookingEquipmentTitle: I18n
            get() = I18n.string("com_miam_preferences_cooking_equipment_title", miamStrings?.com_miam_preferences_cooking_equipment_title)

        public val dietSubtitle: I18n
            get() = I18n.string("com_miam_preferences_diet_subtitle", miamStrings?.com_miam_preferences_diet_subtitle)

        public val tastesSubtitle: I18n
            get() = I18n.string("com_miam_preferences_tastes_subtitle", miamStrings?.com_miam_preferences_tastes_subtitle)

        public val cookingEquipmentSubtitle: I18n
            get() = I18n.string("com_miam_preferences_cooking_equipment_subtitle", miamStrings?.com_miam_preferences_cooking_equipment_subtitle)

        public val searchTitle: I18n
            get() = I18n.string("com_miam_preferences_search_title", miamStrings?.com_miam_preferences_search_title)

        public val searchPlaceholder: I18n
            get() = I18n.string("com_miam_preferences_search_placeholder", miamStrings?.com_miam_preferences_search_placeholder)
    }

    public object Price {
        public val currency: I18n
            get() = I18n.string("com_miam_price_currency", miamStrings?.com_miam_price_currency)

        public val perGuest: I18n
            get() = I18n.string("com_miam_price_per_guest", miamStrings?.com_miam_price_per_guest)
    }

    public object Error {
        public val generic: I18n
            get() = I18n.string("com_miam_generic_error", miamStrings?.com_miam_generic_error)
    }

    public object Tag {
        public val preRecipeCount: I18n
            get() = I18n.string("com_miam_tag_pre_recipe_count", miamStrings?.com_miam_tag_pre_recipe_count)

        public val postRecipeCount: I18n
            get() = I18n.string("com_miam_tag_post_recipe_count", miamStrings?.com_miam_tag_post_recipe_count)
    }

    public object Filters {
        public val lessThanFiveEuros: I18n
            get() = I18n.string("com_miam_filter_less_than_five_euros", miamStrings?.com_miam_filter_less_than_five_euros)

        public val betweenFiveAndTenEuros: I18n
            get() = I18n.string("com_miam_filter_between_five_and_ten_euros", miamStrings?.com_miam_filter_between_five_and_ten_euros)

        public val moreThanTenEuros: I18n
            get() = I18n.string("com_miam_filter_more_than_ten_euros", miamStrings?.com_miam_filter_more_than_ten_euros)

        public val lessThanFifteenMinutes: I18n
            get() = I18n.string("com_miam_filter_less_than_fifteen_minutes", miamStrings?.com_miam_filter_less_than_fifteen_minutes)

        public val lessThanThirteenMinutes: I18n
            get() = I18n.string("com_miam_filter_less_than_thirteen_minutes", miamStrings?.com_miam_filter_less_than_thirteen_minutes)

        public val lessThanAnHour: I18n
            get() = I18n.string("com_miam_filter_less_than_one_hour", miamStrings?.com_miam_filter_less_than_one_hour)

        public val lessThanTwoHours: I18n
            get() = I18n.string("com_miam_filter_less_than_two_hours", miamStrings?.com_miam_filter_less_than_two_hours)
    }

    public object Counter {
        public val persons: I18n
            get() = I18n.string("com_miam_counter_person", miamStrings?.com_miam_counter_person)
    }

    public object MyMeals {
        public val noMealIdeaInBasket: I18n
            get() = I18n.string("com_miam_my_meals_no_meal_in_basket", miamStrings?.com_miam_my_meals_no_meal_in_basket)

        public fun mealsAdded(numberOfMeals: Int): I18n {
            return I18n.plural("com_miam_my_meals_meal_added", miamPlurals?.com_miam_my_meals_meal_added, numberOfMeals, numberOfMeals)
        }
    }

    public object SponsorBanner {
        public val sponsorBannerSpeach: I18n
            get() = I18n.string("com_miam_sponsor_banner_speach", miamStrings?.com_miam_sponsor_banner_speach)

            public val sponsorBannerMoreInfo: I18n
            get() = I18n.string("com_miam_sponsor_banner_more_info", miamStrings?.com_miam_sponsor_banner_more_info)
    }
}