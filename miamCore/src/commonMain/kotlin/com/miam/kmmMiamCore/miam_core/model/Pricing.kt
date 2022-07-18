package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.Serializable

@Serializable
data class Pricing(
    val price: Double,
    val serves: Int,
) {
    val pricePerServe: Double
        get() {
            return price / serves
        }
}