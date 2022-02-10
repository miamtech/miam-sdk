package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContract

@Composable
fun RecipeHelper(goTo : (destination : BottomSheetContract.Event) -> Unit) {
    // TODO  RecipeHelper component  + map  with controler
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "RecipeHelper")
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { goTo(BottomSheetContract.Event.GoToHelper) }) {
            Text(text = "GotToRecipeHelper")
        }
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
       /* Button(onClick = { goTo(BottomSheetContract.Event.GoToPreview) }) {
            Text(text = "GotToRecipePreview")
        }*/
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { goTo(BottomSheetContract.Event.GoToSponsor) }) {
            Text(text = "GotToRecipeSponsor")
        }

    }
}