package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.fake.SponsorFakeFactory
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.link
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.sponsorBannerLink
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.sponsorBannerText

@Composable
fun RecipeDetailSponsorBanner(sponsor: Sponsor, openSponsorDetail: (sponsor: Sponsor) -> Unit) {
    Template.recipeDetailSponsorBannerTemplate?.let {
        it(sponsor, openSponsorDetail)
    } ?: MiamRecipeDetailSponsorBanner(sponsor, openSponsorDetail)
}

@Composable
fun MiamRecipeDetailSponsorBanner(sponsor: Sponsor, openSponsorDetail: (sponsor: Sponsor) -> Unit) {
    Row(
        Modifier
            .background(white)
            .padding(16.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Column(Modifier.fillMaxWidth(0.7f)) {
            Text(text = sponsorBannerText, style = body)
            Spacer(Modifier.size(4.dp))
            Clickable({ openSponsorDetail(sponsor) }) {
                Text(text = sponsorBannerLink, style = link, color = primary)
            }

        }
        sponsor.attributes?.let {
            Image(
                modifier = Modifier
                    .width(64.dp)
                    .heightIn(0.dp, 96.dp),
                painter = rememberImagePainter(it.logoUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun RecipeDetailContentPreview() {
    RecipeDetailSponsorBanner(SponsorFakeFactory.create(), {})
}