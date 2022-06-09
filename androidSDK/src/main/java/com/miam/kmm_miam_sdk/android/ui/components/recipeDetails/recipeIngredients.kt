package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.info
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.secondary
import com.miam.kmm_miam_sdk.android.theme.Colors.ternary
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Ingredient
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

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
                text =  "${recipe.relationships?.ingredients?.data?.size} ingrédients",
                style = Typography.subtitleBold,
                color = primary
            )
            Counter(
                vmRecipe.currentState.guest,
                isDisable = false,
                { vmRecipe.setEvent(RecipeContract.Event.IncreaseGuest) },
                { vmRecipe.setEvent(RecipeContract.Event.DecreaseGuest) },
                )
        }
        Divider(Modifier.padding(8.dp))
        Column(
            modifier =  Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ternary.copy(alpha = 0.1f))
        ) {
            if (recipe.relationships?.ingredients?.data != null) {
                val ingredients: List<Ingredient> = recipe.relationships!!.ingredients!!.data
                ingredients.forEach {
                    IngredientRow(
                        it.attributes!!.name!!.capitalize(),
                        vmRecipe.readableFloatNumber(
                            vmRecipe.realQuantities(
                                it.attributes!!.quantity!!,
                                state.guest,
                                recipe.attributes!!.numberOfGuests!!
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


