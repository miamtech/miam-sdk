package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.back
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.search
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.FullScreen
import com.miam.kmmMiamCore.component.catalogFilter.CatalogFilterContract
import com.miam.kmmMiamCore.component.catalogFilter.CatalogFilterViewModel

class CatalogSearch(
    private val catalogFilterVM: CatalogFilterViewModel,
    private val closeDialog : () -> Unit,
    private val goToSearchResult : () -> Unit,
) {


    private fun updateResearch(searchString : String){
        catalogFilterVM.setEvent(
            CatalogFilterContract.Event.SetSearchString(searchString)
        )
    }

    @Composable
    fun Content() {

        val state = catalogFilterVM.uiState.collectAsState()
        val focusRequester = FocusRequester()


        FullScreen{
            if(Template.CatalogSearchTemplate != null){
                Template.CatalogSearchTemplate?.let {
                    it(
                        state.value.searchString ?: "",
                        ::updateResearch,
                        {closeDialog()},
                        {goToSearchResult()},
                    )
                }
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(white)) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Row(Modifier.fillMaxWidth()) {
                            Clickable(onClick = {closeDialog()}) {
                                Image(
                                    painter = painterResource(back),
                                    contentDescription = null,
                                    Modifier.rotate(180f).padding(vertical = 8.dp)
                                )
                            }
                        }
                        Row(Modifier.fillMaxWidth().border(
                            border = BorderStroke(1.dp, Colors.primary),
                            shape = RoundedCornerShape(50)
                        ), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween){
                            TextField(
                                modifier=Modifier.weight(1f,false).
                                focusRequester(focusRequester),
                                value = state.value.searchString ?: "",
                                onValueChange = {
                                    updateResearch(it)
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    disabledTextColor = Color.Transparent,
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                placeholder = { Text( "Chercher un ingrédient ou une recette")}

                            )
                            Clickable(onClick = { goToSearchResult() }) {
                                Surface(
                                    shape = CircleShape,
                                    elevation = 8.dp,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                ) {
                                    Box(
                                        Modifier
                                            .background(Colors.primary)
                                            .padding(8.dp))
                                    {
                                        Image(
                                            painter = painterResource(search),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(white),
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
}