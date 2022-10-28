package com.miam.kmm_miam_sdk.android.ui.components.preferences

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Tag
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.ressource.Image.close
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.backgroundGrey
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template.DietPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.EquipmentPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.GuestPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.IngredientPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.theme.Typography.bodySmall
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitle
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.common.RoundedCheckbox
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.android.ui.components.preferenceSearch.PreferencesSearch
import com.miam.kmm_miam_sdk.android.ui.components.preferences.customization.PreferencesText
import com.miam.kmm_miam_sdk.android.ui.components.preferences.customization.PreferencesText.title
import com.miam.kmm_miam_sdk.android.utils.FlowLayout


@Composable
fun GuestPreferencesSection() {
    if (GuestPreferencesSectionTemplate != null) {
        GuestPreferencesSectionTemplate?.let { it() }
    } else {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = PreferencesText.guestLabel, style = bodyBold)
            Counter(count = 1, isDisable = false, increase = { /*TODO*/ }, decrease = { /*TODO*/ })
        }
    }
}

@Composable
fun DietPreferencesSection(dietsTag: List<CheckableTag>, togglePreference: (pref: CheckableTag) -> Unit) {
    if (DietPreferencesSectionTemplate != null) {
        DietPreferencesSectionTemplate?.let { it() }
    } else {
        Column {
            Text(text = PreferencesText.dietLabel, style = bodyBold, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = PreferencesText.dietSubtitle, style = bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Column(Modifier.background(color = white, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))) {
                dietsTag.forEach { diet ->
                    Clickable(onClick = { togglePreference(diet) }) {
                        Column {
                            Row(Modifier.padding(vertical = 14.dp, horizontal = 18.dp)) {
                                RoundedCheckbox(check = diet.isChecked) {
                                    togglePreference(diet)
                                }
                                Text(
                                    text = diet.tag.attributes!!.name,
                                    style = body,
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientPreferencesSection(ingredientsTag: List<CheckableTag>, togglePreference: (pref: CheckableTag) -> Unit, toggleSearch: () -> Unit) {
    if (IngredientPreferencesSectionTemplate != null) {
        IngredientPreferencesSectionTemplate?.let { it() }
    } else {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = PreferencesText.ingredientLabel,
                style = bodyBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(text = PreferencesText.ingredientSubtitle, style = bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            FlowLayout(verticalSpacing = 4.dp, horizontalSpacing = 8.dp) {
                ingredientsTag.forEach { checkableTag ->
                    Clickable(onClick = { togglePreference(checkableTag) }) {
                        Box(
                            Modifier
                                .border(
                                    BorderStroke(1.dp, if (checkableTag.isChecked) Colors.primary else Color.Gray),
                                    RoundedCornerShape(50)
                                )
                                .clip(RoundedCornerShape(50))
                                .background(if (checkableTag.isChecked) Colors.primary else Colors.white)

                        ) {
                            Row(Modifier.padding(horizontal = 8.dp, vertical = 11.dp)) {
                                Text(
                                    text = checkableTag.tag.attributes!!.name,
                                    color = if (checkableTag.isChecked) Colors.white else Color.Gray
                                )
                                if (checkableTag.isChecked) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Image(
                                        painter = painterResource(Image.check),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(Colors.white),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Clickable(onClick = { toggleSearch() }) {
                    Box(
                        Modifier
                            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(50))
                            .background(Colors.white)
                    ) {
                        Row(Modifier.padding(horizontal = 8.dp, vertical = 11.dp)) {
                            Text(
                                text = "Ajouter +",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EquipmentPreferencesSection(equipmentsTag: List<CheckableTag>, togglePreference: (pref: CheckableTag) -> Unit) {
    if (EquipmentPreferencesSectionTemplate != null) {
        //  EquipmentPreferencesSectionTemplate.let { it() }
    } else {
        Column {
            Text(text = PreferencesText.equipmentLabel, style = bodyBold, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = PreferencesText.equipmentSubtitle, style = bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Column(Modifier.background(color = white, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))) {
                equipmentsTag.forEach { equipment ->
                    Clickable(onClick = { togglePreference(equipment) }) {
                        Column {
                            Row(Modifier.padding(vertical = 14.dp, horizontal = 18.dp)) {
                                RoundedCheckbox(check = equipment.isChecked) {
                                    togglePreference(equipment)
                                }
                                Text(
                                    text = equipment.tag.attributes!!.name,
                                    style = body,
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PreferencesHeader(closePref: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Clickable(onClick = { closePref() }) {
                Box(
                    Modifier
                        .padding(12.dp)
                        .clip(CircleShape)
                        .background(backgroundGrey)
                ) {
                    Image(
                        painter = painterResource(close),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = subtitle.copy(fontWeight = FontWeight.Bold))
        }
        Divider(Modifier.fillMaxWidth())
    }

}

@Composable
fun PreferencesFooter(closePref: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = white,
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Clickable(onClick = { closePref() }) {
            Box(
                Modifier
                    .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(50))
                    .background(Colors.white)
            ) {
                Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(
                        text = "Annuler",
                        style = subtitle.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                }
            }
        }
        Clickable(onClick = {}) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(50))
                    .background(primary)
                    .border(BorderStroke(1.dp, primary), RoundedCornerShape(50))
            ) {
                Row(
                    Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)

                ) {
                    Text(
                        text = "Voir les 60 repas",
                        style = subtitle.copy(fontWeight = FontWeight.Bold),
                        color = white
                    )
                }
            }
        }
    }
}

@Composable
fun PreferencesSuccessView(
    context: Context,
    ingredientsTag: List<CheckableTag>,
    dietsTag: List<CheckableTag>,
    equipmentTag: List<CheckableTag>,
    togglePreference: (pref: CheckableTag) -> Unit,
    closePreference: () -> Unit,
    addIngredientPreferences: (tag: Tag) -> Unit

) {

    var showSearch by remember { mutableStateOf(false) }

    if (showSearch) {
        val prefSearch = PreferencesSearch(context)
        prefSearch.bind({ showSearch = !showSearch }, {
            addIngredientPreferences(it)
            showSearch = !showSearch
        })
        prefSearch.Content()
    } else {
        Scaffold(
            topBar = { PreferencesHeader(closePreference) },
            content = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(color = backgroundGrey)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    GuestPreferencesSection()
                    Spacer(modifier = Modifier.height(24.dp))
                    DietPreferencesSection(dietsTag, togglePreference)
                    Spacer(modifier = Modifier.height(24.dp))
                    IngredientPreferencesSection(ingredientsTag, togglePreference) { showSearch = !showSearch }
                    Spacer(modifier = Modifier.height(24.dp))
                    EquipmentPreferencesSection(equipmentTag, togglePreference)
                    Spacer(modifier = Modifier.height(100.dp))
                }
            },
            bottomBar = { PreferencesFooter(closePreference) })
    }
}
