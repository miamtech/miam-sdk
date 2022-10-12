package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.primary

@Composable
fun RoundedCheckbox(check: Boolean, onCheckedChange: () -> Unit){

    Card(
        modifier = Modifier.background(Color.White),
        elevation = 0.dp,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.5.dp, color = primary)
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .background(if (check) primary else Color.White)
                .clickable {
                    onCheckedChange()
                },
            contentAlignment = Alignment.Center
        ) {
            if(check)
                Icon(Icons.Default.Check, contentDescription = "", tint = Color.White)
        }
    }
}
