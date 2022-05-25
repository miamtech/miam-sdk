package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miam.kmm_miam_sdk.android.theme.Template

@Composable
fun BasketPreviewLoader(){
    if (Template.basketPreviewLoadingTemplate != null){
        (Template.basketPreviewLoadingTemplate!!)()
    } else {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            CircularProgressIndicator()
        }
    }
}

