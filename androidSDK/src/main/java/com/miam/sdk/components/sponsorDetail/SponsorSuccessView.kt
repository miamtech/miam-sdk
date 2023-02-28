package com.miam.sdk.components.sponsorDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.miam_core.model.SponsorBlock
import com.miam.kmmMiamCore.miam_core.model.SponsorBlockTypesList
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorImageAndTextBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorImageBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorImageWithTextBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorTextAndImageBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorTextBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorTitleBlock
import com.miam.sdk.components.sponsorDetail.sponsorBlock.SponsorVideoBlock
import java.util.*


@Composable
fun SponsorDetailSuccessView(sponsorBlocks: List<SponsorBlock>, previous: () -> Unit) {

    Scaffold(topBar = {
        SponsorDetailTopBar(previous)
    }) { paddingValue ->
        Column(
            Modifier
                .background(white)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
                .padding(paddingValue)
        ) {
            sponsorBlocks.sortedBy { it.attributes?.position }.forEach { sponsorBlock ->
                BlockFactory(sponsorBlock)
            }
        }
    }
}


@Composable
fun BlockFactory(sponsorBlock: SponsorBlock) {
    sponsorBlock.relationships?.sponsorBlockType?.data?.id?.let {
        //TODO uppercase is a quick fixe because sponsorBlockType don't have attibutes error in serialisation ?
        when (SponsorBlockTypesList.valueOf(it.uppercase(Locale.getDefault()))) {
            SponsorBlockTypesList.TITLE, SponsorBlockTypesList.SMALL_TITLE -> sponsorBlock.attributes?.content?.let { content ->
                SponsorTitleBlock(text = content)
            }
            SponsorBlockTypesList.PICTURE, SponsorBlockTypesList.SMALL_PICTURE -> sponsorBlock.attributes?.pictureUrl?.let { picUrl ->
                SponsorImageBlock(picUrl)
            }
            SponsorBlockTypesList.TEXT, SponsorBlockTypesList.SMALL_TEXT -> sponsorBlock.attributes?.content?.let { content ->
                SponsorTextBlock(
                    content,
                    backgroundColor = sponsorBlock.attributes?.backgroundColor
                )
            }
            SponsorBlockTypesList.IMAGE_AND_TEXT -> sponsorBlock.attributes?.content?.let { content ->
                sponsorBlock.attributes?.pictureUrl?.let { pictureUrl ->
                    SponsorImageAndTextBlock(
                        content,
                        pictureUrl,
                        backgroundColor = sponsorBlock.attributes?.backgroundColor
                    )
                }
            }
            SponsorBlockTypesList.TEXT_AND_IMAGE -> sponsorBlock.attributes?.content?.let { content ->
                sponsorBlock.attributes?.pictureUrl?.let { pictureUrl ->
                    SponsorTextAndImageBlock(
                        content,
                        pictureUrl,
                        backgroundColor = sponsorBlock.attributes?.backgroundColor
                    )
                }
            }
            SponsorBlockTypesList.IMAGE_WITH_TEXT -> sponsorBlock.attributes?.content?.let { content ->
                sponsorBlock.attributes?.pictureUrl?.let { pictureUrl ->
                    SponsorImageWithTextBlock(
                        content,
                        pictureUrl,
                        backgroundColor = sponsorBlock.attributes?.backgroundColor
                    )
                }
            }
            SponsorBlockTypesList.VIDEO -> sponsorBlock.attributes?.videoUrl?.let { videoUrl ->
                SponsorVideoBlock(videoUrl)
            }
            else -> {
                Box {
                }
            }
        }
    }

}