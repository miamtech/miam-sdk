package com.miam.kmm_miam_sdk.android.ui.components.price

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.theme.Typography.bodySmall
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitle
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.mainContainer
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.priceContainer
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.priceEmptyState
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.loaderColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.priceDecimalColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.priceIntegerColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceColor.subtitleColor
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.loaderDecimal
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceStyle.loaderInteger
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceText.currency
import com.miam.kmm_miam_sdk.android.ui.components.price.PriceText.preGuests
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.pricing.PricingContract

import com.miam.kmm_miam_sdk.component.pricing.PricingViewModel

import com.miam.kmm_miam_sdk.miam_core.model.Pricing


class Price(
            val recipeId :Int? = -1,
            val guestNumber:Int? = -1,
            val price: Double? = null,
            val isTotalPrice: Boolean = false,
            val color: Color? = null ,
            val fontSize: Int? = null
    ) {

    private var vmPrice : PricingViewModel = PricingViewModel()

    init {
        if(recipeId != -1 && guestNumber != -1 ){
            vmPrice.setEvent(
                PricingContract.Event.OnSetRecipe(recipeId!!, guestNumber!!)
            )
        } else if(price != null) {
            vmPrice.setEvent(
                PricingContract.Event.SetDirectPrice(price)
            )
        }

        vmPrice.setEvent(
            PricingContract.Event.OnPriceUpdate
            )
    }

        @Composable
        fun content(){
            val state by vmPrice.uiState.collectAsState()
            Box(){
                ManagementResourceState(
                    resourceState = state.price,
                    successView = { price ->
                        requireNotNull(price)
                        priceView(price, vmPrice)
                    },
                    emptyView = { emptyState() },
                    onTryAgain = { /*TODO*/ },
                    onCheckAgain = { /*TODO*/ },
                    loadingView = { PriceShimmer() }
                )
            }
        }


    @Composable
    fun emptyState(){
        Spacer(modifier = priceEmptyState)
    }

    @Composable
    fun priceView(price: Pricing, vmPrice: PricingViewModel) {
            Column( modifier= mainContainer ) {
                Row( modifier= priceContainer ) {
                    Text("${vmPrice.currentState.integerPart},",
                        color = priceIntegerColor,
                        style = subtitle
                    )
                    Text(
                        "${vmPrice.currentState.decimalPart}$currency",
                        color = priceDecimalColor,
                        style = bodySmall
                    )
                }
                if(!isTotalPrice){
                    Text(
                        preGuests,
                        color = subtitleColor,
                        style = bodySmall
                    )
                }
            }
    }


    @Composable
    fun PriceShimmer(){

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
            end= Offset(
                x= translateAnimation.value,
                y=translateAnimation.value
            )
        )

        shimmerPriceItem(brush)
    }

    @Composable
    fun shimmerPriceItem(brush: Brush){

        Column() {
            Row() {
                Spacer(modifier = loaderInteger.background(brush = brush))
                Spacer(modifier = loaderDecimal.background(brush = brush))
            }
            if(!isTotalPrice){
                Text(
                    preGuests,
                    color = subtitleColor,
                    style = bodySmall
                )
            }

        }
    }
}




