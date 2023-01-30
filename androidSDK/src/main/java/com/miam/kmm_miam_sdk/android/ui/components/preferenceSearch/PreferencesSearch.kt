package com.miam.kmm_miam_sdk.android.ui.components.preferenceSearch

import android.content.Context
import android.util.AttributeSet
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.preferencesSearch.PreferencesSearchViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.Tag
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.preferenceSearch.customization.PreferencesText.searchPreferencePlaceholder
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class PreferencesSearch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {

    private var back: () -> Unit = {
        LogHandler.error("[Miam][Error] you must bind back function")
    }
    private var addTag: (tag: Tag) -> Unit = {
        LogHandler.error("[Miam][Error] you must bind addTag function")
    }

    fun bind(
        back: () -> Unit,
        addTag: (tag: Tag) -> Unit
    ) {
        this.back = back
        this.addTag = addTag
    }

    private val preferencesSearchVM = PreferencesSearchViewModel()

    @Composable
    override fun Content() {

        var text by remember { mutableStateOf(TextFieldValue("")) }

        Column(
            Modifier
                .fillMaxSize()
                .background(white)             
        ) {
            if (Template.SearchPreferencesTemplate != null) {
                Template.SearchPreferencesTemplate?.let {
                    it(back, text) {
                        text = it
                        preferencesSearchVM.search(it.text)
                    }
                }
            } else {
                Row(Modifier.padding(vertical = 24.dp, horizontal = 16.dp) ,verticalAlignment = Alignment.CenterVertically) {
                    BackButton(back)
                    SearchContainer {
                        TextField(
                            value = text,
                            onValueChange = {
                                text = it
                                preferencesSearchVM.search(it.text)
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                disabledTextColor = Color.Transparent,
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            placeholder = {
                                Text(searchPreferencePlaceholder)
                            }
                        )
                    }
                }
            }
            StateManager(preferencesSearchVM)
        }
    }

    @Composable
    fun StateManager(preferencesSearchVM: PreferencesSearchViewModel) {
        val state by preferencesSearchVM.uiState.collectAsState()
        ManagementResourceState(
            resourceState = state.searchProposal,
            successView = { tags ->
                requireNotNull(tags)
                Column {
                    tags.forEach { tag ->
                        if (Template.SearchResultRowPreferencesTemplate != null) {
                            Template.SearchResultRowPreferencesTemplate?.let {
                                it({
                                    preferencesSearchVM.resetState()
                                    addTag(tag)
                                }, tag.attributes!!.name)
                            }
                        } else {

                            Clickable(onClick = {
                                preferencesSearchVM.resetState()
                                addTag(tag)
                            }) {
                                Row(Modifier.fillMaxWidth().padding( horizontal = 16.dp)) {
                                    Text(
                                        text = tag.attributes!!.name,
                                        style = body,
                                        modifier = Modifier.padding(vertical = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            loadingView = {
                if (Template.SearchPreferencesLoadingTemplate != null) {
                    Template.SearchPreferencesLoadingTemplate?.let { it() }
                } else {
                    Box {
                    }
                }
            },
            emptyView = {
                if (Template.SearchPreferencesEmptyTemplate != null) {
                    Template.SearchPreferencesEmptyTemplate?.let { it() }
                } else {
                    Box {
                    }
                }
            },
            onTryAgain = { },
            onCheckAgain = { },
        )
    }
}


@Composable
fun SearchContainer(children: @Composable () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Colors.grey),
                shape = RoundedCornerShape(50)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        children()
    }
}

@Composable
fun BackButton(back: () -> Unit) {
    Clickable(onClick = { back() }) {
        Image(
            painter = painterResource(CatalogImage.back),
            contentDescription = null,
            Modifier
                .rotate(180f)
                .padding(vertical = 8.dp)
        )
    }
}
