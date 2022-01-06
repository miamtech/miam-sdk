package com.miam.kmm_miam_sdk.android.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.recipeCard.RecipeCardContract
import com.miam.kmm_miam_sdk.component.recipeCard.RecipeCardViewModel
import com.miam.kmm_miam_sdk.network.model.Recipe

class RecipeView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private var vmRecipeCard : RecipeCardViewModel = RecipeCardViewModel()

    init {
        vmRecipeCard.setEvent(
            RecipeCardContract.Event.OnGetRecipe(
                idRecipe = 1
            )
        )
    }

    // The Content function works as a Composable function so we can now define our Compose UI components to render.
    @Composable
    override fun Content() {
        RecipeCardView(
            vmRecipeCard
        )
    }
}


@ExperimentalCoilApi
@Composable
fun RecipeCardView(vmRecipeCard: RecipeCardViewModel){

    val state by vmRecipeCard.uiState.collectAsState()

    Box( ){
        ManagementResourceState(
            resourceState = state.recipeCard,
            successView = { recipe ->
                requireNotNull(recipe)
                recipeCard(recipe)
            },
            onTryAgain = { vmRecipeCard.setEvent(RecipeCardContract.Event.Retry) },
            onCheckAgain = { vmRecipeCard.setEvent(RecipeCardContract.Event.Retry) },
            )
    }
}

@ExperimentalCoilApi
@Composable
private fun recipeCard(recipe : Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Box  {
            Column {
                Box ( modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()) {
                    Image(
                        painter = rememberImagePainter(recipe.attributes.mediaUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = recipe.attributes.title,
                        style = MaterialTheme.typography.h5.copy(color = Color.White, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .align(Alignment.Center)
                            .padding(horizontal = 30.dp)
                    )
                    FloatingActionButton(modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(24.dp)
                        .absoluteOffset(x = 8.dp, y = 8.dp),
                        backgroundColor = Color.Gray,
                        onClick = { /*TODO*/ }) {
                        Text(text = "?", color = Color.White )
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
//                            Image(
//                                painter = painterResource(R.drawable.ic_clock),
//                                contentDescription = null,
//                                modifier = Modifier.size(16.dp)
//                            )
                            Text(text = "1 h 10",fontSize = 8.sp,modifier= Modifier.padding(top = 4.dp))
                        }


                        Divider(
                            color = Color.Gray,
                            modifier = Modifier
                                .height(28.dp)
                                .width(1.dp)
                        )
                        Column(
                            Modifier.padding(start = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
//                            Image(
//                                painter = painterResource(R.drawable.ic_diflow),
//                                contentDescription = null,
//                                modifier = Modifier.size(16.dp)
//                            )
                            Text(text = "Facile",fontSize = 8.sp, modifier= Modifier.padding(top = 4.dp))
                        }
                    }
                    Row(){
                        Column() {
                            Row(){
                                Text("4,",color = Color(0xff037E92))
                                Text("99€",color = Color(0xff037E92), fontSize = 8.sp)
                            }
                            Text("par pers.",color = Color.Gray,fontSize = 8.sp)
                        }
                    }


                }

                Row(
                    Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                ), verticalAlignment = Alignment.CenterVertically,){
//                    Image(
//                        painter = painterResource(R.drawable.ic_peoples),
//                        contentDescription = null,
//                        modifier = Modifier.size(16.dp)
//                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,


                        ){
                        IconButton(onClick = { /*TODO*/ },) {
//                            Image(
//                                painter = painterResource(R.drawable.ic_less),
//                                contentDescription = null,
//                                modifier = Modifier.size(16.dp)
//                            )


                        }
                        TextField(value = "4", onValueChange = {/*TODO*/},
                            modifier= Modifier
                                .size(40.dp)
                                .padding(0.dp)
                                .border(
                                    border = BorderStroke(width = 1.dp, color = Color.Gray),
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent))
                        IconButton(onClick = { /*TODO*/ }) {
//                            Image(
//                                painter = painterResource(R.drawable.ic_plus),
//                                contentDescription = null,
//                                modifier = Modifier.size(16.dp)
//                            )
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
//                        Image(
//                            painter = painterResource(R.drawable.ic_cookhat),
//                            contentDescription = null,
//                            modifier = Modifier.size(20.dp)
//                        )
                        Text(text = "Recette", color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp))
                    }

                }

            }

            Box(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 8.dp, bottom = 8.dp)) {
                FloatingActionButton(modifier = Modifier.size(36.dp),
                    backgroundColor = Color(0xff037E92),
                    onClick = { /*TODO*/ }) {
//                    Image(
//                        painter = painterResource(R.drawable.ic_cart),
//                        contentDescription = null,
//                        modifier = Modifier.size(20.dp)
//                    )

                }
            }

        }
    }

}