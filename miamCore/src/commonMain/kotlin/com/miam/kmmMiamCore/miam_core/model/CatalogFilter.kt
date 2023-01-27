package com.miam.kmmMiamCore.miam_core.model

public data class CatalogFilterOptions(
    val name: String,
    val uiLabel: String,
    var isSelected: Boolean = false
) {
    public fun on(): CatalogFilterOptions {
        return copy(isSelected = true)
    }

    public fun off(): CatalogFilterOptions {
        return copy(isSelected = false)
    }

    public fun toogle(): CatalogFilterOptions {
        return copy(isSelected = !isSelected)
    }
}

 
