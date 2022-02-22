package com.example.androidtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.miam.kmm_miam_sdk.android.di.KoinInitilizer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler
import com.miam.kmm_miam_sdk.handler.UserHandler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KoinInitilizer.init(this)
        PointOfSaleHandler.updateStoreId("35290")
        PointOfSaleHandler.setSupplier(7)
        UserHandler.updateUserId("ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        setContentView(R.layout.activity_main)
        val recipeView = findViewById(R.id.miam2) as RecipeView
        recipeView.bind(recipeId = 2)
        val recipeView2 = findViewById(R.id.miam3) as RecipeView
        recipeView2.bind(recipeId = 1)

    }
}