package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.quantityFormatter.QuantityFormatter
import com.miam.kmmMiamCore.component.recipe.RecipeContract
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.Ingredient
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.black
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import java.util.*

@Composable
fun RecipeIngredients(recipe: Recipe, vmRecipe: RecipeViewModel) {

    val state: RecipeContract.State = vmRecipe.uiState.collectAsState().value

    Column {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = "${recipe.relationships?.ingredients?.data?.size} ingrédients",
                style = Typography.subtitleBold,
                color = black
            )
            Counter(
                vmRecipe.currentState.guest,
                isDisable = false,
                { vmRecipe.increaseGuest() },
                { vmRecipe.decreaseGuest() },
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Colors.backgroundGrey)
        ) {
            if (recipe.relationships?.ingredients?.data != null) {
                val ingredients: List<Ingredient> = recipe.relationships!!.ingredients!!.data
                ingredients.forEach {
                    IngredientRow(
                        it.attributes!!.name!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        QuantityFormatter.readableFloatNumber(
                            QuantityFormatter.realQuantities(
                                it.attributes!!.quantity!!,
                                state.guest,
                                recipe.attributes!!.numberOfGuests
                            ), it.attributes!!.unit!!
                        )
                    )
                }
            }
        }

    }
}

@Composable
fun IngredientRow(ingredient: String, quantity: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = ingredient,
            style = body
        )
        Text(
            text = quantity,
            style = bodyBold
        )
    }
}


