package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage.close
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.FullScreen
import com.miam.kmm_miam_sdk.miam_core.model.CatalogFilter

class CatalogFilter(private val filters: CatalogFilter) {

    @Composable
    fun Content() {
        FullScreen{
            Column(Modifier.padding(vertical = 8.dp, horizontal = 16.dp).background(white),
                horizontalAlignment = Alignment.CenterHorizontally) {
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
                        onClick = { },
                        children = {
                            Image(
                                painter = painterResource(close),
                                contentDescription = null,
                            )
                        }
                    )
                }
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    Text(text = "Difficulté")
                    filters.difficulty.forEach {
                        Row() {
                            Checkbox(
                                checked = it.isSelected,
                                onCheckedChange = { },
                            )
                            Text(text = it.uiLabel, modifier = Modifier.padding(start = 10.dp))
                        }
                    }
                    Divider(Modifier.padding(vertical = 16.dp))
                    Text(text = "Coût par personne")
                    filters.cost.forEach {
                        Row() {
                            Checkbox(
                                checked = it.isSelected,
                                onCheckedChange = { },
                            )
                            Text(text = it.uiLabel, modifier = Modifier.padding(start = 10.dp))
                        }
                    }
                    Divider(Modifier.padding(vertical = 16.dp))
                    Text(text = "Temps de préparation")
                    filters.time.forEach {
                        Row() {
                            Checkbox(
                                checked = it.isSelected,
                                onCheckedChange = { },
                            )
                            Text(text = it.uiLabel, modifier = Modifier.padding(start = 10.dp))
                        }
                    }
                }
                Clickable(onClick = { filters.clearFilter() }) {
                    Box(modifier = Modifier
                        .border(
                            border = BorderStroke(1.dp, Colors.primary),
                            shape = RoundedCornerShape(50)
                        )
                    ) {
                        Text(
                            text = "Retirer les filtres",
                            color = primary,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                    }
                }
                Divider(Modifier.padding(vertical = 8.dp))
                Clickable(onClick = { filters.clearFilter() }) {
                    Box(Modifier.clip(RoundedCornerShape(50)).background(primary)) {
                        Text(text = "Voir les 30 idées repas",
                            color = white,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                    }
                }
            }
        }
    }
}