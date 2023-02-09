package com.miam.core.sdk.localisation

import com.miam.core.localisation.i18n.I18n
import com.miam.sdk.resources.MiamSdkResources

private val miamStrings = runCatching { MiamSdkResources.strings }.getOrNull()

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
    }

    public object ItemSelector {
        public val replaceProduct: I18n
            get() = I18n.string("com_miam_item_selector_replace_by", miamStrings?.com_miam_item_selector_replace_by)
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

        public val swapProduct: I18n
            get() = I18n.string("com_miam_basket_swap_product", miamStrings?.com_miam_basket_swap_product)
    }

    public object Favorites {
        public val noFavorites: I18n
            get() = I18n.string("com_miam_favorites_no_favorites", miamStrings?.com_miam_favorites_no_favorites)

        public val goToCatalog: I18n
            get() = I18n.string("com_miam_favorites_go_to_catalog", miamStrings?.com_miam_favorites_go_to_catalog)
    }

    public object Catalog {
        public val showAll: I18n
            get() = I18n.string("com_miam_catalog_show_all", miamStrings?.com_miam_catalog_show_all)

        public val loadingText: I18n
            get() = I18n.string("com_miam_catalog_loading_text", miamStrings?.com_miam_catalog_loading_text)

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

        public val showResults: I18n
            get() = I18n.string("com_miam_catalog_filter_show_results", miamStrings?.com_miam_catalog_filter_show_results)
    }

    public object Preferences {
        public val title: I18n
            get() = I18n.string("com_miam_preferences_title", miamStrings?.com_miam_preferences_title)

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
}