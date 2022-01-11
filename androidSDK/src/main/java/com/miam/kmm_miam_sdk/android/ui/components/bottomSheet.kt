package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.component.BottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.BottomSheet.BottomSheetContent


import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MiamBottomSheet(
    bottomSheetState: ModalBottomSheetState, scope: CoroutineScope
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

@Composable
private fun BottomSheetContent(){

    val vmBottomSheet = BottomSheetViewModel()
    val state by vmBottomSheet.uiState.collectAsState()

    when(state.content) {
        BottomSheetContent.RECIPE_DETAIL -> Box(){}
        BottomSheetContent.RECIPE_HELPER -> Box(){}
        BottomSheetContent.RECIPE_SPONSOR -> Box(){}
        BottomSheetContent.BASKET_PREVIEW -> Box(){}
        else -> Box(){}
    }
}
