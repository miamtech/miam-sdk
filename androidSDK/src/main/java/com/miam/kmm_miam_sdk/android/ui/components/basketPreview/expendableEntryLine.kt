package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun expendableEntryLine(
    entries: List<BasketEntry>,
    title: String,
    backGroundColor: Color,
    fontColor: Color,
    click: ((bpl : BasketEntry) -> Unit)? = null
) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Column() {
        Card(
            backgroundColor = backGroundColor,
            onClick = { expandedState = !expandedState }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)

            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = fontColor
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        tint = fontColor,
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
        }

        if (expandedState) {
            Column {
                entries.forEach { entry ->
                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier.padding(8.dp).fillMaxWidth()

                   ) {
                       Text(text = entry.relationships?.groceriesEntry?.data?.attributes?.name ?: "")
                       if(click != null){
                           Button(
                               border = BorderStroke(1.dp, Color(0xffD9D9D9)),
                               shape = RoundedCornerShape(50),
                               colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                               onClick = { click(entry) }) {
                               Text(text = "Ajouter au panier")
                           }
                       }

                   }
                }
            }
        }

    }
}