package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.catalogFilter.CatalogFilterContract
import com.miam.kmmMiamCore.component.catalogFilter.CatalogFilterViewModel
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.search
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.FullScreen

class CatalogSearch(
    private val catalogFilterVM: CatalogFilterViewModel,
    private val closeDialog: () -> Unit,
    private val goToSearchResult: () -> Unit,
) {

    private fun updateResearch(searchString: String) {
        catalogFilterVM.setEvent(
            CatalogFilterContract.Event.SetSearchString(searchString)
        )
    }

    @Composable
    fun Content() {

        val state = catalogFilterVM.uiState.collectAsState()
        val textState = remember { mutableStateOf(state.value.searchString ?: "") }

        FullScreen {
            if (Template.CatalogSearchTemplate != null) {
                TemplateView(state)
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(white)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        BackButton()
                        SearchContainer {
                            SearchTextField(textState, Modifier.weight(1f, false))
                            SearchButton(textState)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun TemplateView(state: State<CatalogFilterContract.State>) {
        Template.CatalogSearchTemplate?.let {
            it(
                state.value.searchString ?: "",
                ::updateResearch,
                { closeDialog() },
                { goToSearchResult() },
            )
        }
    }

    @Composable
    fun BackButton() {
        Row(Modifier.fillMaxWidth()) {
            Clickable(onClick = { closeDialog() }) {
                Image(
                    painter = painterResource(CatalogImage.back),
                    contentDescription = null,
                    Modifier
                        .rotate(180f)
                        .padding(vertical = 8.dp)
                )
            }
        }
    }

    @Composable
    fun SearchContainer(children: @Composable () -> Unit) {
        Row(
            Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, Colors.primary),
                    shape = RoundedCornerShape(50)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            children()
        }
    }

    @Composable
    fun SearchTextField(textState: MutableState<String>, modifier: Modifier) {
        val focusRequester = FocusRequester()
        TextField(
            modifier = modifier.focusRequester(focusRequester),
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text("Chercher un ingrédient ou une recette") }
        )
    }

    @Composable
    fun SearchButton(textState: MutableState<String>) {
        Clickable(onClick = {
            updateResearch(textState.value)
            goToSearchResult()
        }) {
            Surface(
                shape = CircleShape,
                elevation = 8.dp,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Box(
                    Modifier
                        .background(Colors.primary)
                        .padding(8.dp)
                )
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