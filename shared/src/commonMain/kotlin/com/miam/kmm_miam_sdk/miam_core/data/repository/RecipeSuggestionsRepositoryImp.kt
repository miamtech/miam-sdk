package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeSuggestionsRepositoryImp(private val recipeDataSource: MiamAPIDatasource) :
    RecipeSuggestionsRepository {

    override suspend fun getRecipeSuggestions(
        customerId: Int,
        criteria: SuggestionsCriteria
    ) = flow {
        val recipes = recipeDataSource.getRecipeSuggestions(customerId, criteria)
        val recipe = recipes.get(0)
        emit(recipe)

    }
}
