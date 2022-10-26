package com.miam.kmm_miam_sdk.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    verticalSpacing: Dp = 0.dp,
    horizontalSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        data class FlowContent(
            val placeable: Placeable,
            val x: Int,
            val y: Int
        )

        var y = 0
        var x = 0
        var rowMaxY = 0
        val flowContents = mutableListOf<FlowContent>()

        val verticalSpacingPx = verticalSpacing.roundToPx()
        val horizontalSpacingPx = horizontalSpacing.roundToPx()

        placeables.forEach { placeable ->
            if (placeable.width + x > constraints.maxWidth) {
                x = 0
                y += rowMaxY
                rowMaxY = 0
            }
            rowMaxY = max(placeable.height + verticalSpacingPx, rowMaxY)

            flowContents.add(FlowContent(placeable, x, y))
            x += placeable.width + horizontalSpacingPx
        }
        y += rowMaxY

        layout(constraints.maxWidth, y) {
            flowContents.forEach {
                it.placeable.place(it.x, it.y)
            }
        }
    }
}