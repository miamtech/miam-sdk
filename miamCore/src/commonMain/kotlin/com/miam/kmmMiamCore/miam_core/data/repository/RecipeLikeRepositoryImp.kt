package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.RecipeLike

class RecipeLikeRepositoryImp(private val recipeDataSource: MiamAPIDatasource) {
    suspend fun getRecipeLikes(recipeIds: List<String>): List<RecipeLike> {
        return recipeDataSource.getRecipeLikes(recipeIds)
    }

    suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike {
        return recipeDataSource.createRecipeLike(recipeLike)
    }

    suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike {
        return recipeDataSource.updateRecipeLike(recipeLike)
    }
}