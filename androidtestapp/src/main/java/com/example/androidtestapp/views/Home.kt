package com.example.androidtestapp.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androidtestapp.models.MyProductsRepository
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import org.koin.core.component.KoinComponent


class Home(private val context: Context): KoinComponent {
    
    @Composable
    fun Content() {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Recipes(context)
        }
    }

    fun randomCriteria(): SuggestionsCriteria {
        val randomProductId = mutableListOf<String>()
        for (i in 0..2) {
            val randomElement = MyProductsRepository.getRandomProduct()
            randomProductId.add(randomElement.id)
        }

        return SuggestionsCriteria(shelfIngredientsIds = randomProductId)
    }

    @Composable
    fun Recipes(context: Context) {
        val recipe1 = RecipeView(context)
        val recipe2 = RecipeView(context)
        val recipe3 = RecipeView(context)

        recipe1.bind(recipeId = "305")
        recipe2.bind(criteria = randomCriteria())
        // recette a base de poulet
        recipe3.bind(criteria = SuggestionsCriteria(currentIngredientsIds = listOf("5319173")))

        Column {
            recipe1.Content()
            recipe2.Content()
            recipe3.Content()
        }


    }
}