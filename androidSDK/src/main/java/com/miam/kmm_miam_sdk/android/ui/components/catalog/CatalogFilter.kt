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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterContract
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.close
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.FullScreen

// TODO Refact with filter service
class CatalogFilter(
    private val catalogFilterVM: SingletonFilterViewModel,
    private val closeDialog: () -> Unit,
    private val goToFilterResult: () -> Unit
) {
    private fun onCostFilterChanged(catOption: CatalogFilterOptions) {
        catalogFilterVM.setEvent(SingletonFilterContract.Event.OnCostFilterChanged(catOption))
    }

    private fun onTimeFilterChanged(catOption: CatalogFilterOptions) {
        catalogFilterVM.setEvent(SingletonFilterContract.Event.OnTimeFilterChanged(catOption))
    }

    private fun onDifficultyChanged(catOption: CatalogFilterOptions) {
        catalogFilterVM.setEvent(SingletonFilterContract.Event.OnDifficultyChanged(catOption))
    }

    private fun clearFilter() {
        catalogFilterVM.clear()
        catalogFilterVM.getRecipeCount()
    }

    @Composable
    fun Content() {

        val state = catalogFilterVM.uiState.collectAsState()

        FullScreen {
            if (Template.CatalogFilterTemplate != null) {
                Template.CatalogFilterTemplate?.let {
                    it(
                        state.value.difficulty,
                        state.value.cost,
                        state.value.time,
                        ::onCostFilterChanged,
                        ::onTimeFilterChanged,
                        ::onDifficultyChanged,
                        ::clearFilter,
                        { goToFilterResult() },
                        { closeDialog() }
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(white)
                ) {
                    Column(
                        Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Affiner ma sélection",
                                color = Colors.black,
                                style = Typography.subtitleBold
                            )
                            Clickable(
                                onClick = { closeDialog() },
                                children = {
                                    Image(
                                        painter = painterResource(close),
                                        contentDescription = null,
                                    )
                                }
                            )
                        }
                        Column(
                            Modifier
                                .weight(weight = 1f, fill = false)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(text = "Difficulté", style = Typography.bodyBold)
                            state.value.difficulty.forEach { catOption ->
                                CheckboxRow(
                                    catOption,
                                    mutableStateOf(catOption.isSelected),
                                    ::onDifficultyChanged
                                )
                            }
                            Divider(Modifier.padding(vertical = 16.dp))
                            Text(text = "Coût par personne", style = Typography.bodyBold)
                            state.value.cost.forEach { catOption ->
                                CheckboxRow(
                                    catOption,
                                    mutableStateOf(catOption.isSelected),
                                    ::onCostFilterChanged
                                )
                            }
                            Divider(Modifier.padding(vertical = 16.dp))
                            Text(text = "Temps de préparation", style = Typography.bodyBold)
                            state.value.time.forEach { catOption ->
                                CheckboxRow(
                                    catOption,
                                    mutableStateOf(catOption.isSelected),
                                    ::onTimeFilterChanged
                                )
                            }
                        }
                        Clickable(onClick = {
                            clearFilter()
                        }) {
                            Box(
                                modifier = Modifier
                                    .border(
                                        border = BorderStroke(1.dp, Colors.primary),
                                        shape = RoundedCornerShape(50)
                                    )
                            ) {
                                Text(
                                    text = "Retirer les filtres",
                                    color = primary,
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),
                                )
                            }
                        }

                        Divider(Modifier.padding(vertical = 8.dp))
                        Clickable(onClick = { goToFilterResult() }) {
                            Box(
                                Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(primary)
                            ) {
                                Text(
                                    text = "Voir les ${state.value.numberOfResult} idées repas",
                                    color = white,
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckboxRow(
    catOption: CatalogFilterOptions,
    checkedState: MutableState<Boolean>,
    updateFilter: (catOption: CatalogFilterOptions) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                updateFilter(catOption)
            },
            colors = CheckboxDefaults.colors(primary)
        )
        Text(text = catOption.uiLabel)
    }
}