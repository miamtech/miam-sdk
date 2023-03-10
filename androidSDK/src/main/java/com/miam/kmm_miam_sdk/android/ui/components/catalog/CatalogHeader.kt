package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.catalog.CatalogContent
import com.miam.kmmMiamCore.component.catalog.CatalogContract
import com.miam.kmmMiamCore.component.catalog.CatalogViewModel
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmm_miam_sdk.android.ressource.Image.guests
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogColor.headerTextColor
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.back
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.favorite
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.filter
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.recipeId
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.search
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.trait
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.favoriteTitle
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.headerTitle
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable

@Composable
fun CatalogHeader(content: CatalogContent, enableFilters: Boolean, enablePreferences: Boolean, catalogVm: CatalogViewModel) {

    val filterVM = FilterViewModelInstance.instance
    val showFullHeader = content == CatalogContent.CATEGORIES_LIST
    val isFavorite = filterVM.currentState.isFavorite

    fun openFilter() {
        catalogVm.openFilter()
    }

    fun openSearch() {
        catalogVm.openSearch()
    }

    fun goToFavorite() {
        catalogVm.setEvent(CatalogContract.Event.GoToFavorite)
    }

    fun openPreferences() {
        catalogVm.openPreferences()
    }

    fun goToBack() {
        catalogVm.setEvent(CatalogContract.Event.GoBack)
    }

    fun getActiveFilterCount(): Int {
        return filterVM.getActiveFilterCount()
    }

    if (Template.CatalogHeader != null) {
        Template.CatalogHeader?.let {
            it(::openFilter, ::openSearch, ::openPreferences, ::goToFavorite, ::goToBack, ::getActiveFilterCount, showFullHeader, isFavorite)
        }
    } else {
        Column(Modifier.background(color = primary)) {
            if (showFullHeader) {
                Box(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(recipeId),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(headerTextColor),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Box {
                            Text(
                                text = headerTitle,
                                color = headerTextColor,
                                style = subtitleBold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                            Image(
                                painter = painterResource(trait),
                                contentDescription = null,
                                Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(y = 4.dp)
                            )

                        }
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (showFullHeader) Arrangement.Start else Arrangement.SpaceBetween
            ) {
                if (!showFullHeader) {
                    Clickable(onClick = { goToBack() }, children = {
                        Image(
                            painter = painterResource(back),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(white),
                            modifier = Modifier
                                .rotate(180f)
                                .padding(vertical = 8.dp)
                        )
                    }
                    )
                }
                Row {
                    Clickable(onClick = { openSearch() }, children = {
                        Surface(
                            shape = CircleShape,
                            elevation = 8.dp,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Box(
                                Modifier
                                    .background(white)
                                    .padding(8.dp)
                            )
                            {
                                Image(
                                    painter = painterResource(search),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(primary),
                                )
                            }
                        }
                    }
                    )
                    if (enablePreferences) {
                        Clickable(onClick = { openPreferences() }) {
                            Surface(
                                shape = CircleShape,
                                elevation = 8.dp,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Box(
                                    Modifier
                                        .background(white)
                                        .padding(8.dp)
                                )
                                {
                                    Image(
                                        painter = painterResource(guests),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(primary),
                                    )
                                }
                            }
                        }
                    }
                    if (enableFilters) {
                        Clickable(onClick = { openFilter() }, children = {
                            Box {
                                Surface(
                                    shape = CircleShape,
                                    elevation = 8.dp,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                ) {
                                    Row(
                                        Modifier
                                            .background(white)
                                            .padding(8.dp)
                                    )
                                    {
                                        Image(
                                            painter = painterResource(filter),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(primary),
                                        )

                                    }
                                }
                                if (getActiveFilterCount() != 0) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                            .align(Alignment.TopEnd)
                                    ) {
                                        Text(
                                            text = getActiveFilterCount().toString(),
                                            color = white,
                                            modifier = Modifier.align(Alignment.Center)

                                        )
                                    }
                                }
                            }
                        })
                        if (showFullHeader) {
                            Box(modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .border(
                                    border = BorderStroke(1.dp, white),
                                    shape = RoundedCornerShape(50)
                                )
                                .clickable { goToFavorite() }
                            ) {
                                Row(
                                    Modifier
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                        .background(primary),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(favorite),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(white),
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                    Text(
                                        text = favoriteTitle,
                                        color = white
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}