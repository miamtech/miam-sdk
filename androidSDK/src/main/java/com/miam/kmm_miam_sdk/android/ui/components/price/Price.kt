package com.miam.kmm_miam_sdk.android.ui.components.price

import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.miam.kmmMiamCore.component.pricing.RecipePricingViewModel
import com.miam.kmm_miam_sdk.android.templatesParameters.PriceParameters
import com.miam.kmm_miam_sdk.android.theme.Template
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

fun formatPrice(price: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.currency = Currency.getInstance("EUR")

    return numberFormat.format(price)
}


@Composable
fun SimplePrice(price: Double) {
    if (Template.simplePriceTemplate != null) {
        Template.simplePriceTemplate?.let { it(PriceParameters(price)) }
    } else {
        Column(
            modifier = mainContainer,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = priceContainer) {
                Text(
                    "${formatPrice(price)}",
                    color = priceIntegerTotalPriceColor,
                    style = subtitleBold
                )
            }
        }
    }
}

@Composable
fun RecipePrice(
    recipeId: String,
    guestNumber: Int,
) {
    val vmPrice = RecipePricingViewModel()
    vmPrice.setRecipe(recipeId, guestNumber)
    PriceStateManager(vmPrice)

    LaunchedEffect(Unit) { vmPrice.listenBasketChanges() }
    DisposableEffect(Unit) { onDispose { vmPrice.stopListenBasketChanges() } }
}

@Composable
fun PriceStateManager(vmPrice: RecipePricingViewModel) {
    val state by vmPrice.uiState.collectAsState()
    Box {
        ManagementResourceState(
            resourceState = state.price,
            successView = { price ->
                requireNotNull(price)
                RecipePriceView(price.pricePerServe)
            },
            emptyView = { EmptyState() },
            loadingView = { PriceShimmer() }
        )
    }
}

@Composable
fun EmptyState() {
    if (Template.priceEmptyTemplate != null) {
        Template.priceEmptyTemplate?.let { it() }
    } else {
        Spacer(modifier = priceEmptyState)
    }
}

@Composable
fun RecipePriceView(price: Double) {

    if (Template.recipePriceTemplate != null) {
        Template.recipePriceTemplate?.let { it(PriceParameters(price)) }
    } else {
        Column(
            modifier = mainContainer,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = priceContainer) {
                Text(
                    "${formatPrice(price)}",
                    color = priceIntegerColor,
                    style = subtitleBold
                )
            }
            Text(
                preGuests,
                color = subtitleColor,
                style = bodySmall
            )
        }
    }
}


@Composable
fun PriceShimmer() {
    if (Template.priceLoadingTemplate != null) {
        Template.priceLoadingTemplate?.let { it() }
    } else {
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
        shimmerPriceItem(brush)
    }
}

@Composable
fun shimmerPriceItem(brush: Brush) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = loaderInteger.background(brush = brush))
        }
        Text(
            preGuests,
            color = subtitleColor,
            style = bodySmall
        )
    }
}


@Preview
@Composable
fun PricePreview() {
    RecipePriceView(10.0)
}

@Preview
@Composable
fun PriceLoadingPreview() {
    PriceShimmer()
}



