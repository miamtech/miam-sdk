package com.miam.kmm_miam_sdk.android.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.BasketPreview
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContent
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContract
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.get


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class BottomSheetView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr), KoinComponent {

    private val vmBottomSheet: BottomSheetViewModel = get(BottomSheetViewModel::class.java)


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)


        val contentState by vmBottomSheet.uiState.collectAsState()
        val scope = rememberCoroutineScope()

        val toggleBottomSheet =  {
            scope.launch {
                bottomSheetState.animateTo(ModalBottomSheetValue.Expanded, tween(500))
            }
        }

        LaunchedEffect(key1 = true) {
            vmBottomSheet.effect.collectLatest { effect ->
                   when (effect) {
                        BottomSheetContract.Effect.BottomSheetOpened ->  toggleBottomSheet()
                        BottomSheetContract.Effect.BottomSheetClosed ->  toggleBottomSheet()
                    }
            }
        }
        BottomSheet(bottomSheetState = bottomSheetState, contentState= contentState, vmBottomSheet= vmBottomSheet )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    bottomSheetState: ModalBottomSheetState,
    contentState: BottomSheetContract.State,
    vmBottomSheet: BottomSheetViewModel
) {
    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheetContent( contentState,vmBottomSheet)
        }
    ){}
}

@Composable
private fun BottomSheetContent( contentState: BottomSheetContract.State, vmBottomSheet: BottomSheetViewModel){

    val goTo = { destination: BottomSheetContract.Event  ->  vmBottomSheet.setEvent(destination) }

    Box(modifier = Modifier.defaultMinSize(minHeight = 1.dp)) {
        when(contentState.content) {
            BottomSheetContent.RECIPE_DETAIL -> RecipeDetail(goTo)
            BottomSheetContent.RECIPE_HELPER -> RecipeHelper(goTo)
            BottomSheetContent.RECIPE_SPONSOR -> RecipeSponsor(goTo)
            BottomSheetContent.BASKET_PREVIEW -> BasketPreview(vmBottomSheet.currentState.recipeId ?: -1 ).content(

            )
            else -> Box(){}
        }
    }

}
