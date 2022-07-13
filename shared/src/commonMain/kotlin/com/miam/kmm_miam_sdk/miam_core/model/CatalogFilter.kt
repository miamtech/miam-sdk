package com.miam.kmm_miam_sdk.miam_core.model

data class CatalogFilterOptions(
     val name: String,
     val uiLabel: String,
     var isSelected: Boolean = false
) {
     fun on(): CatalogFilterOptions {
          return copy(isSelected = true)
     }

     fun off(): CatalogFilterOptions {
          return copy(isSelected = false)
     }

     fun toogle(): CatalogFilterOptions {
          return copy(isSelected = !isSelected)
     }
}

 
