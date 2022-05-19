package com.miam.kmm_miam_sdk.android.ui.components.dialog


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.PixelFormat
import android.view.View
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.recipdeDetails
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterContent
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import org.koin.core.component.KoinComponent


class Dialog : KoinComponent {

    private var vmRouter: RouterViewModel = RouterViewModel()

    fun goToDetail(vmRecipe :RecipeViewModel){
        vmRouter.setEvent(
            RouterContract.Event.GoToDetail(
                vmRecipe
            )
        )
    }

    fun goToPreview(recipeId: String ,vmRecipe :RecipeViewModel) {
        vmRouter.setEvent(
            RouterContract.Event.GoToPreview(
                recipeId = recipeId,
                vm = vmRecipe
            )
        )
    }


    @Composable
    fun Content()  {

        val state by vmRouter.uiState.collectAsState()

        if (state.isOpen) {
            Box(){
                BackHandler {
                    vmRouter.setEvent(RouterContract.Event.CloseDialog)
                }
                FullScreen {
                    Box(){

                        when(state.content){
                            RouterContent.RECIPE_DETAIL  -> state.rvm?.let { recipdeDetails(it, vmRouter, fun (){ vmRouter.setEvent(RouterContract.Event.CloseDialog)}) }
                            RouterContent.BASKET_PREVIEW -> state.bpvm?.let { BasketPreview(vmRouter ,it, state.rvm!!, fun (){ vmRouter.setEvent(RouterContract.Event.CloseDialogFromPreview)}).content() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FullScreen(content: @Composable () -> Unit) {

    fun getActualStatusBarHeight(resources: Resources):Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId >0 ){
            return (resources.getDimensionPixelSize(resourceId) / resources.getDisplayMetrics().density).toInt()
        }
        return 0
    }

    fun getActualNavigationBarHeight(resources: Resources) : Int {
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return (resources.getDimensionPixelSize(resourceId) / resources.getDisplayMetrics().density).toInt()
        }
        return 0;
    }

    val view = LocalView.current
    val statusBarHeight = getActualStatusBarHeight(view.context.getResources())
    val bottomBarHeight = getActualNavigationBarHeight(view.context.getResources())
    val parentComposition = rememberCompositionContext()
    val currentContent by rememberUpdatedState(content)

    val fullScreenLayout = remember {
        FullScreenLayout(
            view
        ).apply {
            setContent(parentComposition) {
                Box(Modifier.padding(top= statusBarHeight.dp, bottom = bottomBarHeight.dp)) {
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
) : AbstractComposeView(composeView.context) {

    private val windowManager =
        composeView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager


    private val params = createLayoutParams()

    override var shouldCreateCompositionOnAttachedToWindow: Boolean = false
        private set

    init {
        id = android.R.id.content
        ViewTreeLifecycleOwner.set(this, ViewTreeLifecycleOwner.get(composeView))
        ViewTreeViewModelStoreOwner.set(this, ViewTreeViewModelStoreOwner.get(composeView))
        ViewTreeSavedStateRegistryOwner.set(this, ViewTreeSavedStateRegistryOwner.get(composeView))

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
            flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
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



