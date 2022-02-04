package com.miam.kmm_miam_sdk.android.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.recipeCard.RecipeCardContract
import com.miam.kmm_miam_sdk.component.recipeCard.RecipeCardViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


@coil.annotation.ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class RecipeView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr), KoinComponent {

    private var vmRecipeCard : RecipeCardViewModel = RecipeCardViewModel()


    init {
        vmRecipeCard.setEvent(
            RecipeCardContract.Event.OnGetRecipe(
                idRecipe = 1
            )
        )
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {

        val state by vmRecipeCard.uiState.collectAsState()

        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        val toggleBottomSheet =  {
            scope.launch {
                bottomSheetState.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                println(bottomSheetState.isVisible)
            }
        }


        Box( ){
            ManagementResourceState(
                resourceState = state.recipeCard,
                successView = { recipe ->
                    requireNotNull(recipe)
                    recipeCard(recipe, vmRecipeCard,  toggleBottomSheet  )
                },
                onTryAgain = { vmRecipeCard.setEvent(RecipeCardContract.Event.Retry) },
                onCheckAgain = { vmRecipeCard.setEvent(RecipeCardContract.Event.Retry) },
            )
            BottomSheet(bottomSheetState)

            /* BackHandler(enabled = bottomSheetState.isVisible) {
                scope.launch {  bottomSheetState.hide() }
            }*/
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun recipeCard(recipe : Recipe ,vmRecipeCard: RecipeCardViewModel, toggleBottomSheet: () -> Job ) {
    val price = Price(recipeId = recipe.id)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box  {
            Column {
                Box ( modifier = Modifier
                    .height(245.dp)
                    .fillMaxWidth()) {
                    Image(
                        painter = rememberImagePainter(recipe.attributes.mediaUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(245.dp)
                            .fillMaxWidth(),

                        )
                    Text(
                        text = recipe.attributes.title,
                        style = MaterialTheme.typography.h5.copy(color = Color.White, fontSize = 27.sp ,fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .align(Alignment.Center)
                            .padding(horizontal = 30.dp)
                    )
                    if (vmRecipeCard.currentState.isInCart) {
                        Box(modifier = Modifier
                            .absoluteOffset(x = 8.dp, y = 8.dp)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 4.dp,
                                    topStart = 4.dp,
                                    bottomStart = 4.dp,
                                    bottomEnd = 4.dp
                                )
                            )
                            .background(Color(0xffF47F7A))){
                            Row(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center){

                                Text(text = "Déjà ajoutée", color = Color.White,
                                    modifier = Modifier.padding(horizontal = 5.dp))
                            }
                        }
                    }else {
                        FloatingActionButton(modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(24.dp)
                            .absoluteOffset(x = 8.dp, y = 8.dp),
                            backgroundColor = Color.Gray,
                            onClick =  { toggleBottomSheet() }) {
                            Text(text = "?", color = Color.White )
                        }
                    }


                }
                Row (
                    Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 32.dp,
                            bottom = 16.dp
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row() {
                        Column(Modifier.padding(end = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.ic_clock),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(text = recipe.totalTime ,fontSize = 16.sp,modifier= Modifier.padding(top = 4.dp))
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
                            Text(text = recipe.difficultyLabel ,fontSize = 16.sp, modifier= Modifier.padding(top = 4.dp))
                        }
                    }
                    price.content()
                }

                Row(
                    Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 8.dp
                    ), verticalAlignment = Alignment.CenterVertically,){
                    Image(
                        painter = painterResource(R.drawable.ic_peoples),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                        ){
                        IconButton(onClick = { vmRecipeCard.setEvent(RecipeCardContract.Event.DecreaseGuest) },) {
                            Image(
                                painter = painterResource(R.drawable.ic_less),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .height(32.dp)
                                .width(48.dp)
                                .border(
                                    border = BorderStroke(width = 1.dp, color = Color.Gray),
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text =  vmRecipeCard.currentState.guest.toString(),  modifier = Modifier
                                .padding(
                                    horizontal = 8.dp,
                                    vertical = 4.dp
                                ))
                        }
                        IconButton(onClick = { vmRecipeCard.setEvent(RecipeCardContract.Event.IncreaseGuest) }) {
                            Image(
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

            }
            Box(modifier = Modifier
                .absoluteOffset(x= 0.dp, y = 178.dp)){

                Box(modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                    .background(Color(0xff00af98))){
                    Row(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center){
                        Image(
                            painter = painterResource(R.drawable.ic_cookhat),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = "Recette", color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp))
                    }
                }
            }

            if (vmRecipeCard.currentState.isInCart) {
                Box(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 8.dp)) {
                    FloatingActionButton(modifier = Modifier.size(36.dp),
                        backgroundColor = Color(0xff037E92),
                        onClick = { toggleBottomSheet() }) {
                        Image(
                            painter = painterResource(R.drawable.ic_details),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else {
                Box(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 8.dp)) {
                    FloatingActionButton(modifier = Modifier.size(36.dp),
                        backgroundColor = Color(0xff037E92),
                        onClick = { vmRecipeCard.setEvent(RecipeCardContract.Event.OnAddRecipe) }) {
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