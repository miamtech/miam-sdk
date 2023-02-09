package com.miam.sdk.components.sponsorDetail

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.core.sdk.viewModels.sponsorDetailViewModel.SponsorDetailViewModelImpl
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class SponsorDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {


    private var vmSponsorDetail: SponsorDetailViewModelImpl = SponsorDetailViewModelImpl()

    fun bind(sponsor: Sponsor) {
        vmSponsorDetail.fetchSponsorBlockByIds(sponsor)
    }

    @Composable
    override fun Content() {
        val state by vmSponsorDetail.state.collectAsState()

        ManagementResourceState(
            resourceState = state.sponsorBlocks,
            successView = { sponsorBlocks ->
                requireNotNull(sponsorBlocks)
                SponsorDetailSuccessView(sponsorBlocks)
            },
            loadingView = {
                Template.sponsorDetailLoadingTemplate?.let {
                    it()
                } ?: SponsorDetailLoadingView()
            },
            emptyView = {
                Template.sponsorDetailEmptyTemplate?.let {
                    it()
                } ?: Box {}
            }
        )
    }

}