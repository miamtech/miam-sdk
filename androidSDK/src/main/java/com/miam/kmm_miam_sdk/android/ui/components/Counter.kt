package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.R

data class CounterModifier(
    val rowModifier: Modifier? = null,
    val iconModifier :Modifier? = null,
    val lessButtonModifier :Modifier? = null,
    val countBorderModifier :Modifier? = null,
    val countTextModifier :Modifier?= null,
    val plusButtonModifier: Modifier? = null
)

@Composable
fun Counter(count: Int, increase : () -> Unit , decrease: () -> Unit, counterModifier: CounterModifier ) {
    Row(

        modifier = counterModifier.rowModifier ?:
        Modifier.padding(
            horizontal = 8.dp,
            vertical = 8.dp
        ), verticalAlignment = Alignment.CenterVertically,){
        Image(
            painter = painterResource(R.drawable.ic_peoples),
            contentDescription = null,
            modifier = counterModifier.iconModifier ?: Modifier.size(24.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ){
            IconButton(onClick = { decrease() },) {
                Image(
                    painter = painterResource(R.drawable.ic_less),
                    contentDescription = null,
                    modifier= counterModifier.lessButtonModifier ?: Modifier.size(16.dp)
                )
            }
            Row(
                modifier= counterModifier.countBorderModifier ?: Modifier
                    .height(32.dp)
                    .width(48.dp)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.Gray),
                        shape = RoundedCornerShape(4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = count.toString(),  modifier = counterModifier.countTextModifier ?: Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    ))
            }
            IconButton(onClick = { increase() }) {
                Image(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = counterModifier.plusButtonModifier ?: Modifier.size(16.dp)
                )
            }
        }
    }
}
