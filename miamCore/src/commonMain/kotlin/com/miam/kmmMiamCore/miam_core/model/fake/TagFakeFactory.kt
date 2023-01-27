package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Tag
import com.miam.kmmMiamCore.miam_core.model.TagAttributes

public object TagFakeFactory {

    public const val FAKE_ID: String = "tagUUID"
    public const val FAKE_NAME: String = "tagName"

    public fun create(
        id: String = FAKE_ID,
        attributes: TagAttributes? = createAttributes(),
    ): Tag = Tag(
        id = id,
        attributes = attributes,
    )

    public fun createAttributes(
        name: String = FAKE_NAME,
    ): TagAttributes = TagAttributes(
        name = name,
        tagTypeId = "",
        iconUrl = null,
        pictureUrl = null,
        // TODO Romain: Any useful parameter
    )
}