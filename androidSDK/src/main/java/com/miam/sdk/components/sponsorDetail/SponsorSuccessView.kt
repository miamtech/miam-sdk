package com.miam.sdk.components.sponsorDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.miam.kmmMiamCore.miam_core.model.SponsorBlock
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorImageBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.Titre


@Composable
fun SponsorDetailSuccessView(sponsorBlocks: List<SponsorBlock>) {
    Column(
        Modifier
            .background(white)
            .fillMaxSize()
    ) {
        sponsorBlocks.forEach { sponsorBlock ->
            blockFactory(sponsorBlock)
        }
    }
}


@Composable
fun blockFactory(sponsorBlock: SponsorBlock) {
    when (sponsorBlock.relationships?.sponsorBlockType?.data?.id ?: "") {
        "titre" -> sponsorBlock.attributes?.text?.let { Titre(text = it) }
        "image" -> sponsorBlock.attributes?.pictureUrl?.let { SponsorImageBlock(it) }
        "petite-image" -> sponsorBlock.attributes?.pictureUrl?.let { SponsorImageBlock(it) }
        "petit-text" -> sponsorBlock.attributes?.text?.let { SponsorImageBlock(it) }
        else -> {
            Box {

            }
        }
    }
}