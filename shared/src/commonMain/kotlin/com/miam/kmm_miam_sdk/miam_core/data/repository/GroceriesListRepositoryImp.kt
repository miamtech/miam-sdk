package com.miam.kmm_miam_sdk.miam_core.data.repository



import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GroceriesListRepositoryImp (
    private val groceriesListDataSource: MiamAPIDatasource
): GroceriesListRepository {

    override suspend fun getCurrent(): Flow<GroceriesList> = flow {
       val gl = groceriesListDataSource.getCurrent()
       //val groceriesEntries = groceriesListDataSource.getGroceriesEntries(gl.id)
//        gl.apply { attributes.groceriesEntries = groceriesEntries }

       emit(gl)
    }

    override suspend fun getNew(): Flow<GroceriesList> {
        TODO("Not yet implemented")
    }

    override suspend fun appendRecipeToList(): Flow<GroceriesList> {
        TODO("Not yet implemented")
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

