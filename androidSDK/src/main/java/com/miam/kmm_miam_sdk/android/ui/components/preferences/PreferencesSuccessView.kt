package com.miam.kmm_miam_sdk.android.ui.components.preferences

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Dimension
import com.miam.kmm_miam_sdk.android.theme.Template.DietPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.EquipmentPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.GuestPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.IngredientPreferencesSectionTemplate
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorStyle
import com.miam.kmm_miam_sdk.android.ui.components.preferences.customization.PreferencesText


@Composable
fun GuestPreferencesSection() {
    if (GuestPreferencesSectionTemplate != null) {
        GuestPreferencesSectionTemplate?.let { it() }
    } else {
        Row(Modifier.fillMaxWidth()) {
            Text(text = PreferencesText.guestLabel)
            Counter(count = 1, isDisable = false, increase = { /*TODO*/ }, decrease = { /*TODO*/ })
        }
    }
}

@Composable
fun DietPreferencesSection() {
    if (DietPreferencesSectionTemplate != null) {
        DietPreferencesSectionTemplate?.let { it() }
    } else {
        Column {
            Text(text = PreferencesText.guestLabel)
            Text(text = PreferencesText.guestLabel)
            Column {


            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IngredientPreferencesSection(ingredientsTag: List<CheckableTag>) {
    if (IngredientPreferencesSectionTemplate != null) {
        IngredientPreferencesSectionTemplate?.let { it() }
    } else {
        Column {
            Text(text = PreferencesText.guestLabel)
            Text(text = PreferencesText.guestLabel)
            LazyVerticalGrid(
                cells = GridCells.Adaptive(ItemsSelectorStyle.itemsWidth.dp),
                contentPadding = PaddingValues(
                    start = Dimension.mPadding,
                    top = Dimension.lPadding,
                    end = Dimension.mPadding,
                    bottom = 0.dp
                ),
                content = {
                    items(ingredientsTag.size) { index ->
                        Clickable(onClick = {/*TODO*/ }) {
                            Box(
                                Modifier
                                    .border(
                                        BorderStroke(
                                            1.dp,
                                            if (ingredientsTag[index].isChecked) Colors.primary else Color.Gray
                                        ),
                                        RoundedCornerShape(50)
                                    )
                                    .background(if (ingredientsTag[index].isChecked) Colors.primary else Colors.white)
                            ) {
                                Row(Modifier.padding(horizontal = 8.dp, vertical = 11.dp)) {
                                    Text(
                                        text = ingredientsTag[index].tag.attributes!!.name,
                                        color = if (ingredientsTag[index].isChecked) Colors.white else Color.Gray
                                    )
                                    if (ingredientsTag[index].isChecked) {
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
                }
            )
        }
    }
}

@Composable
fun EquipmentPreferencesSection() {
    if (EquipmentPreferencesSectionTemplate != null) {
        //  EquipmentPreferencesSectionTemplate.let { it() }
    } else {
        Column {
            Text(text = PreferencesText.guestLabel)
            Text(text = PreferencesText.guestLabel)
            Column {

            }
        }
    }
}

@Composable
fun PreferencesSuccessView(ingredientsTag: List<CheckableTag>) {
    Column {
        GuestPreferencesSection()
        DietPreferencesSection()
        IngredientPreferencesSection(ingredientsTag)
        EquipmentPreferencesSection()
    }
}