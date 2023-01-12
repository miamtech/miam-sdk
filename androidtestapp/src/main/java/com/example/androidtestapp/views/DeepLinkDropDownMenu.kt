package com.example.androidtestapp.views

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.miam.kmmMiamCore.handler.CatalogCategory
import com.miam.kmmMiamCore.handler.PointOfSaleHandler

class DeepLinkDropDownMenu(val navigateTo: (id: String) -> Unit) {

    private val categoriesState: MutableState<List<CatalogCategory>> =
        mutableStateOf(listOf())

    init {
        PointOfSaleHandler.getCatalogCategories(::fetchCategory)
    }

    private fun fetchCategory(categories: List<CatalogCategory>) {
        categoriesState.value = categories
    }

    @Composable
    fun Content() {

        var expanded by remember { mutableStateOf(true) }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            categoriesState.value.forEach {
                DropdownMenuItem(onClick = { navigateTo(it.id) }) {
                    Text(it.title)
                }
            }
        }
    }
}