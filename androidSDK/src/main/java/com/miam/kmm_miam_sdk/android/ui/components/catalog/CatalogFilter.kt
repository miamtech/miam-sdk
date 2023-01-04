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
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterContract
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.close
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable


// TODO Refact with filter service
class CatalogFilter(
    private val closeDialog: () -> Unit,
    private val goToFilterResult: () -> Unit
) {

    private val filterVM = FilterViewModelInstance.instance

    private fun onCostFilterChanged(catOption: CatalogFilterOptions) {
        filterVM.setEvent(SingletonFilterContract.Event.OnCostFilterChanged(catOption))
    }

    private fun onTimeFilterChanged(catOption: CatalogFilterOptions) {
        filterVM.setEvent(SingletonFilterContract.Event.OnTimeFilterChanged(catOption))
    }

    private fun onDifficultyChanged(catOption: CatalogFilterOptions) {
        filterVM.setEvent(SingletonFilterContract.Event.OnDifficultyChanged(catOption))
    }

    private fun clearFilter() {
        filterVM.clear()
    }

    private fun applyAndGo() {
        filterVM.applyFilter()
        goToFilterResult()
    }

    @Composable
    fun Content() {

        val state = filterVM.uiState.collectAsState()

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
                    ::applyAndGo,
                    closeDialog
                )
            }
        } else {
            MiamCatalogFilter(
                difficulties = state.value.difficulty,
                costs = state.value.cost,
                times = state.value.time,
                onCostFilterChanged = ::onCostFilterChanged,
                onTimeFilterChanged = ::onTimeFilterChanged,
                onDifficultyChanged = ::onDifficultyChanged,
                clearFilter = ::clearFilter,
                applyAndGo = ::applyAndGo,
                closeDialog = closeDialog,
                state.value.numberOfResult
            )
        }
    }
}

@Composable
fun MiamCatalogFilter(
    difficulties: List<CatalogFilterOptions>,
    costs: List<CatalogFilterOptions>,
    times: List<CatalogFilterOptions>,
    onCostFilterChanged: (CatalogFilterOptions) -> Unit,
    onTimeFilterChanged: (CatalogFilterOptions) -> Unit,
    onDifficultyChanged: (CatalogFilterOptions) -> Unit,
    clearFilter: () -> Unit,
    applyAndGo: () -> Unit,
    closeDialog: () -> Unit,
    numberOfResult: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Column(
            Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilterHeader(closeDialog = closeDialog)
            Column(
                Modifier
                    .weight(weight = 1f, fill = false)
                    .verticalScroll(rememberScrollState())
            ) {
                FilterSection(text = "Difficulté", catalogFilterOptions = difficulties, onDifficultyChanged)
                Divider(Modifier.padding(vertical = 16.dp))
                FilterSection(text = "Coût par personne", catalogFilterOptions = costs, onCostFilterChanged)
                Divider(Modifier.padding(vertical = 16.dp))
                FilterSection(text = "Temps de préparation", catalogFilterOptions = times, onTimeFilterChanged)
            }
            ClearButton(clearFilter)
            Divider(Modifier.padding(vertical = 8.dp))
            ApplyAndGoButton(applyAndGo, numberOfResult)
        }
    }
}

@Composable
fun FilterHeader(closeDialog: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Affiner ma sélection", color = Colors.black, style = Typography.subtitleBold)
        Clickable(
            onClick = closeDialog,
            children = { Image(painter = painterResource(close), contentDescription = null) }
        )
    }
}

@Composable
fun FilterSection(text: String, catalogFilterOptions: List<CatalogFilterOptions>, onCheckedCallback: (CatalogFilterOptions) -> Unit) {
    Text(text = text, style = Typography.bodyBold)
    catalogFilterOptions.forEach { catOption ->
        CheckboxRow(catOption, mutableStateOf(catOption.isSelected)) { onCheckedCallback(catOption) }
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

@Composable
fun ClearButton(clearFilter: () -> Unit) {
    Clickable(onClick = { clearFilter() }) {
        Box(
            modifier = Modifier.border(border = BorderStroke(1.dp, primary), shape = RoundedCornerShape(50))
        ) {
            Text(
                text = "Retirer les filtres",
                color = primary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun ApplyAndGoButton(applyAndGo: () -> Unit, numberOfResult: Int) {
    Clickable(onClick = applyAndGo) {
        Box(
            Modifier
                .clip(RoundedCornerShape(50))
                .background(primary)
        ) {
            Text(
                text = "Voir les ${numberOfResult} idées repas",
                color = white,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}