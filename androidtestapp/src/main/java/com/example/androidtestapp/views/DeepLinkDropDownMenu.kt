package com.example.androidtestapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

        val expanded = remember { mutableStateOf(false) }

        Box {
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "More Menu"
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {

                categoriesState.value.forEach {
                    DropdownMenuItem(onClick = {
                        navigateTo(it.id)
                        expanded.value = false
                    }) {
                        Text(it.title)
                    }
                }
            }
        }
    }

}