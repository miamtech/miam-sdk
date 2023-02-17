package com.miam.sdk.components.sponsorDetail.sponsorBlock

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

// TODO make it work with youtube link

@Composable
fun SponsorVideoBlock(url: String) {

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                    url
                )
            )
            prepare()
            playWhenReady = true
        }
    }

    Box(
        Modifier
            .height(240.dp)
            .fillMaxWidth()
    ) {
        DisposableEffect(key1 = Unit) { onDispose { exoPlayer.release() } }

        AndroidView(
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                }
            }
        )
    }
}