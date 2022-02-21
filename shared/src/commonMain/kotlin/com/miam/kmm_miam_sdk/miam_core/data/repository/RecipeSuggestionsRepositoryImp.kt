package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeSuggestionsRepositoryImp(private val recipeDataSource: MiamAPIDatasource) :
    RecipeSuggestionsRepository {

    override fun getRecipeSuggestions(
        customerId: Int,
        criteria: SuggestionsCriteria
    ): Flow<Recipe> = flow {
        val recipe = recipeDataSource.getRecipeSuggestions(customerId, criteria)
        emit(recipe)
    }
}