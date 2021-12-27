package com.example.kmm_miam_sdk.android


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi


import com.example.kmm_miam_sdk.android.di.viewModelModule
import com.example.kmm_miam_sdk.android.ui.components.RecipeCardView
import com.example.kmm_miam_sdk.component.recipeCard.RecipeCardContract
import com.example.kmm_miam_sdk.component.recipeCard.RecipeCardViewModel
import com.example.kmm_miam_sdk.di.initKoin

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.java.KoinJavaComponent.get


@ExperimentalCoilApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainActivity)
            modules(
                viewModelModule
            )
        }

        val vmRecipeCard = RecipeCardViewModel()
        val vmRecipeCard2 = RecipeCardViewModel()
        vmRecipeCard.setEvent(
            RecipeCardContract.Event.OnGetRecipe(
                idRecipe = 1637
            )
        )

        vmRecipeCard2.setEvent(
            RecipeCardContract.Event.OnGetRecipe(
                idRecipe = 1639
            )
        )
        setContentView(R.layout.activity_main)
        val greeting = findViewById<ComposeView>(R.id.greeting)
        greeting.setContent {
           Column() {
               RecipeCardView(vmRecipeCard)
               RecipeCardView(vmRecipeCard2)
           }

        }

    }

}