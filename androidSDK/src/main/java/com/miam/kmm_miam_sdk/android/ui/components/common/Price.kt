package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator

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
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.pricing.PricingContract

import com.miam.kmm_miam_sdk.component.pricing.PricingViewModel

import com.miam.kmm_miam_sdk.miam_core.model.Pricing


class Price(
            val recipeId :Int? = -1,
            val price: Double? = null,
            val isTotalPrice: Boolean = false,
            val color: Color? = null ,
            val fontSize: Int? = null
    ) {

    private var vmPrice : PricingViewModel = PricingViewModel()

    init {
        if(recipeId != -1 ){
            vmPrice.setEvent(
                PricingContract.Event.OnSetRecipe(recipeId!!)
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
            Box( ){
                ManagementResourceState(
                    resourceState = state.price,
                    successView = { price ->
                        requireNotNull(price)
                        priceView(price, vmPrice)
                    },
                    onTryAgain = { /*TODO*/ },
                    onCheckAgain = { /*TODO*/ },
                    loadingView = {   PriceShimmer()  }
                )
            }
        }

    @Composable
    fun priceView(price: Pricing, vmPrice: PricingViewModel) {
            Column() {
                Row() {
                    Text("${vmPrice.currentState.integerPart},",
                        color = color ?: Color(0xff037E92),
                        fontSize = fontSize?.sp ?: 22.sp
                    )
                    Text("${vmPrice.currentState.decimalPart}€",
                        color =  color ?: Color(0xff037E92),
                        fontSize =  fontSize?.minus(6)?.sp ?: 16.sp)
                }
                if(!isTotalPrice){
                    Text("par pers.", color = Color.Gray, fontSize = 14.sp)
                }

            }
    }


    @Composable
    fun PriceShimmer(){

        val shimerColors = listOf(
            Color.LightGray.copy(alpha = 0.6F),
            Color.LightGray.copy(alpha = 0.2F),
            Color.LightGray.copy(alpha = 0.6F)
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

                Spacer(modifier = Modifier
                    .height(fontSize?.dp ?: 22.dp)
                    .width(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush = brush))
                Spacer(modifier = Modifier
                    .height(fontSize?.minus(6)?.dp ?: 16.dp)
                    .width(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush = brush))
            }
            if(!isTotalPrice){
                Text("par pers.", color = Color.Gray, fontSize = 14.sp)
            }

        }
    }
}




