package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.Flow

interface RecipeSuggestionsRepository {

    suspend fun getRecipeSuggestions(customerId: Int, criteria: SuggestionsCriteria): Flow<Recipe>

}