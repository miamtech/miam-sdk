package com.miam.kmm_miam_sdk.android.ui.components.catalogHeader

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.catalog.CatalogFilter
import com.miam.kmm_miam_sdk.android.ui.components.catalog.CatalogSearch
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogColor.headerTextColor
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.recipeId
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.trait
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.headerTitle
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable


enum class HeaderState {
    DEFAULT, FILTER, SEARCH, PREFERENCES
}

@Composable
fun CatalogHeader(isMainPage: Boolean, isFavorit: Boolean, goToRecipeList: () -> Unit, goToFavorite: () -> Unit, goToBack: () -> Unit) {

    var headerState by remember { mutableStateOf(HeaderState.DEFAULT) }
    val catalogFilterVm = SingletonFilterViewModel()

    fun openFilter() {
        headerState = HeaderState.FILTER
    }

    fun openSearch() {
        headerState = HeaderState.SEARCH
    }

    fun closeModal() {
        headerState = HeaderState.DEFAULT
    }

    fun openPreferences() {
        headerState = HeaderState.PREFERENCES
    }

    // TODO Refact with filter service
    val filter = CatalogFilter(catalogFilterVm, ::closeModal, goToRecipeList)
    // TODO Refact with filter service
    val search = CatalogSearch(catalogFilterVm, ::closeModal, goToRecipeList)


    fun getActiveFilterCount(): Int {
        // (TODO) use filter service
        return -1
    }

    @Composable
    fun HeaderTitle() {
        Box(
            Modifier.fillMaxWidth()
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

    @Composable
    fun BackButton() {
        Clickable(onClick = { goToBack() }) {
            Image(
                painter = painterResource(CatalogImage.back),
                contentDescription = null,
                colorFilter = ColorFilter.tint(white),
                modifier = Modifier
                    .rotate(180f)
                    .padding(vertical = 8.dp)
            )
        }
    }

    @Composable
    fun SearchButton() {
        Clickable(onClick = { openSearch() }) {
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
                        painter = painterResource(Image.search),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(primary),
                    )
                }
            }
        }
    }

    @Composable
    fun FilterButton() {
        Clickable(onClick = { openFilter() }) {
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
                            painter = painterResource(Image.filter),
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
        }
    }

    @Composable
    fun FavoriteButton() {
        Clickable(onClick = { goToFavorite() }) {
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
                        painter = painterResource(Image.favorite),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(primary),
                    )
                }
            }
        }
    }

    @Composable
    fun MainPageFavoriteButton() {
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
                    painter = painterResource(Image.favorite),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(white),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = CatalogText.favoriteButtonText,
                    color = white
                )
            }
        }
    }

    @Composable
    fun MainPageHeader() {
        HeaderTitle()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            BackButton()
            Row {
                SearchButton()
                FilterButton()
                MainPageFavoriteButton()
            }
        }
    }

    @Composable
    fun DefaultHeader() {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                SearchButton()
                FilterButton()
                if (!isFavorit) {
                    FavoriteButton()
                }
            }
        }
    }

    //  current returned component
    Box {
        when (headerState) {
            HeaderState.FILTER -> filter.Content()
            HeaderState.SEARCH -> search.Content()
            else -> {
                Box {}
            }
        }
        if (Template.CatalogHeader != null) {
            Template.CatalogHeader?.let {
                it(::openFilter, ::openSearch, ::openPreferences, goToFavorite, goToBack, ::getActiveFilterCount)
            }
        } else {
            Column(Modifier.background(color = primary)) {
                if (isMainPage) {
                    MainPageHeader()
                    return
                }
                DefaultHeader()
            }
        }
    }
}
