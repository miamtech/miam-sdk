package com.miam.sdk.components.sponsorDetail.sponsorBlock

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

// Youtube Player need  com.pierfrancescosoffritti.androidyoutubeplayer:core
//    val idVideo = url.substringAfter("watch?v=").substringBefore('&')
//    Box(Modifier.padding(top = 8.dp).padding(horizontal = 16.dp)) {
//        AndroidView(factory = {
//            YouTubePlayerView(it).apply {
//                addYouTubePlayerListener(
//                    object: AbstractYouTubePlayerListener() {
//                        override fun onReady(youTubePlayer: YouTubePlayer) {
//                            super.onReady(youTubePlayer)
//                            youTubePlayer.loadVideo(idVideo, 0f)
//                        }
//                    }
//                )
//            }
//        })
//    }


    Box(
        Modifier
            .height(240.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
