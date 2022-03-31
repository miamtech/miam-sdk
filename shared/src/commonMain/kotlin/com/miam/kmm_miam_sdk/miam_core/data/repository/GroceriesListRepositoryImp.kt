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

    override suspend fun getNew(): GroceriesList {
       return groceriesListDataSource.getNew()
    }

    override suspend fun updateGroceriesList(gl : GroceriesList): GroceriesList {
        return groceriesListDataSource.updateGroceriesList(gl)
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

