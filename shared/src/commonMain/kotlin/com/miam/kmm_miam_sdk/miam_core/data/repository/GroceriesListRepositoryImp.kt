package com.miam.kmm_miam_sdk.miam_core.data.repository



import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.Recipe


class GroceriesListRepositoryImp (
    private val groceriesListDataSource: MiamAPIDatasource
): GroceriesListRepository {

    override suspend fun getCurrent(): GroceriesList {
        val gl = groceriesListDataSource.getCurrent()
        return getMissingRecipes(gl)
    }

    override suspend fun reset(): GroceriesList {
       return groceriesListDataSource.reset()
    }

    override suspend fun updateGroceriesList(gl: GroceriesList): GroceriesList {
        val oldRecipes = gl.recipes
        val newGl = groceriesListDataSource.updateGroceriesList(gl)
        return getMissingRecipes(newGl, oldRecipes)
    }

    private suspend fun getMissingRecipes(gl: GroceriesList, existingRecipes: List<Recipe> = emptyList()): GroceriesList {
        val missingRecipes = groceriesListDataSource.getRecipeByIds(gl.missingRecipesIds(existingRecipes))
        gl.rebuildRecipesRelationships(missingRecipes, existingRecipes)
        return gl
    }

    override suspend fun removeRecipeFromList(): GroceriesList {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipeGuests(): GroceriesList {
        TODO("Not yet implemented")
    }

    override suspend fun removeAllRecipes(): GroceriesList {
        TODO("Not yet implemented")
    }

}

