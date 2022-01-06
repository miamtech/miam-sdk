package com.miam.kmm_miam_sdk.network.model.Converter

import com.miam.kmm_miam_sdk.network.model.DAO.IngredientDAO
import com.miam.kmm_miam_sdk.network.model.Ingredient

class IngredientConverter
{

    companion object {

         fun DAOstoDTOs(ingredientsDAO: List<IngredientDAO>) : List<Ingredient> {
             println("miam "+ ingredientsDAO)
            return ingredientsDAO.map { value ->  Ingredient( value.id, value.attributes) }
         }

    }
}