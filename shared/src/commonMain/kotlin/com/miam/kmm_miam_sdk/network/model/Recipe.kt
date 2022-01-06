package com.miam.kmm_miam_sdk.network.model


import com.miam.kmm_miam_sdk.network.model.DAO.RecipeAttributes

class Recipe (val attributes: RecipeAttributes) : MiamObject {

     var ingredients : List<Ingredient> = emptyList()

    companion object {
         var type  = "recipes"
         var relationship = arrayOf("ingredients")
    }

}

