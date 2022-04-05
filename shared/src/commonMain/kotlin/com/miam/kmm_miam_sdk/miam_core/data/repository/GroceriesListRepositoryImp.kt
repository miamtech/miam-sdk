package com.miam.kmm_miam_sdk.miam_core.data.repository



import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList


class GroceriesListRepositoryImp (
    private val groceriesListDataSource: MiamAPIDatasource
): GroceriesListRepository {

    override suspend fun getCurrent(): GroceriesList {
       val gl = groceriesListDataSource.getCurrent()
       val recipes = groceriesListDataSource.getRecipes(gl.attributes?.recipesInfos ?: emptyList())
        gl.recipes = recipes
        return gl
    }

    override suspend fun reset(): GroceriesList {
       return groceriesListDataSource.reset()
    }

    override suspend fun updateGroceriesList(gl: GroceriesList): GroceriesList {
        val newGl = groceriesListDataSource.updateGroceriesList(gl)
        newGl.recipes = groceriesListDataSource.getRecipes(gl.attributes!!.recipesInfos)
        return newGl
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

