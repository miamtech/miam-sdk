package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogColor.headerTextColor
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.favorite
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.filter
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.recipeId
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.search
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.trait
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.component.catalog.CatalogContent
import com.miam.kmm_miam_sdk.component.catalog.CatalogContract
import com.miam.kmm_miam_sdk.component.catalog.CatalogViewModel

@Composable
fun CatalogHeader( state: CatalogContract.State , catalogVm : CatalogViewModel) {

    val showFullHeader = state.content == CatalogContent.DEFAULT

    fun openFilter(){
        catalogVm.setEvent(CatalogContract.Event.ToggleFilter)
    }
    
    Column() {
        if(showFullHeader){
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = primary)
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
                    Box() {
                        Text(
                            text = "Idées repas en 1 clic",
                            color = headerTextColor,
                            style = subtitleBold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        Image(
                            painter = painterResource(trait),
                            contentDescription = null,
                            Modifier.align(Alignment.BottomEnd).offset(y= 4.dp)
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
            horizontalArrangement = Arrangement.Start
        ) {
            Clickable(onClick = {}, children = {
                Surface(
                    shape = CircleShape,
                    elevation = 8.dp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Box(
                        Modifier
                            .background(primary)
                            .padding(8.dp))
                    {
                        Image(
                            painter = painterResource(search),
                            contentDescription = null,
                        )
                    }
                }
            }
            )

            Clickable(onClick = {openFilter()}, children = {
                Surface(
                    shape = CircleShape,
                    elevation = 8.dp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Box(
                        Modifier
                            .background(primary)
                            .padding(8.dp))
                    {
                        Image(
                            painter = painterResource(filter),
                            contentDescription = null,
                        )
                    }
                }
            })
            Box(modifier = Modifier
                .padding(horizontal = 10.dp)
                .border(
                    border = BorderStroke(1.dp, primary),
                    shape = RoundedCornerShape(50)
                )
            ) {
                Row( Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(favorite),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(primary),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Mes idées repas",
                        color = primary
                    )
                }
            }
        }
    }
}