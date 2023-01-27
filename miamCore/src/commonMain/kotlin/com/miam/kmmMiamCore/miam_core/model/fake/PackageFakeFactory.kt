package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmmMiamCore.miam_core.model.PackageAttributes
import com.miam.kmmMiamCore.miam_core.model.PackageRelationships
import com.miam.kmmMiamCore.miam_core.model.PackageSettings

public object PackageFakeFactory {

    public const val FAKE_ID: String = "packageUUID"
    public const val FAKE_TITLE: String = "packageTitle"

    public fun create(
        id: String = FAKE_ID,
        attributes: PackageAttributes? = createAttributes(),
        relationships: PackageRelationships? = null
    ): Package = Package(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        title: String = FAKE_TITLE
    ): PackageAttributes = PackageAttributes(
        title = title,
        authorId = null,
        editable = null,
        shared = null,
        catalogCategory = null,
        catalogPosition = null,
        recipesCount = 0,
        settings = PackageSettings(),
        // TODO Romain: Any useful parameter
    )
}