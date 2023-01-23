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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.preferences.PreferencesContent
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
import com.miam.kmm_miam_sdk.android.theme.Template.PreferencesFooterTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.PreferencesHeaderTemplate
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
fun GuestPreferencesSection(guests: Int?, guestChanged: (count: Int) -> Unit) {
    if (GuestPreferencesSectionTemplate != null) {
        GuestPreferencesSectionTemplate?.let { it(guests, guestChanged) }
    } else {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = PreferencesText.guestLabel, style = bodyBold)
            Counter(
                initialCount = guests,
                isDisable = false,
                onCounterChanged = { guestChanged(it) },
                minValue = 1,
                maxValue = 99
            )
        }
    }
}

@Composable
fun DietPreferencesSection(dietsTag: List<CheckableTag>, togglePreference: (tagIdToToogle: String) -> Unit) {
    if (DietPreferencesSectionTemplate != null) {
        DietPreferencesSectionTemplate?.let { it(dietsTag, togglePreference) }
    } else {
        Column {
            Text(text = PreferencesText.dietLabel, style = bodyBold, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = PreferencesText.dietSubtitle, style = bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Column(Modifier.background(color = white, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))) {
                dietsTag.forEach { diet ->
                    Clickable(onClick = { togglePreference(diet.tag.id) }) {
                        Column {
                            Row(Modifier.padding(vertical = 14.dp, horizontal = 18.dp)) {
                                RoundedCheckbox(check = diet.isChecked) {
                                    togglePreference(diet.tag.id)
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
fun IngredientPreferencesSection(
    ingredientsTag: List<CheckableTag>,
    togglePreference: (tagIdToToogle: String) -> Unit,
    back: () -> Unit,
    goToSearch: () -> Unit
) {
    if (IngredientPreferencesSectionTemplate != null) {
        IngredientPreferencesSectionTemplate?.let { it(ingredientsTag, togglePreference, back, goToSearch) }
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
                    Clickable(onClick = { togglePreference(checkableTag.tag.id) }) {
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
                Clickable(onClick = goToSearch) {
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
fun EquipmentPreferencesSection(equipmentsTag: List<CheckableTag>, togglePreference: (tagIdToToogle: String) -> Unit) {
    if (EquipmentPreferencesSectionTemplate != null) {
        EquipmentPreferencesSectionTemplate?.let { it(equipmentsTag, togglePreference) }
    } else {
        Column {
            Text(text = PreferencesText.equipmentLabel, style = bodyBold, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = PreferencesText.equipmentSubtitle, style = bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Column(Modifier.background(color = white, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))) {
                equipmentsTag.forEach { equipment ->
                    Clickable(onClick = { togglePreference(equipment.tag.id) }) {
                        Column {
                            Row(Modifier.padding(vertical = 14.dp, horizontal = 18.dp)) {
                                RoundedCheckbox(check = equipment.isChecked) {
                                    togglePreference(equipment.tag.id)
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
    if (PreferencesHeaderTemplate != null) {
        PreferencesHeaderTemplate?.let { it(closePref) }
    } else {
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
}

@Composable
fun PreferencesFooter(closePref: () -> Unit, applyPref: () -> Unit, recipesFound: Int) {
    if (PreferencesFooterTemplate != null) {
        PreferencesFooterTemplate?.let { it(closePref, applyPref, recipesFound) }
    } else {
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
            Clickable(onClick = { applyPref() }) {
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
                            text = "Voir les $recipesFound repas",
                            style = subtitle.copy(fontWeight = FontWeight.Bold),
                            color = white
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PreferencesSuccessView(
    content: PreferencesContent,
    context: Context,
    guests: Int?,
    recipesFound: Int,
    ingredientsTag: List<CheckableTag>,
    dietsTag: List<CheckableTag>,
    equipmentTag: List<CheckableTag>,
    togglePreference: (tagIdToToogle: String) -> Unit,
    closePreferences: () -> Unit,
    applyPreferences: () -> Unit,
    guestChanged: (count: Int) -> Unit,
    addIngredientPreferences: (tag: Tag) -> Unit,
    goToSearch: () -> Unit,
    back: () -> Unit
) {
    when (content) {
        PreferencesContent.SEARCH_PREFRERENCES -> {
            val prefSearch = PreferencesSearch(context)
            prefSearch.bind(back) {
                addIngredientPreferences(it)
                back()
            }
            prefSearch.Content()
        }
        PreferencesContent.ALL_PREFRENCES -> {
            Scaffold(
                topBar = { PreferencesHeader(closePreferences) },
                content = {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .background(color = backgroundGrey)
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        GuestPreferencesSection(guests, guestChanged)
                        Spacer(modifier = Modifier.height(24.dp))
                        DietPreferencesSection(dietsTag, togglePreference)
                        Spacer(modifier = Modifier.height(24.dp))
                        IngredientPreferencesSection(ingredientsTag, togglePreference, back) { goToSearch() }
                        Spacer(modifier = Modifier.height(24.dp))
                        EquipmentPreferencesSection(equipmentTag, togglePreference)
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                },
                bottomBar = { PreferencesFooter(closePreferences, applyPreferences, recipesFound) })
        }
    }
}
