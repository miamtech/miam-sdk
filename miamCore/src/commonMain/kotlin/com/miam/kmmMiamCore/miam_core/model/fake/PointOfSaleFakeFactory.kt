package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.PointOfSale
import com.miam.kmmMiamCore.miam_core.model.PointOfSaleAttributes

public object PointOfSaleFakeFactory {
    public const val FAKE_ID: Int = -1
    public const val FAKE_NAME: String = "POSName"

    public fun create(
        id: Int = FAKE_ID,
        attributes: PointOfSaleAttributes = createAttributes(),
    ): PointOfSale = PointOfSale(
        id = id,
        attributes = attributes
    )

    public fun createAttributes(
        name: String = FAKE_NAME,
    ): PointOfSaleAttributes = PointOfSaleAttributes(
        name = name,
        // TODO Romain: Any useful parameter
    )
}