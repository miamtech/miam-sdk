package com.miam.kmm_miam_sdk.android.ui.components.Counter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterColor.lessButtonBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterColor.lessButtonBackgroundDisableColor
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterColor.lessIconColor
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterImage.guests
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterImage.less
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterStyle.guestIcon
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterStyle.iconModifier
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterStyle.lessButton
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterStyle.lessButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterStyle.mainRowContainer
import com.miam.kmm_miam_sdk.android.ui.components.Counter.CounterStyle.rowModifier

data class CounterModifier(
    val rowModifier: Modifier? = null,
    val iconModifier: Modifier? = null,
    val lessButtonModifier: Modifier? = null,
    val countBorderModifier: Modifier? = null,
    val countTextModifier: Modifier? = null,
    val plusButtonModifier: Modifier? = null,
    val buttonColors: Color = Color.Black
)

@Composable
fun Counter(
    count: Int,
    isDisable: Boolean,
    increase: () -> Unit,
    decrease: () -> Unit,
    counterModifier: CounterModifier
) {
    Row(
        modifier = mainRowContainer,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(guests),
            contentDescription = "guests icon",
            modifier = guestIcon
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            ) {
            IconButton(
                    onClick = { decrease() },
                    enabled = !isDisable,
                    modifier = lessButton.background(
                        if(isDisable) lessButtonBackgroundDisableColor else lessButtonBackgroundColor
                    )
            ) {
                Image(
                    painter = painterResource(less),
                    contentDescription = "less icon",
                    colorFilter = ColorFilter.tint(lessIconColor),
                    modifier = lessButtonIcon
                )
            }
            Row(
                modifier = counterModifier.countBorderModifier ?: Modifier
                    .height(32.dp)
                    .width(48.dp)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.Gray),
                        shape = RoundedCornerShape(4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = count.toString(),
                    modifier = counterModifier.countTextModifier ?: Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                )
            }
            IconButton(
                modifier = Modifier.background( if(isDisable) Color(0xffF6F6F6) else Color.Transparent),
                onClick = { increase() },
                enabled = !isDisable,) {
                Image(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(counterModifier.buttonColors),
                    modifier = counterModifier.plusButtonModifier ?: Modifier.size(16.dp)
                )
            }
        }
    }
}
