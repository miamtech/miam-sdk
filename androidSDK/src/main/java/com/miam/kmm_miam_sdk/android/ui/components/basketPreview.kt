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
fun BasketPreview(goTo : (destination : BottomSheetContract.Event) -> Unit) {

    // TODO BasketPreview component  + map  with controler

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "BasketPreview")
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { goTo(BottomSheetContract.Event.GoToDetail) }) {
            Text(text = "GotToRecipeDetails")
        }
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { goTo(BottomSheetContract.Event.GoToHelper) }) {
            Text(text = "GotToRecipeHelper")
        }
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { goTo(BottomSheetContract.Event.GoToSponsor) }) {
            Text(text = "GotToRecipeSponsor")
        }

    }
}