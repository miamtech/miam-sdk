package com.miam.kmm_miam_sdk.android.ui.components.price

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.miam.kmmMiamCore.component.pricing.PricingContract
import com.miam.kmmMiamCore.component.pricing.PricingViewModel
import com.miam.kmm_miam_sdk.android.theme.Typography.bodySmall
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.loaderColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.priceIntegerColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.priceIntegerTotalPriceColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.subtitleColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.loaderInteger
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.mainContainer
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.priceContainer
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.priceEmptyState
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceText.currency
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceText.preGuests
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

@Composable
fun Price(
    recipeId: String? = "",
    guestNumber: Int = -1,
    price: Double? = null,
    isTotalPrice: Boolean = false,
) {
    val vmPrice =
        PricingViewModel()

    if (recipeId != "" && guestNumber != -1) {
        vmPrice.setEvent(
            PricingContract.Event.OnSetRecipe(recipeId!!, guestNumber)
        )
    } else if (price != null) {
        vmPrice.setEvent(
            PricingContract.Event.SetDirectPrice(price)
        )
    }

    PriceStateManager(vmPrice, isTotalPrice)

}

@Composable
fun PriceStateManager(vmPrice: PricingViewModel, isTotalPrice: Boolean) {
    val state by vmPrice.uiState.collectAsState()
    
    Box {
        ManagementResourceState(
            resourceState = state.price,
            successView = { price ->
                requireNotNull(price)
                PriceView(
                    price.price,
                    isTotalPrice
                )
            },
            emptyView = { EmptyState() },
            onTryAgain = { /*TODO*/ },
            onCheckAgain = { /*TODO*/ },
            loadingView = { PriceShimmer(isTotalPrice) }
        )
    }
}

@Composable
fun EmptyState() {
    Spacer(modifier = priceEmptyState)
}

@Composable
fun PriceView(price: Double, isTotalPrice: Boolean) {
    Column(
        modifier = mainContainer,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = priceContainer) {
            Text(
                "$price$currency",
                color = if (isTotalPrice) priceIntegerTotalPriceColor else priceIntegerColor,
                style = subtitleBold
            )
        }
        if (!isTotalPrice) {
            Text(
                preGuests,
                color = subtitleColor,
                style = bodySmall
            )
        }
    }
}


@Composable
fun PriceShimmer(isTotalPrice: Boolean) {

    val shimerColors = listOf(
        loaderColor.copy(alpha = 0.6F),
        loaderColor.copy(alpha = 0.2F),
        loaderColor.copy(alpha = 0.6F)
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimerColors,
        start = Offset.Zero,
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    shimmerPriceItem(brush, isTotalPrice)
}

@Composable
fun shimmerPriceItem(brush: Brush, isTotalPrice: Boolean) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = loaderInteger.background(brush = brush))
        }
        if (!isTotalPrice) {
            Text(
                preGuests,
                color = subtitleColor,
                style = bodySmall
            )
        }

    }
}


@Preview
@Composable
fun PricePreview() {
    PriceView(10.0, false)
}

@Preview
@Composable
fun TotalPricePreview() {
    PriceView(10.0, true)
}

@Preview
@Composable
fun PriceLoadingPreview() {
    PriceShimmer(false)
}



