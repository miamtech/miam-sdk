package com.miam.kmm_miam_sdk.android.ui.components.counter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.CircularProgressIndicator
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
import com.miam.core.sdk.localisation.Localisation.Counter.persons
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
    initialCount: Int?,
    isDisable: Boolean,
    onCounterChanged: (newValue: Int) -> Unit,
    lightMode: Boolean = false,
    minValue: Int? = null,
    maxValue: Int? = null,
    isLoading: Boolean = false,
    key: Any? = null
) {
    var localCount by remember(key ?: initialCount) { mutableStateOf(initialCount) }

    fun newValueBounded(newValue: Int): Boolean {
        return (minValue == null || newValue >= minValue) && (maxValue == null || newValue <= maxValue)
    }

    fun changedValue(localCount: Int?, delta: Int): Int? {
        // if min value is not defined 1 is surely bounded
        if (localCount == null) return minValue ?: 1

        if (!newValueBounded(localCount + delta)) return null

        return localCount + delta
    }

    fun increase() {
        changedValue(localCount, 1)?.let { newCount ->
            localCount = newCount
            onCounterChanged(newCount)
        }
    }

    fun decrease() {
        changedValue(localCount, -1)?.let { newCount ->
            localCount = newCount
            onCounterChanged(newCount)
        }
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
            Plus({ decrease() }, isDisable)
            MiddleText(localCount, lightMode, isLoading)
            Minus({ increase() }, isDisable)
        }
    }
}

@Composable
fun Plus(decrease: () -> Unit, isDisable: Boolean) {
    IconButton(
        onClick = { decrease() },
        enabled = !isDisable,
        modifier = lessButton.background(if (isDisable) lessButtonBackgroundDisableColor else lessButtonBackgroundColor)
    ) {
        Image(
            painter = painterResource(less),
            contentDescription = "less icon",
            colorFilter = ColorFilter.tint(lessIconColor),
            modifier = lessButtonIcon
        )
    }
}

@Composable
fun MiddleText(localCount: Int?, lightMode: Boolean, isLoading: Boolean) {
    fun counterText(countValue: Int?, lightMode: Boolean): String {
        if (countValue == null) return "-"
        return localCount.toString() + if (lightMode) "" else " " + persons.localised
    }

    Row(
        modifier = countBorder,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = countTextColor)
        } else {
            Text(
                text = counterText(localCount, lightMode),
                color = countTextColor,
                modifier = countText
            )
        }
    }
}

@Composable
fun Minus(increase: () -> Unit, isDisable: Boolean) {
    IconButton(
        onClick = { increase() },
        enabled = !isDisable,
        modifier = plusButton.background(if (isDisable) plusButtonBackgroundDisableColor else plusButtonBackgroundColor)
    ) {
        Image(
            painter = painterResource(plus),
            contentDescription = null,
            colorFilter = ColorFilter.tint(plusIconColor),
            modifier = plusButtonIcon
        )
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