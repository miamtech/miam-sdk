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


class Price(recipeId :Int) {

    private var vmPrice : PricingViewModel = PricingViewModel()

    init {
        vmPrice.setEvent(
            PricingContract.Event.OnSetRecipe(
                idRecipe = recipeId
            )
        )
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


        Row() {
            Column() {
                Row() {
                    Text("${vmPrice.currentState.integerPart},", color = Color(0xff037E92), fontSize = 24.sp)
                    Text("${vmPrice.currentState.decimalPart}€", color = Color(0xff037E92), fontSize = 16.sp)
                }
                Text("par pers.", color = Color.Gray, fontSize = 16.sp)
            }
        }
    }
}