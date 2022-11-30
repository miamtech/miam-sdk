package com.miam.kmm_miam_sdk.android.ui.components.counter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.countTextColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.lessButtonBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.lessButtonBackgroundDisableColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.lessIconColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.plusButtonBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.plusButtonBackgroundDisableColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterColor.plusIconColor
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterImage.guests
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterImage.less
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterImage.plus
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.countBorder
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.countText
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.guestIcon
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.guestIconLight
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.lessButton
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.lessButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.mainRowContainer
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.plusButton
import com.miam.kmm_miam_sdk.android.ui.components.counter.CounterStyle.plusButtonIcon


@Composable
fun Counter(
    initialCount: Int,
    isDisable: Boolean,
    onCounterChanged: (newValue: Int) -> Unit,
    lightMode: Boolean = false,
    minValue: Int? = null,
    maxValue: Int? = null
) {
    var localCount by remember(initialCount) { mutableStateOf(initialCount) }

    fun newValueBounded(newValue: Int): Boolean {
        return (minValue == null || newValue >= minValue) && (maxValue == null || newValue <= maxValue)
    }

    fun increase() {
        if (!newValueBounded(localCount + 1)) return

        onCounterChanged(++localCount)
    }

    fun decrease() {
        if (!newValueBounded(localCount - 1)) return

        onCounterChanged(--localCount)
    }

    Row(
        modifier = mainRowContainer,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(guests),
            contentDescription = "guests icon",
            modifier = if (lightMode) guestIconLight else guestIcon
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { decrease() },
                enabled = !isDisable,
                modifier = lessButton.background(
                    if (isDisable) lessButtonBackgroundDisableColor else lessButtonBackgroundColor
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
                modifier = countBorder,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = localCount.toString() + if (lightMode) "" else " pers.",
                    color = countTextColor,
                    modifier = countText
                )
            }
            IconButton(
                modifier = plusButton.background(
                    if (isDisable) plusButtonBackgroundDisableColor else plusButtonBackgroundColor
                ),
                onClick = { increase() },
                enabled = !isDisable
            ) {
                Image(
                    painter = painterResource(plus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(plusIconColor),
                    modifier = plusButtonIcon
                )
            }
        }
    }
}


@Preview
@Composable
fun CounterPreview() {
    Counter(12, false, {}, false)
}

@Preview
@Composable
fun lightCounterPreview() {
    Counter(12, false, {}, true)
}