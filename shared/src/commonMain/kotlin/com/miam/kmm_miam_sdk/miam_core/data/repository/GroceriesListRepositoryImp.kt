package com.miam.kmm_miam_sdk.miam_core.data.repository



import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesListWithoutRelationship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GroceriesListRepositoryImp (
    private val groceriesListDataSource: MiamAPIDatasource
): GroceriesListRepository {

    override suspend fun getCurrent(): GroceriesList {
       val gl = groceriesListDataSource.getCurrent()
       //val groceriesEntries = groceriesListDataSource.getGroceriesEntries(gl.id)
       val recipes = groceriesListDataSource.getRecipes(gl.attributes.recipesInfos ?: emptyList())
        gl.apply {
            //relationships.groceriesEntries = groceriesEntries.groceriesEntries
        relationships?.recipes = recipes}
       return gl
    }

    override suspend fun reset(): GroceriesList {
       return groceriesListDataSource.reset()
    }

    override suspend fun updateGroceriesList(gl : GroceriesListWithoutRelationship): GroceriesList {
        val newGl = groceriesListDataSource.updateGroceriesList(gl)
        val recipes = groceriesListDataSource.getRecipes(gl.attributes.recipesInfos ?: emptyList())
        newGl.apply {
            relationships?.recipes = recipes}
        return newGl
    }

    override suspend fun removeRecipeFromList(): Flow<GroceriesList> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipeGuests(): Flow<GroceriesList> {
        TODO("Not yet implemented")
    }

    override suspend fun removeAllRecipes(): Flow<GroceriesList> {
        TODO("Not yet implemented")
    }

}

