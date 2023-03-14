package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MiamContext internal constructor(
    @SerialName("supplier_id")
    val SupplierID: String,
    @SerialName("plausible_domaine")
    val plausibleDomain: String,
    val host:String,
    @SerialName("miam_origin")
    val origin: String,
    @SerialName("miam_environment")
    val environment:String,
)