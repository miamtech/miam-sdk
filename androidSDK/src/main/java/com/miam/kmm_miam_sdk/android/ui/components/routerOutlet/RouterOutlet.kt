package com.miam.kmm_miam_sdk.android.ui.components.routerOutlet


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.view.View
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorContract
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.component.router.RouterContent
import com.miam.kmmMiamCore.component.router.RouterOutletContract
import com.miam.kmmMiamCore.component.router.RouterOutletViewModel
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.ItemsSelector
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetails
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RouterOutlet: KoinComponent {

    private var vmRouter: RouterOutletViewModel = RouterOutletViewModel()
    private val itemSelectorViewModel: ItemSelectorViewModel by inject()

    fun getViewModel(): RouterOutletViewModel {
        return vmRouter
    }

    fun goToDetail(vmRecipe: RecipeViewModel, showDetailsFooter: Boolean = true) {
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToDetail(
                vmRecipe, showDetailsFooter
            )
        )
    }

    fun goToPreview(recipeId: String, vmRecipe: RecipeViewModel) {
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToPreview(
                recipeId = recipeId,
                vm = vmRecipe
            )
        )
    }

    fun goToReplaceItem() {
        itemSelectorViewModel.setEvent(
            ItemSelectorContract.Event.SetReturnToBasketPreview(
                returnToPreview = {
                    if (vmRouter.currentState.recipeId != null && vmRouter.currentState.rvm != null) {
                        goToPreview(
                            vmRouter.currentState.recipeId!!,
                            vmRouter.currentState.rvm!!
                        )
                    } else {
                        close()
                    }
                }
            )
        )
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToItemSelector
        )
    }

    private fun close() {
        vmRouter.setEvent(RouterOutletContract.Event.CloseDialogFromPreview)
    }

    @Composable
    fun Content() {
        val state by vmRouter.uiState.collectAsState()

        if (state.isOpen) {
            Box {
                BackHandler {
                    vmRouter.setEvent(RouterOutletContract.Event.CloseDialog)
                }
                FullScreen {
                    Box {
                        when (state.content) {
                            RouterContent.RECIPE_DETAIL -> state.rvm?.let {
                                RecipeDetails(
                                    it,
                                    vmRouter,
                                    fun() { vmRouter.setEvent(RouterOutletContract.Event.CloseDialog) })
                            }
                            RouterContent.BASKET_PREVIEW -> state.rvm?.let {
                                BasketPreview(
                                    recipeId = state.recipeId!!,
                                    it,
                                    { goToDetail(it) },
                                    ::close,
                                    ::goToReplaceItem
                                ).content()
                            }
                            RouterContent.ITEMS_SELECTOR -> ItemsSelector().Content()
                            RouterContent.EMPTY -> EmptyView(::close)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyView(close: () -> Unit) {
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = RecipeDetailsStyle.headerMainContainer,
            ) {
                Clickable(
                    onClick = { close() },
                ) {
                    Image(
                        painter = painterResource(Image.toggleCaret),
                        contentDescription = null,
                        modifier = RecipeDetailsStyle.headerCloseIconModifier.rotate(180f)
                    )
                }
            }
        },
        content = {
            Text(text = "Une erreur s'est produite, veuillez réessayer", textAlign = TextAlign.Center)
        }
    )
}

@Composable
fun FullScreen(content: @Composable () -> Unit) {

    val view = LocalView.current
    val parentComposition = rememberCompositionContext()
    val currentContent by rememberUpdatedState(content)

    val fullScreenLayout = remember {
        FullScreenLayout(
            view
        ).apply {
            setContent(parentComposition) {
                Box {
                    currentContent()
                }
            }
        }
    }

    DisposableEffect(fullScreenLayout) {
        fullScreenLayout.show()
        onDispose { fullScreenLayout.dismiss() }
    }
}

@SuppressLint("ViewConstructor")
private class FullScreenLayout(
    private val composeView: View
): AbstractComposeView(composeView.context) {

    private val windowManager =
        composeView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager


    private val params = createLayoutParams()

    override var shouldCreateCompositionOnAttachedToWindow: Boolean = false
        private set

    init {
        id = android.R.id.content
        ViewTreeLifecycleOwner.set(this, ViewTreeLifecycleOwner.get(composeView))
        ViewTreeViewModelStoreOwner.set(this, ViewTreeViewModelStoreOwner.get(composeView))
        setViewTreeSavedStateRegistryOwner(composeView.findViewTreeSavedStateRegistryOwner())

        setTag(R.id.compose_view_saveable_id_tag, "dialogLayout")
    }


    private var content: @Composable () -> Unit by mutableStateOf({})

    @Composable
    override fun Content() {
        content()
    }

    fun setContent(parent: CompositionContext, content: @Composable () -> Unit) {
        setParentCompositionContext(parent)
        this.content = content
        shouldCreateCompositionOnAttachedToWindow = true
    }

    private fun createLayoutParams(): WindowManager.LayoutParams =
        WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
            token = composeView.applicationWindowToken
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            format = PixelFormat.TRANSLUCENT
            flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        }

    fun show() {
        windowManager.addView(this, params)
    }

    fun dismiss() {
        disposeComposition()
        ViewTreeLifecycleOwner.set(this, null)
        windowManager.removeViewImmediate(this)
    }
}



