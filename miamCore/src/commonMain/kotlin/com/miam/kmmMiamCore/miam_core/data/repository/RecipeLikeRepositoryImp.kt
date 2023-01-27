package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.RecipeLike

public class RecipeLikeRepositoryImp(private val recipeDataSource: MiamAPIDatasource) {
    public suspend fun getRecipeLikes(recipeIds: List<String>): List<RecipeLike> {
        return recipeDataSource.getRecipeLikes(recipeIds)
    }

    public suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike {
        return recipeDataSource.createRecipeLike(recipeLike)
    }

    public suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike {
        return recipeDataSource.updateRecipeLike(recipeLike)
    }
}