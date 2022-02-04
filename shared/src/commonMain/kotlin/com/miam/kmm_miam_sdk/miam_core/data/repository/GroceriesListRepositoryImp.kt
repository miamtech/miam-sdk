package com.miam.kmm_miam_sdk.miam_core.data.repository



import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesListWithoutRelationship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GroceriesListRepositoryImp (
    private val groceriesListDataSource: MiamAPIDatasource
): GroceriesListRepository {

    override suspend fun getCurrent(): Flow<GroceriesList> = flow {
       val gl = groceriesListDataSource.getCurrent()
       //val groceriesEntries = groceriesListDataSource.getGroceriesEntries(gl.id)
       val recipes = groceriesListDataSource.getRecipes(gl.attributes?.recipesInfos ?: emptyList())
        gl.apply {
            //relationships.groceriesEntries = groceriesEntries.groceriesEntries
        relationships?.recipes = recipes}
       emit(gl)
    }

    override suspend fun getNew(): Flow<GroceriesList> = flow {
       val gl = groceriesListDataSource.getNew()
       emit(gl)
    }

    override suspend fun updateGroceriesList(gl : GroceriesListWithoutRelationship): Flow<GroceriesList> = flow {
        val gl = groceriesListDataSource.updateGroceriesList(gl)
        emit(gl)
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

