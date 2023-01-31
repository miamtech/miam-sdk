package com.miam.kmm_miam_sdk.android.ui.components.basketPreview


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewContract
import com.miam.kmmMiamCore.component.basketPreview.BasketPreviewViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent.BasketPreviewFooter
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent.BasketPreviewHeader
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState


class BasketPreview(
    recipeId: String,
    private val recipeVm: RecipeViewModel,
    val goToDetail: () -> Unit,
    val previous: () -> Unit,
    val close: () -> Unit,
    private val goToItemSelector: () -> Unit,
) {

    private val vmBasketPreview: BasketPreviewViewModel = BasketPreviewViewModel(recipeId)

    @Composable
    fun content() {
        fun removeRecipeAndClose() {
            vmBasketPreview.setEvent(BasketPreviewContract.Event.RemoveRecipe(recipeVm.recipeId ?: ""))
            close()
        }

        Scaffold(
            topBar = {
                if (Template.basketPreviewHeaderTemplate != null) {
                    Template.basketPreviewHeaderTemplate?.let {
                        it(recipeVm, goToDetail, previous)
                    }
                } else {
                    BasketPreviewHeader(goToDetail, previous)
                }
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    UpdatableContent(
                        vmBasketPreview,
                        { recipeVm.updateGuest(it) },
                        goToDetail,
                        goToItemSelector
                    )
                }
            },
            bottomBar = {
                BottomAppBar(backgroundColor = RecipeDetailsColor.footerSectionBackgroundColor) {
                    BasketPreviewFooter(
                        { removeRecipeAndClose() }
                    ) {
                        close()
                    }
                }
            }
        )
    }
}

@Composable
fun UpdatableContent(
    vmBasketPreview: BasketPreviewViewModel,
    updateGuest: (guestCount: Int) -> Unit,
    goToDetail: () -> Unit,
    goToItemSelector: () -> Unit,
) {

    val state by vmBasketPreview.uiState.collectAsState()

    ManagementResourceState(
        resourceState = state.line,
        successView = { line ->
            requireNotNull(line)
            BasketPreviewSuccessView(
                line,
                vmBasketPreview,
                { guestCount -> vmBasketPreview.updateGuest(updateGuest, guestCount) },
                state.isReloading,
                goToDetail,
                goToItemSelector
            )
        },
        loadingView = { BasketPreviewLoader() }
    )
}


@Composable
fun BasketPreviewSuccessView(
    line: BasketPreviewLine,
    vmBasketPreview: BasketPreviewViewModel,
    updateGuest: (guestCount: Int) -> Unit,
    isReloading: Boolean,
    goToDetail: () -> Unit,
    goToItemSelector: () -> Unit
) {
    Column {
        BasketPreviewRecipeLine(
            line = line,
            updateGuest,
            goToDetail,
            isReloading
        )
        BasketPreviewItem(
            line = line,
            vmBasketPreview = vmBasketPreview,
            goToItemSelector = { goToItemSelector() }
        )
        Spacer(modifier = Modifier.padding(vertical = 32.dp))
    }
}