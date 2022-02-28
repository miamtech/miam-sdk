package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetContract
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.miam_core.data.repository.RecipeSuggestionsRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent


class RecipeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr), KoinComponent {

    private var vmRecipe: RecipeViewModel = RecipeViewModel()
    private val idRecipeState: MutableState<Int?> = mutableStateOf(null)

    init {
    }

    fun bind(recipeId: Int = 0, recipe: Recipe? = null) {
        if (recipeId != 0) {
            vmRecipe.setEvent(
                RecipeContract.Event.OnGetRecipe(
                    recipeId
                )
            )
        } else if (recipe != null) {
            vmRecipe.setEvent(
                RecipeContract.Event.OnSetRecipe(
                    recipe
                )
            )
        }
    }


    var idRecipe: Int
        get() = idRecipeState.value ?: 0
        set(value) {
            idRecipeState.value = value
            if (value != null) {
                vmRecipe.setEvent(
                    RecipeContract.Event.OnGetRecipe(
                        value
                    )
                )
            }
        }


    @Composable
    override fun Content() {
        val vmBottomSheet: BottomSheetViewModel =
            KoinJavaComponent.get(BottomSheetViewModel::class.java)
        val state by vmRecipe.uiState.collectAsState()


        Box() {
            ManagementResourceState(
                resourceState = state.recipeState,
                successView = { recipe ->
                    requireNotNull(recipe)
                    recipeCard(recipe, vmRecipe, vmBottomSheet)
                },
                onTryAgain = { vmRecipe.setEvent(RecipeContract.Event.Retry) },
                onCheckAgain = { vmRecipe.setEvent(RecipeContract.Event.Retry) },
            )
            /* BackHandler(enabled = bottomSheetState.isVisible) {
                scope.launch {  bottomSheetState.hide() }
            }*/
        }
    }
}


@Composable
private fun recipeCard(
    recipe: Recipe,
    vmRecipe: RecipeViewModel,
    vmBottomSheet: BottomSheetViewModel
) {
    val price = Price(recipeId = recipe.id)
    val openDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box {
            Column {
                Box(
                    modifier = Modifier
                        .height(245.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(recipe.attributes.mediaUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(245.dp)
                            .fillMaxWidth()
                            .clickable(onClick = { openDialog.value = true })
                    )
                    Text(
                        text = recipe.attributes.title,
                        style = MaterialTheme.typography.h5.copy(
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .align(Alignment.Center)
                            .padding(horizontal = 30.dp)
                    )
                    if (vmRecipe.currentState.isInCart) {
                        Box(
                            modifier = Modifier
                                .absoluteOffset(x = 8.dp, y = 8.dp)
                                .clip(
                                    RoundedCornerShape(
                                        topEnd = 4.dp,
                                        topStart = 4.dp,
                                        bottomStart = 4.dp,
                                        bottomEnd = 4.dp
                                    )
                                )
                                .background(Color(0xffF47F7A))
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 10.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = "Déjà ajoutée", color = Color.White,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                )
                            }
                        }
                    } else {
                        FloatingActionButton(modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(24.dp)
                            .absoluteOffset(x = 8.dp, y = 8.dp),
                            backgroundColor = Color.Gray,
                            onClick = { vmBottomSheet.setEvent(BottomSheetContract.Event.GoToDetail) }) {
                            Text(text = "?", color = Color.White)
                        }
                    }
                }

                RouterModal(vmRecipe, openDialog = openDialog)




                Row(
                    Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 32.dp,
                            bottom = 16.dp
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Column(
                            Modifier.padding(end = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_clock),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = recipe.totalTime,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }


                        Divider(
                            color = Color.Gray,
                            modifier = Modifier
                                .height(32.dp)
                                .width(1.dp)
                        )
                        Column(
                            Modifier.padding(start = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_diflow),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = recipe.difficultyLabel,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                    price.content()
                }
                Counter(
                    vmRecipe.currentState.guest,
                    { vmRecipe.setEvent(RecipeContract.Event.IncreaseGuest) },
                    { vmRecipe.setEvent(RecipeContract.Event.DecreaseGuest) },
                    CounterModifier()
                )
            }
            Box(
                modifier = Modifier
                    .absoluteOffset(x = 0.dp, y = 188.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .background(Color(0xff00af98))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_cookhat),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Recette", color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
            }

            if (vmRecipe.currentState.isInCart) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    FloatingActionButton(modifier = Modifier.size(36.dp),
                        backgroundColor = Color(0xff037E92),
                        onClick = {
                            vmBottomSheet.setEvent(
                                BottomSheetContract.Event.GoToPreview(
                                    recipeId = recipe.id
                                )
                            )
                        }) {
                        Image(
                            painter = painterResource(R.drawable.ic_details),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    FloatingActionButton(modifier = Modifier.size(36.dp),
                        backgroundColor = Color(0xff037E92),
                        onClick = {
                            vmRecipe.setEvent(RecipeContract.Event.OnAddRecipe)
                            vmBottomSheet.setEvent(
                                BottomSheetContract.Event.GoToPreview(
                                    recipeId = recipe.id
                                )
                            )
                        }) {
                        Image(
                            painter = painterResource(R.drawable.ic_cart),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
