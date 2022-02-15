package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.*

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.graphics.Color

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
}