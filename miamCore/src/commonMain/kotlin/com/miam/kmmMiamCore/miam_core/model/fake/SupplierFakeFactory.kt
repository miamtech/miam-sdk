package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Supplier
import com.miam.kmmMiamCore.miam_core.model.SupplierAttributes

public object SupplierFakeFactory {

    public const val FAKE_ID: String = "-1"

    public fun create(
        id: String = FAKE_ID,
        attributes: SupplierAttributes? = createAttributes(),
    ): Supplier = Supplier(
        id = id,
        attributes = attributes,
    )

    public fun createAttributes(): SupplierAttributes = SupplierAttributes(
        name = "test",
        languageId = "fr"
    )
}