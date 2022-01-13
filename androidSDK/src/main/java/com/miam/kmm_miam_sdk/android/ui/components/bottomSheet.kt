package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContent
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContract
import org.koin.java.KoinJavaComponent.get


@coil.annotation.ExperimentalCoilApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    bottomSheetState: ModalBottomSheetState,
) {
    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheetContent()
        }
    ){}
}

@ExperimentalCoilApi
@Composable
private fun BottomSheetContent(){

    val vmBottomSheet: BottomSheetViewModel = get(BottomSheetViewModel::class.java)
    val state by vmBottomSheet.uiState.collectAsState()

    val goTo = { destination: BottomSheetContract.Event  ->  vmBottomSheet.setEvent(destination) }

    Box(modifier = Modifier.defaultMinSize(minHeight = 1.dp)) {
        when(state.content) {
            BottomSheetContent.RECIPE_DETAIL -> RecipeDetail(goTo)
            BottomSheetContent.RECIPE_HELPER -> RecipeHelper(goTo)
            BottomSheetContent.RECIPE_SPONSOR -> RecipeSponsor(goTo)
            BottomSheetContent.BASKET_PREVIEW -> BasketPreview(goTo)
            else -> Box(){}
        }
    }

}
