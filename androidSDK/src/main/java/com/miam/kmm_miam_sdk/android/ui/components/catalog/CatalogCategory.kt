package com.miam.kmm_miam_sdk.android.ui.components.catalog

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogColor.moreInfoTextColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeView
import com.miam.kmmMiamCore.miam_core.model.Package

@Composable
fun CatalogCategory(
        category: Package,
        context : Context,
        goToCategoryPage : (category : Package) -> Unit
) {

        if(Template.CatalogCategoryTemplate != null) {
                  Template.CatalogCategoryTemplate?.let {
                          it(
                                  context = context,
                                  category= category,
                                  recipesID = category.relationships?.recipes?.data?.map { it.id } ?: emptyList()){
                                  goToCategoryPage(it)
                          }
                  }
        } else {
                Column() {
                        Row(
                                Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)) {
                                Column() {
                                        category.attributes?.title?.let {
                                                Text(
                                                        text = it,
                                                        color= Colors.black,
                                                        style = Typography.subtitleBold
                                                )
                                        }
                                        category.attributes?.settings?.subtitle.let {
                                                Text(
                                                        text = it ?: "",
                                                        color= Colors.black,
                                                        modifier = Modifier.padding(top= 4.dp)
                                                )
                                        }
                                }
                        }
                        Row(
                                Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp).clickable { goToCategoryPage(category) },
                                horizontalArrangement = Arrangement.End) {
                                Text(
                                        text = "Tout voir",
                                        style = TextStyle(textDecoration = TextDecoration.Underline),
                                        color = moreInfoTextColor
                                )
                        }
                        LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                                items(items = (category.relationships?.recipes?.data ?: emptyList())) { item ->
                                        Box(modifier = Modifier
                                                .width(350.dp)
                                                .height(600.dp)){
                                                val recipe = RecipeView(context)
                                                recipe.bind(recipeId = item.id)
                                                recipe.isNotInShelf()
                                                recipe.Content()
                                        }
                                }
                        }
                }
        }

}