package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Supplier
import com.miam.kmmMiamCore.miam_core.model.SupplierAttributes

public object SupplierFakeFactory {

    public const val FAKE_ID: String = "-1"
    public const val FAKE_NAME: String = "supplierName"

    public fun create(
        id: String = FAKE_ID,
        attributes: SupplierAttributes? = createAttributes(),
    ): Supplier = Supplier(
        id = id,
        attributes = attributes,
    )

    public fun createAttributes(
        name: String = FAKE_NAME,
    ): SupplierAttributes = SupplierAttributes(
        name = name,
        languageId = "fr"

        // TODO Romain: Any useful parameter
    )
}