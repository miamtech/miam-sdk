package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.ui.components.common.Counter
import com.miam.kmm_miam_sdk.android.ui.components.common.CounterModifier
import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Ingredient
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

@Composable
fun RecipeIngredients(recipe: Recipe, vmRecipe: RecipeViewModel) {

    val state: RecipeContract.State = vmRecipe.uiState.collectAsState().value

    Column() {
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
            Counter(
                vmRecipe.currentState.guest,
                isDisable = false,
                { vmRecipe.setEvent(RecipeContract.Event.IncreaseGuest) },
                { vmRecipe.setEvent(RecipeContract.Event.DecreaseGuest) },
                CounterModifier(),
                )
            Text(
                text = "Quantité",
                color = MiamMasterView.darkGray
            )
        }
        if (recipe.attributes.ingredients != null) {
            val ingredients: List<Ingredient> = recipe.attributes.ingredients!!.ingredients
            ingredients.forEach {
                IngredientRow(
                    it.attributes.name!!.capitalize(),
                    ReadableFloatNumber(
                        realQuantities(
                            it.attributes.quantity!!,
                            state.guest,
                            recipe.attributes.numberOfGuests!!
                        ), it.attributes.unit!!
                    )
                )
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
            .padding(vertical = 7.dp, horizontal = 16.dp)
    ) {
        Text(
            text = ingredient,
            fontWeight = FontWeight.W700
        )
        Text(
            text = quantity,
            color = MiamMasterView.grayColor
        )
    }
}

fun realQuantities(quantity: String, currentGuest: Int, recipeGuest: Int): String {
    return quantity.toFloat().toBigDecimal().multiply(currentGuest.toBigDecimal())
        .divide(recipeGuest.toBigDecimal(), 2).toString()
}
